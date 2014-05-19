package ec.gob.simintel.administracion.controladores;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.DefaultRequestContext;
import ec.gob.simintel.administracion.servicios.ServicioPais;
import ec.gob.simintel.administracion.servicios.ServicioProvincia;
import ec.gob.simintel.controladores.commons.GeneradorMensajes;
import ec.gob.simintel.controladores.commons.MensajesError;
import ec.gob.simintel.controladores.commons.MensajesInformacion;
import ec.gob.simintel.entidades.Institucion;
import ec.gob.simintel.entidades.Pai;
import ec.gob.simintel.entidades.Provincia;


@ManagedBean
@ViewScoped
public class ControladorProvincia {
	@EJB
	private ServicioProvincia  srvProvincia;
	private Provincia  provincia; 
	private String nombreProvinciaABuscar;
	private boolean activarBotonInsertar;
	private boolean activarBotonActualizar;
	
	private Institucion instituciones;
	private List<Provincia> lstProvincias;
	
	//BLOQUE Y DESBLOQUE O DE BOTONES PROVINCIA
	private Boolean botonInsertarProvincia;
	private Boolean botonActualizarProvincia;
	private Boolean botonEliminarProvincia;
		
   /*
    * Combos 
    */
	@EJB
	private ServicioPais srvPais;
	private List<Pai> lstPaises;
	private Integer idPais;
	
	
		
	@PostConstruct
	public void incio(){
		 nuevaProvincia();
		 cargarListaPaises();
	}
		
		
	public void nuevaProvincia(){
		provincia = new Provincia();
		habilitarGuardarProvincia();
	}
		
	private void habilitarGuardarProvincia(){
		botonInsertarProvincia = false;
		botonActualizarProvincia =true;
		botonEliminarProvincia = true;
	}
		
	public void habilitarEditarProvincia(){
		botonInsertarProvincia = true;
		botonActualizarProvincia = false;
		botonEliminarProvincia = false;
	}
		
	/**
	 * Carga las provincias  que seleccione en el combo pais 
	 */
	public void cargarListaProvincias(){
		lstProvincias = srvProvincia.buscarProvinciasPorPais(idPais);
	    nuevaProvincia();
	}
		
	public void cargarListaPaises(){
		lstPaises=srvPais.buscarTodos();
	}
	
	
		
	public void abrirDialogoEliminarProvincia(){
		
		DefaultRequestContext.getCurrentInstance().execute("dlgEliminarProvincia.show()");
			
	}		
		
		
		
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++METODOS CRUD PROVINCIAS++++++++++++++++++++++++++++++++++++++++++++++
		
	public void insertarProvincia(){
		
		if(!idPais.equals(0)){
			System.out.println("Insertando");
			provincia.setPai(srvPais.buscarPorId(idPais));
			srvProvincia.insertar(provincia);
			cargarListaProvincias();
			nuevaProvincia();	
			}else{
				GeneradorMensajes.mostrarMensajeError(MensajesError.PROVINCIA_PAIS_OBLIGATORIO);
			}
			
	}
		
	public void actualizarProvincia(){
			srvProvincia.actualizar(provincia);
			cargarListaProvincias();
			nuevaProvincia();	
					
	}

	public void eliminarProvincia(){
		try {
			srvProvincia.eliminar(provincia);
			setLstProvincias(srvProvincia.buscarTodos());
			nuevaProvincia();
			GeneradorMensajes.mostrarMensajeInformacion(MensajesInformacion.COMMONS_ELIMINACION_OK);
		} catch (Exception e) {
			e.printStackTrace();
			GeneradorMensajes.mostrarMensajeError(MensajesError.COMMONS_TIENE_REGISTRO_ASOCIADOS);
		}						
	}
		
		//**************************************************METODOS BUSQUEDA PROVINCIA***************************************************
	public void buscarProvincia(){
		System.out.println("imprimir" + nombreProvinciaABuscar);
			if((nombreProvinciaABuscar.toString()).compareTo("") != 0){
				setLstProvincias(srvProvincia.buscarProvinciaPorNombres(nombreProvinciaABuscar));
				if (getLstProvincias().size() > 0){
					nombreProvinciaABuscar=null;
						
			    }else{
						
				GeneradorMensajes.mostrarMensajeInformacion(MensajesInformacion.COMMONS_NO_EXISTEN_REGISTROS_CON_CARACTERES);	
				}
			}else{
				GeneradorMensajes.mostrarMensajeInformacion(MensajesError.COMMONS_INGRESAR_PARAMETRO_BUSQUEDA);
				}
				
	}
			
			//GETTS  Y SETTS

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public String getNombreProvinciaABuscar() {
		return nombreProvinciaABuscar;
	}

	public void setNombreProvinciaABuscar(String nombreProvinciaABuscar) {
		this.nombreProvinciaABuscar = nombreProvinciaABuscar;
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

	public Institucion getInstituciones() {
		return instituciones;
	}

	public void setInstituciones(Institucion instituciones) {
		this.instituciones = instituciones;
	}

	public Boolean getBotonInsertarProvincia() {
		return botonInsertarProvincia;
	}

	public void setBotonInsertarProvincia(Boolean botonInsertarProvincia) {
		this.botonInsertarProvincia = botonInsertarProvincia;
	}

	public Boolean getBotonActualizarProvincia() {
		return botonActualizarProvincia;
	}

	public void setBotonActualizarProvincia(Boolean botonActualizarProvincia) {
		this.botonActualizarProvincia = botonActualizarProvincia;
	}

	public Boolean getBotonEliminarProvincia() {
		return botonEliminarProvincia;
	}

	public void setBotonEliminarProvincia(Boolean botonEliminarProvincia) {
		this.botonEliminarProvincia = botonEliminarProvincia;
	}

	public List<Provincia> getLstProvincias() {
		return lstProvincias;
	}

	public void setLstProvincias(List<Provincia> lstProvincias) {
		this.lstProvincias = lstProvincias;
	}	

	public Integer getIdPais() {
		return idPais;
	}
	
	public void setIdPais(Integer idPais) {
		this.idPais = idPais;
	}

	public List<Pai> getLstPaises() {
		return lstPaises;
	}

	public void setLstPaises(List<Pai> lstPaises) {
		this.lstPaises = lstPaises;
	}


	


	
		
}
