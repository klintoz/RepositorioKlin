package ec.gob.simintel.administracion.controladores;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.DefaultRequestContext;

import ec.gob.simintel.administracion.servicios.ServicioAtributosEquipo;
import ec.gob.simintel.controladores.commons.Constantes;
import ec.gob.simintel.controladores.commons.GeneradorMensajes;
import ec.gob.simintel.controladores.commons.MensajesError;
import ec.gob.simintel.controladores.commons.MensajesInformacion;
import ec.gob.simintel.entidades.Atributossetatrib;


@ManagedBean
@ViewScoped
public class ControladorAtributosEquipo {
	
	@EJB
	private ServicioAtributosEquipo srvAtribEquipo;
	private List<Atributossetatrib> lstAtribEquipos;
	private Atributossetatrib  atributoEquipo;
	
	/**
	 * botones
	 */
	private String nombreDeAtributoABuscar;
	private boolean activarBotonInsertar;
	private boolean activarBotonactualizar;
	
	private Boolean botonInsertarAtributoEquipo;
	private Boolean botonActualizarAtributoEquipo;
	private Boolean botonEliminarAtributoEquipo;
	
	/**
	 * combo obligatorio si/no 
	 */
	private int idObligatorio;
	
	@PostConstruct
	public  void inicio(){
		System.out.println("Ejecutando el Post");
		nuevoAtributoEquipo();
		cargarListaAtributosEquipos();
		
	}
	
	 public void pintarCombo(){
		 getObligatorio();
	 }
	
	public void nuevoAtributoEquipo(){
		atributoEquipo = new Atributossetatrib();
		habilitarGuardarAtributoEquipo();
		idObligatorio=0;
	}
	
	/**
	 * Estos metodos permiten activar y desactivar los botones de la barra del panel principal
	 */
	
	public void habilitarGuardarAtributoEquipo(){
		setBotonInsertarAtributoEquipo(false);
		setBotonActualizarAtributoEquipo(true);
		setBotonEliminarAtributoEquipo(true);
	}
	
	public void habilitarEditarAtributoequipo(){
		setBotonInsertarAtributoEquipo(true);
		setBotonActualizarAtributoEquipo(false);
		setBotonEliminarAtributoEquipo(false);
	    getObligatorio();//permite pintar el id del combo
	 
	}
	
	
	public void cargarListaAtributosEquipos(){
		lstAtribEquipos= srvAtribEquipo.buscarTodos();
	}
	
	public void abrirDialogoEliminarAtributos(){
		DefaultRequestContext.getCurrentInstance().execute("dlgEliminarAtributo.show()");	
			
	}
	
	public void abrirDialogoBuscarAtributos(){
		cargarListaAtributosEquipos();
		DefaultRequestContext.getCurrentInstance().execute("dlgBuscarAtributo.show()");
		
	}
	
	/**
	 * Setea el valor del seriado seleccionado en el combo 
	 * @return True = si lo escogio y se seteo, False = si no se escogio 
	 */
	
	public boolean setObligatorio(){
		boolean escogido= false;
		switch (idObligatorio) {
		case 1:
			atributoEquipo.setObligatorio(Constantes.OBLIGATORIOSI);
			escogido= true;
			break;

		case 2:
			atributoEquipo.setObligatorio(Constantes.OBLIGATORIONO);
			escogido= true;
			break;
		}
		return escogido;
	}
	
	/**
	 * Recupera el valor correspondiente se el atributo es obligatorio  (si/no)
	 */
	public void getObligatorio(){
	 idObligatorio=0;
	 System.out.println("entro al getObligatorio" + atributoEquipo.getObligatorio());
	 if (atributoEquipo.getObligatorio().compareTo(Constantes.OBLIGATORIOSI)==0){
		 idObligatorio=1;
		 
	 }else if ( atributoEquipo.getObligatorio().compareTo(Constantes.SERIADONO)==0){
		 idObligatorio=2;
	 }
	}
	
	/**
	 * Métodos Crud 
	 */
	
	
	/**
	 * Métod insertarAtributoEquipos permite insertar los atributos que van a tener los Equipos
	 */

	public void insertarAtributosEquipos(){
		System.out.println("Insertando");
		if(setObligatorio()){			
			srvAtribEquipo.insertar(atributoEquipo);
			nuevoAtributoEquipo();
			cargarListaAtributosEquipos();	
		}else{
			GeneradorMensajes.mostrarMensajeError(MensajesError.ATRIBUTO_OBLIGATORIO);
		}
		
	}
	
	
	/**
	 * Métod actualizarAtributoEquipos permite actualizar los atributos que van a tener los Equipos
	 */
	
	public void actualizarAtributoEquipos(){
		System.out.println("Actualizando....");
		setObligatorio();
		srvAtribEquipo.actualizar(atributoEquipo);
		nuevoAtributoEquipo();
	}
	
	public void eliminarAtributoEquipo(){
		System.out.println("Eliminando....");
		try {
			srvAtribEquipo.eliminar(atributoEquipo);
			nuevoAtributoEquipo();
			cargarListaAtributosEquipos();
			GeneradorMensajes.mostrarMensajeInformacion(MensajesInformacion.COMMONS_ELIMINACION_OK);
		} catch (Exception e) {
			e.printStackTrace();
			GeneradorMensajes.mostrarMensajeError(MensajesError.COMMONS_TIENE_REGISTRO_ASOCIADOS);
			
			// TODO: handle exception
		}
	}
	
	
	/**
	*	Busca  los atributos que puede tener los  Equipo ya sea de Conectividad o Equipamiento	
	*	
	 */
	
	public void buscarAtributoPorNombre(){
		System.out.println("imprimir" + nombreDeAtributoABuscar);
		if(nombreDeAtributoABuscar.toString().compareTo("")!=0){
			setLstAtribEquipos(srvAtribEquipo.buscarPorTextoDespliegue(nombreDeAtributoABuscar));
			if(lstAtribEquipos.size()>0){
				nombreDeAtributoABuscar=null;
				//habilitarEditarAtributoequipo();
			}
			else{
				GeneradorMensajes.mostrarMensajeInformacion(MensajesInformacion.COMMONS_NO_EXISTEN_REGISTROS_CON_PARAMETRO);
			}
		}else{
			GeneradorMensajes.mostrarMensajeError(MensajesError.COMMONS_INGRESAR_PARAMETRO_BUSQUEDA);
		}
		
	}
	
	
	
	
	/**
	 * creacion de los set y get para poder ser utilizados  los atributos en los Métodos
	 * @return
	 */
	
	public Atributossetatrib getAtributoEquipo() {
		return atributoEquipo;
	}


	public void setAtributoEquipo(Atributossetatrib atributoEquipo) {
		this.atributoEquipo = atributoEquipo;
	}


	public String getNombreDeAtributoABuscar() {
		return nombreDeAtributoABuscar;
	}


	public void setNombreDeAtributoABuscar(String nombreDeAtributoABuscar) {
		this.nombreDeAtributoABuscar = nombreDeAtributoABuscar;
	}


	public boolean isActivarBotonInsertar() {
		return activarBotonInsertar;
	}


	public void setActivarBotonInsertar(boolean activarBotonInsertar) {
		this.activarBotonInsertar = activarBotonInsertar;
	}


	public boolean isActivarBotonactualizar() {
		return activarBotonactualizar;
	}


	public void setActivarBotonactualizar(boolean activarBotonactualizar) {
		this.activarBotonactualizar = activarBotonactualizar;
	}


	public Boolean getBotonInsertarAtributoEquipo() {
		return botonInsertarAtributoEquipo;
	}


	public void setBotonInsertarAtributoEquipo(
			Boolean botonInsertarAtributoEquipo) {
		this.botonInsertarAtributoEquipo = botonInsertarAtributoEquipo;
	}


	public Boolean getBotonActualizarAtributoEquipo() {
		return botonActualizarAtributoEquipo;
	}


	public void setBotonActualizarAtributoEquipo(
			Boolean botonActualizarAtributoEquipo) {
		this.botonActualizarAtributoEquipo = botonActualizarAtributoEquipo;
	}


	public Boolean getBotonEliminarAtributoEquipo() {
		return botonEliminarAtributoEquipo;
	}


	public void setBotonEliminarAtributoEquipo(
			Boolean botonEliminarAtributoEquipo) {
		this.botonEliminarAtributoEquipo = botonEliminarAtributoEquipo;
	}


	public List<Atributossetatrib> getLstAtribEquipos() {
		return lstAtribEquipos;
	}


	public void setLstAtribEquipos(List<Atributossetatrib> lstAtribEquipos) {
		this.lstAtribEquipos = lstAtribEquipos;
	}


	public int getIdObligatorio() {
		return idObligatorio;
	}


	public void setIdObligatorio(int idObligatorio) {
		this.idObligatorio = idObligatorio;
	}



	
	

}
