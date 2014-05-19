package ec.gob.simintel.administracion.controladores;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.DefaultRequestContext;
import ec.gob.simintel.administracion.servicios.ServicioGrupoDatos;
import ec.gob.simintel.administracion.servicios.ServicioSetAtributo;
import ec.gob.simintel.controladores.commons.Constantes;
import ec.gob.simintel.controladores.commons.GeneradorMensajes;
import ec.gob.simintel.controladores.commons.MensajesError;
import ec.gob.simintel.controladores.commons.MensajesInformacion;
import ec.gob.simintel.entidades.Grupodato;
import ec.gob.simintel.entidades.Setatributo;
import ec.gob.simintel.validadores.UtilRedireccion;


/**
 * Premite crear los grupos de Datos que van estar ligados a las descripciones del Equipamiento 
 * @author Klintozcano
 *
 */

@ManagedBean
@ViewScoped
public class ControladorGrupoDatos {
	
	@EJB
	private ServicioGrupoDatos srvGrupoDtos;
	private Grupodato grupoDato;
	private List<Grupodato> lstGrupoDto;
	@EJB
	private ServicioSetAtributo srvSetatributo;
	private Setatributo  equipo;
	private List<Setatributo> lstEquipos;
	private Integer idSetAtributo;
	
	/*Busquedas*/
	private String nombreGrupoDtoABuscar;
	private boolean activarBotonInsertar;
	private boolean activarBotonActualizar;
	
	/*Botones de operaciones CRUD*/
	private Boolean botonInsertarGrupoDto;
	private Boolean botonActualizarGrupoDto;
	private Boolean botonEliminarGrupoDtos;
	
	private Boolean botonInsertarSetatributo;
	private Boolean botonActualizarSetatributo;
	private Boolean botonEliminarSetatributo;
	private int idSeriado;
	
	public Boolean accionGuardar;
	
	
	
	@PostConstruct
	public void inicio(){
		System.out.println("Ejecuntado post");
		nuevoGrupoDato();
		cargarListaGrupoDatos();
		setLstEquipos(new ArrayList<Setatributo>());
		habilitarAgregarEquipos();
	}
	
	public void nuevoGrupoDato(){
		grupoDato= new Grupodato();
		setLstEquipos(new ArrayList<Setatributo>());
		nuevoEquipo();
		habilitarGuardarGrupoDtos();
	}
	
	public void nuevoEquipo(){
		setEquipo(new Setatributo());
		idSeriado=0;
		
	}
	
	private void habilitarGuardarGrupoDtos(){
		setBotonInsertarGrupoDto(false);
		setBotonActualizarGrupoDto(true);
		setBotonEliminarGrupoDtos(true);
		
	}
		
	public void habilitarEditarGrupoDtos(){
		setBotonInsertarGrupoDto(true);
		setBotonActualizarGrupoDto(false);
		setBotonEliminarGrupoDtos(false);
		cargarListaEquipos();		
		
	}
	
	public void habilitarAgregarEquipos(){
		botonInsertarSetatributo=false;
		botonActualizarSetatributo=true;
		
	} 
	public void habilitarModificarEquipos(){
		System.out.println("Entro a modificar Equipo");
		botonInsertarSetatributo=true;
		botonEliminarSetatributo=true;
		botonActualizarSetatributo=false;
		
		if(botonInsertarGrupoDto==false){//estoy insertando grupo 
			botonInsertarGrupoDto=true;
			accionGuardar=true;//le llamo true si estube guardando grupo
		}else{//estoy actualizando grupo
			accionGuardar=false;
			botonActualizarGrupoDto=true;
			botonEliminarGrupoDtos=true;
		}		
		obtenerSeriado();//permite pintar del equipo el id del combo
	}
	
	
	/**
	 * Método que me permite cargar la lista Grupos de Datos 
	 * @return
	 * lista de Grupo de datos
	 */
	
	public void cargarListaGrupoDatos(){
		lstGrupoDto = srvGrupoDtos.buscarTodos();
	}
	
	public void cargarListaEquipos(){		
		lstEquipos =srvSetatributo.buscarEquipoPorGrupodeDatos(grupoDato.getGrupodatosid());
		ponerIndices();
	}
	
	public void ponerIndices(){
		int indice=0;
		for(Setatributo e : lstEquipos){
			e.setIndice(indice);
			indice++;
		}
	}
	
	public void abrirDialogoBuscarGrupoDtos(){
		cargarListaGrupoDatos();
		DefaultRequestContext.getCurrentInstance().execute("dlgBuscarGrupoDatos.show()");
		
	}
	
	public void abrirDialogoEliminarEquipamiento(){
		DefaultRequestContext.getCurrentInstance().execute("dlgEliminarEquipo.show()");
	}
	
	public void agregarEquipos(){		
		equipo.setGrupodato(grupoDato);
		equipo.setIndice(lstEquipos.size());		
		ponerSeriado(equipo);//setea seriado en el objeto equipo que vamos a agregar a la lista 
		lstEquipos.add(equipo);
		nuevoEquipo();
	}
	
	public void actualizarEquipo(){
		for(Setatributo filaEquipo: lstEquipos){
			if(equipo.getIndice()==filaEquipo.getIndice()){//encuentra la fila seleccionada por su indice
				ponerSeriado(filaEquipo);//setea seriado en la fila que estamos actualizando
				break;
			}
		}
		nuevoEquipo();
		habilitarAgregarEquipos();		
		botonEliminarSetatributo=false;
		
		if(accionGuardar){//verifico que la accion que estube realizando es guardar grupo, para vover a activar los botones de guardar
			botonInsertarGrupoDto=false;
		}else{//la accion que estube realizando es actualizar grupo
			botonActualizarGrupoDto=false;
			botonEliminarGrupoDtos=false;
		}
	}
	
	public void eliminarEquipo(){
		if(equipo.getSetatributosid()!=null){
			//Existe en base, debo eliminarlo
			srvSetatributo.eliminar(equipo);			
		}
		lstEquipos.remove(equipo);
		//vuelvo a poner nuevos indices
		ponerIndices();		
	}
	
	/**
	 * Setea el valor del seriado seleccionado en el combo 
	 * @return True = si lo escogio y se seteo, False = si no se escogio 
	 */
	
	public void ponerSeriado(Setatributo equipo){
		switch(idSeriado){
		case 1:
			equipo.setSeriado(Constantes.SERIADOSI);
		  	break;
		case 2:
			equipo.setSeriado(Constantes.SERIADONO);
			break;
		}
		
	}
	
	/**
	 * Recupera el valor correspondiente de idSeriado para pintar en el combo
	 */
	public void obtenerSeriado(){
		idSeriado=0;
		System.out.println("entro a getseriado " + getEquipo().getSeriado());
		if (equipo.getSeriado().compareTo(Constantes.SERIADOSI)==0){
			idSeriado=1;
			
		}else if (equipo.getSeriado().compareTo(Constantes.SERIADONO)==0) {
			idSeriado=2;
		}
	}
	
	public void pintarCombos(){
		obtenerSeriado();
	}
	
	
	public void irAtributos(){
		UtilRedireccion.redireccionar("pagina/AtributosEquipos.jsf");
	}
	
	
	/**
	 * Creamos los Metodos de las operaciones CRUD Grupo de Datos
	 * @return
	 */
	
	
	public void insertarGrupoDtos(){
		System.out.println("Insertando");
		grupoDato.setSetatributos(getLstEquipos());//CON CascadeType.PERSIST AUTOMATICAMENTE INSERTA LOS EQUIPOS
		//ponerSeriado();
		srvGrupoDtos.insertar(grupoDato);
		nuevoGrupoDato();
	}
	
	public void actualizarGrupoDtos(){
		grupoDato.setSetatributos(lstEquipos);
		srvGrupoDtos.actualizar(grupoDato);//CON CascadeType.PERSIST AUTOMATICAMENTE INSERTA LOS EQUIPOS
		nuevoGrupoDato();
	}
	
	public void eliminarGrupoDtos(){
		
		try {
			srvGrupoDtos.eliminar(grupoDato);
			nuevoGrupoDato();
			GeneradorMensajes.mostrarMensajeInformacion(MensajesInformacion.COMMONS_ELIMINACION_OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			GeneradorMensajes.mostrarMensajeInformacion(MensajesError.COMMONS_TIENE_REGISTRO_ASOCIADOS);
		}
		
	}
	
	//METODOOS DE BUSQUEDA
	/**
	 * Busca Grupo de Dato con caracteres ingresados
	 *y setea los atributos @Transient descripcion de los equipos
	 */
	
	public void buscarGrupoDtosPorNombre(){
		System.out.println("imprimir"+ nombreGrupoDtoABuscar);
		if((nombreGrupoDtoABuscar.toString().compareTo("")!=0)){
			setLstGrupoDto(srvGrupoDtos.buscarPorDescripcion(nombreGrupoDtoABuscar));
			if(getLstGrupoDto().size() > 0){
				nombreGrupoDtoABuscar=null;
			}
			else{
				GeneradorMensajes.mostrarMensajeInformacion(MensajesInformacion.COMMONS_NO_EXISTEN_REGISTROS_CON_PARAMETRO);
				}
		}else{
			GeneradorMensajes.mostrarMensajeError(MensajesError.COMMONS_INGRESAR_PARAMETRO_BUSQUEDA);
		}
	}
		
	/**
	 *
	 * @return
	 */
	public Grupodato getGrupoDato() {
		return grupoDato;
	}
	public void setGrupoDato(Grupodato grupoDato) {
		this.grupoDato = grupoDato;
	}
	public List<Grupodato> getLstGrupoDto() {
		return lstGrupoDto;
	}
	public void setLstGrupoDto(List<Grupodato> lstGrupoDto) {
		this.lstGrupoDto = lstGrupoDto;
	}
	public String getNombreGrupoDtoABuscar() {
		return nombreGrupoDtoABuscar;
	}
	public void setNombreGrupoDtoABuscar(String nombreGrupoDtoABuscar) {
		this.nombreGrupoDtoABuscar = nombreGrupoDtoABuscar;
	}
	public boolean isActivarBotonInsertar() {
		return activarBotonInsertar;
	}
	public void setActivarBotonInsertar(boolean activarBotonInsertar) {
		this.activarBotonInsertar = activarBotonInsertar;
	}
	public boolean isActivarBotonActualizar() {
		return activarBotonActualizar;
	}
	public void setActivarBotonActualizar(boolean activarBotonActualizar) {
		this.activarBotonActualizar = activarBotonActualizar;
	}
	public Boolean getBotonInsertarGrupoDto() {
		return botonInsertarGrupoDto;
	}
	public void setBotonInsertarGrupoDto(Boolean botonInsertarGrupoDto) {
		this.botonInsertarGrupoDto = botonInsertarGrupoDto;
	}
	public Boolean getBotonActualizarGrupoDto() {
		return botonActualizarGrupoDto;
	}
	public void setBotonActualizarGrupoDto(Boolean botonActualizarGrupoDto) {
		this.botonActualizarGrupoDto = botonActualizarGrupoDto;
	}

	public Boolean getBotonEliminarGrupoDtos() {
		return botonEliminarGrupoDtos;
	}

	public void setBotonEliminarGrupoDtos(Boolean botonEliminarGrupoDtos) {
		this.botonEliminarGrupoDtos = botonEliminarGrupoDtos;
	}



	public Integer getIdSetAtributo() {
		return idSetAtributo;
	}

	public void setIdSetAtributo(Integer idSetAtributo) {
		this.idSetAtributo = idSetAtributo;
	}

	public Boolean getBotonInsertarSetatributo() {
		return botonInsertarSetatributo;
	}

	public void setBotonInsertarSetatributo(Boolean botonInsertarSetatributo) {
		this.botonInsertarSetatributo = botonInsertarSetatributo;
	}

	public Boolean getBotonActualizarSetatributo() {
		return botonActualizarSetatributo;
	}

	public void setBotonActualizarSetatributo(Boolean botonActualizarSetatributo) {
		this.botonActualizarSetatributo = botonActualizarSetatributo;
	}

	public Boolean getBotonEliminarSetatributo() {
		return botonEliminarSetatributo;
	}

	public void setBotonEliminarSetatributo(Boolean botonEliminarSetatributo) {
		this.botonEliminarSetatributo = botonEliminarSetatributo;
	}
	
	public int getIdSeriado() {
		return idSeriado;
	}

	public void setIdSeriado(int idSeriado) {
		this.idSeriado = idSeriado;
	}

	public Setatributo getEquipo() {
		return equipo;
	}

	public void setEquipo(Setatributo equipo) {
		this.equipo = equipo;
	}

	public List<Setatributo> getLstEquipos() {
		return lstEquipos;
	}

	public void setLstEquipos(List<Setatributo> lstEquipos) {
		this.lstEquipos = lstEquipos;
	}

	
	
	
	

}
