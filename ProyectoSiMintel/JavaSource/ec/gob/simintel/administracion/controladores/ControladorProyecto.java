package ec.gob.simintel.administracion.controladores;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.DefaultRequestContext;

import ec.gob.simintel.administracion.servicios.ServicioPrograma;
import ec.gob.simintel.administracion.servicios.ServicioProyecto;
import ec.gob.simintel.administracion.servicios.ServicioTipoDocumento;
import ec.gob.simintel.commons.datamanager.GeneralDataManager;
import ec.gob.simintel.controladores.commons.GeneradorMensajes;
import ec.gob.simintel.controladores.commons.MensajesError;
import ec.gob.simintel.controladores.commons.MensajesInformacion;
import ec.gob.simintel.entidades.Proyecto;
import ec.gob.simintel.entidades.Tipodocumento;
import ec.gob.simintel.validadores.UtilRedireccion;


@ManagedBean
@SessionScoped
public class ControladorProyecto {
	
	@EJB
	private ServicioProyecto srvProyecto;
	//Necesitamos que sea DM para enviarle a la pagina convenios el objeto proyecto (fila seleccionada)
	private List<Proyecto> lstProyecto;
	private String nombreProyectoABuscar;
	private boolean activarBotonInsertar;
	private boolean activarBotonActualizar;
	
	/*
	 * Combos 
	 * 
	 */
	@EJB
	private ServicioPrograma srvPrograma;
	@ManagedProperty (value="#{generalDataManager}")
	private GeneralDataManager generalDM;
	//private Proyecto proyecto; borro el proyecto para guardarlo en sesion en mi generalDM y que pueda ser usado por otro controlador
	
	
	@EJB
	private ServicioTipoDocumento srvTipoDoc;
	private List<Tipodocumento> lstTipoDoc;
	private Integer idTipoDoc;
	
	
	//BLOQUE Y DESBLOQUE O DE BOTONES CONVENIOS
	private Boolean botonInsertarProyecto;
	private Boolean botonActualizarProyecto;
	private Boolean botonEliminarProyecto;
		
	@PostConstruct
	public void incio(){
		nuevoProyecto();
		cargarListaProgramas();	
		cargarListTipoDoc();
	}
	
	public void nuevoProyecto(){
		//proyecto = new Proyecto(); asigar osea =, es set
		generalDM.setProyecto(new Proyecto());
		habilitarGuardarProyecto();
		
	}
	
	private void habilitarGuardarProyecto(){
		botonInsertarProyecto = false;
		botonActualizarProyecto =true;
		botonEliminarProyecto = true;
	}

	public void habilitarEditarProyeto(){
		botonInsertarProyecto = true;
		botonActualizarProyecto = false;
		botonEliminarProyecto = false;
	}
	
	public void cargarListaProgramas(){
		getGeneralDM().setLstProgramas(srvPrograma.buscarTodos());
		
	}
	
	public void cargarListTipoDoc(){
		lstTipoDoc=srvTipoDoc.buscarTodos();
	}
	
	public void abrirDialogoEliminarProyecto(){
		
	DefaultRequestContext.getCurrentInstance().execute("dlgEliminarProyecto.show()");	
		
	}
	
	public void abrirDialogoBuscarProyectos(){
		generalDM.setLstProyectos(srvProyecto.buscarTodos());
		DefaultRequestContext.getCurrentInstance().execute("dlgbuscarProyecto.show()");
	}
	
	public void irConvenios(){
		generalDM.setIdProyecto(generalDM.getProyecto().getProyectoid());
		generalDM.cargarListaConvenios();
		UtilRedireccion.redireccionar("paginas/Convenios.jsf");
	}

		
//metodo recuperar proyecto
		
		
	public void recuperarProyecto(){
				
		generalDM.setProyecto(srvProyecto.buscarPorId(generalDM.getProyecto().getProyectoid()));	
		//proyecto=srvProyecto.buscarPorId(proyecto.getProyectoid()); (. es get)para traer el id del proyecto debo traer el proyecto de su nueva ubicacion osea generalDM
		habilitarEditarProyeto();
		
	}
		
	//public void cargarListaProyectos(){
    //  generalDM.setLstProyectos(srvProyecto.buscarProyectoPorPrograma(generalDM.getIdPrograma())); 
		       
	//}
	//METODOS CRUD PROYECTO
	
	public void insertarProyecto(){
		if(!generalDM.equals(0)){
			System.out.println("Insertando");
			generalDM.getProyecto().setPrograma(srvPrograma.buscarPorId(generalDM.getIdPrograma()));
			generalDM.getProyecto().setTipodocumentoid(idTipoDoc);
			srvProyecto.insertar(generalDM.getProyecto());
			generalDM.cargarListaProyectos();
			nuevoProyecto();
		}
		else{
			GeneradorMensajes.mostrarMensajeError(MensajesError.PROYECTO_PROGRAMA_OBLIGATORIO);
		}
				
	}
	public void cancelarCambiosProyecto(){
		generalDM.setProyecto(srvProyecto.buscarPorId(generalDM.getProyecto().getProyectoid()));
		
		
	}
	
	public void actualizarProyecto(){
			srvProyecto.actualizar(generalDM.getProyecto());
			generalDM.cargarListaProyectos();
			nuevoProyecto();
				
	}

	public void eliminarProyecto(){
		try {
			srvProyecto.eliminar(generalDM.getProyecto());
			lstProyecto=srvProyecto.buscarTodos();
			nuevoProyecto();
			GeneradorMensajes.mostrarMensajeInformacion(MensajesInformacion.COMMONS_ELIMINACION_OK);
		} catch (Exception e) {
			e.printStackTrace();
			GeneradorMensajes.mostrarMensajeError(MensajesError.COMMONS_TIENE_REGISTRO_ASOCIADOS);
		}
			
	}

	
	//*****************************************METODOS BUSQUEDA PROYECTO**********************************
	public void buscarProyecto(){
		System.out.println("imprimir" + nombreProyectoABuscar);
		if((nombreProyectoABuscar.toString()).compareTo("") != 0){
			lstProyecto =srvProyecto.buscarProyectosPorNombres(nombreProyectoABuscar);
			if (lstProyecto.size() > 0){
			    nombreProyectoABuscar=null;	
			}else{
					
				GeneradorMensajes.mostrarMensajeInformacion(MensajesInformacion.PARAMETRO_NO_EXISTE);	
				}
			}else{
				GeneradorMensajes.mostrarMensajeInformacion(MensajesError.PARAMETROS_BUSQUEDA);
			}
			
	}
	
	// GENERACION DE GET  Y SET	



	public String getNombreProyectoABuscar() {
		return nombreProyectoABuscar;
	}


	public void setNombreProyectoABuscar(String nombreProyectoABuscar) {
		this.nombreProyectoABuscar = nombreProyectoABuscar;
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


	public List<Proyecto> getLstProyecto() {
		return lstProyecto;
	}


	public void setLstProyecto(List<Proyecto> lstProyecto) {
		this.lstProyecto = lstProyecto;
	}


	public Boolean getBotonInsertarProyecto() {
		return botonInsertarProyecto;
	}


	public void setBotonInsertarProyecto(Boolean botonInsertarProyecto) {
		this.botonInsertarProyecto = botonInsertarProyecto;
	}


	public Boolean getBotonActualizarProyecto() {
		return botonActualizarProyecto;
	}


	public void setBotonActualizarProyecto(Boolean botonActualizarProyecto) {
		this.botonActualizarProyecto = botonActualizarProyecto;
	}


	public Boolean getBotonEliminarProyecto() {
		return botonEliminarProyecto;
	}


	public void setBotonEliminarProyecto(Boolean botonEliminarProyecto) {
		this.botonEliminarProyecto = botonEliminarProyecto;
	}

	public GeneralDataManager getGeneralDM() {
		return generalDM;
	}


	public void setGeneralDM(GeneralDataManager generalDM) {
		this.generalDM = generalDM;
	}

	public Integer getIdTipoDoc() {
		return idTipoDoc;
	}

	public void setIdTipoDoc(Integer idTipoDoc) {
		this.idTipoDoc = idTipoDoc;
	}

	public List<Tipodocumento> getLstTipoDoc() {
		return lstTipoDoc;
	}

	public void setLstTipoDoc(List<Tipodocumento> lstTipoDoc) {
		this.lstTipoDoc = lstTipoDoc;
	}

	



}
