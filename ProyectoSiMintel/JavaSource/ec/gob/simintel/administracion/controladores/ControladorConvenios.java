package ec.gob.simintel.administracion.controladores;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.DefaultRequestContext;

import ec.gob.simintel.administracion.servicios.ServicioConvenio;
import ec.gob.simintel.administracion.servicios.ServicioEstado;
import ec.gob.simintel.administracion.servicios.ServicioPrograma;
import ec.gob.simintel.administracion.servicios.ServicioProveedor;
import ec.gob.simintel.administracion.servicios.ServicioProyecto;
import ec.gob.simintel.administracion.servicios.ServicioTipoDocumento;
import ec.gob.simintel.commons.datamanager.GeneralDataManager;
import ec.gob.simintel.controladores.commons.GeneradorMensajes;
import ec.gob.simintel.controladores.commons.MensajesError;
import ec.gob.simintel.controladores.commons.MensajesInformacion;
import ec.gob.simintel.entidades.Convenio;
import ec.gob.simintel.entidades.Estado;
import ec.gob.simintel.entidades.Persona;
import ec.gob.simintel.entidades.Programa;
import ec.gob.simintel.entidades.Proveedor;
import ec.gob.simintel.entidades.Proyecto;
import ec.gob.simintel.entidades.Tipodocumento;
import ec.gob.simintel.seguridad.servicios.ServicioPersona;
import ec.gob.simintel.validadores.UtilRedireccion;


/**Permite crear convenios y ligar con instittuciones beneficiadas 
 * 
 * @author Klintozcano
 *
 */

@ManagedBean
@SessionScoped
public class ControladorConvenios {
	
	
	@EJB
	private ServicioConvenio srvConvenio;
	//Necesitamos que sea DM para enviarle a la pagina gestionINversion el objeto Convenio (fila seleccionada)
	//private Convenio  convenio;
	//private List<Convenio> lstConvenios;
	private String nombreConvenioABuscar;
	private boolean activarBotonInsertar;
	private boolean activarBotonActualizar;
	
	
	//BLOQUE Y DESBLOQUE O DE BOTONES CONVENIOS
	private Boolean botonInsertarConvenios;
	private Boolean botonActualizarConvenios;
	private Boolean botonEliminarConvenios;
	
	/*
	 * COMBOS//CARGAR COMBO DE CONVENIOS 
	 */
	
	@EJB
	private ServicioProyecto srvProyecto;
	@ManagedProperty (value="#{generalDataManager}")
	private GeneralDataManager generalDM;	
	//private Proyecto proyecto; borro para obtener el proyecto en sesion que lo guardo en el generalDM
	private List<Proyecto> lstProyectos;
	
	@EJB
	private ServicioPrograma srvPrograma;
	private List<Programa> lstPrograma;
	private Integer  idPrograma;
	
	@EJB
	private ServicioProveedor srvProveedor;
	private List<Proveedor> lstProveedores;
	private Integer idProveedor;
	
	@EJB
	private ServicioTipoDocumento SrvTipoDoc;
	private Tipodocumento tipodocumento;
	private List<Tipodocumento> lstTiposDocs;
	private Integer idTipoDoc;

	@EJB
	private ServicioEstado srvEstado;
	private List<Estado> lstEstados;
	private Integer idEstado;
	
	@EJB
	public ServicioPersona srvPersona;
	private List<Persona> lstPersona;
	private Integer idPersona;
	
	
	@PostConstruct
	public void incio(){
		nuevoConvenio();
		cargarListaProyectos();
		cargarListaProveedores();
		cargarListaAdministradores();
		cargarListaTipoDoc();
		cargarListaEstados();
		
	} 
	
	public void nuevoConvenio(){
		generalDM.setConvenio(new Convenio());
		//convenio = new Convenio();
		habilitarGuardarConvenio();
	}
	
	private void habilitarGuardarConvenio(){
		botonInsertarConvenios = false;
		botonActualizarConvenios =true;
		botonEliminarConvenios = true;
	}
	
	public void habilitarEditarConvenio(){
		botonInsertarConvenios = true;
		botonActualizarConvenios = false;
		botonEliminarConvenios = false;
	}
	
	public void cargarListaConvenios(){
		generalDM.setLstConvenios(srvConvenio.buscarConvenioPorProyecto(generalDM.getIdProyecto()));
		nuevoConvenio();
	       
	}
	public void cargarListaProyectos(){
		lstProyectos =srvProyecto.buscarTodos();
		
	}
	
	public void cargarListaProveedores(){
		lstProveedores = srvProveedor.buscarTodos();
	}
	
	public void cargarListaAdministradores(){
		lstPersona = srvPersona.buscarTodos();
	
	}
	
	public void cargarListaTipoDoc(){
		lstTiposDocs =SrvTipoDoc.buscarTodos();
	}
	
	public void cargarListaEstados(){
		lstEstados = srvEstado.buscarTodos();
	}
	

	public void abrirDialogoEliminarConvenio(){
	   DefaultRequestContext.getCurrentInstance().execute("dlgEliminarConvenio.show()");	
	}

   //metodo recuperar convenio
		
	public void recuperarConvenio(){
		generalDM.setConvenio(srvConvenio.buscarPorId(generalDM.getConvenio().getConvenioid()));
		//convenio=srvConvenio.buscarPorId(convenio.getConvenioid());	
		habilitarEditarConvenio();	
	}
	
	public void agregarConvenio(){
		if(!idPersona.equals(0)){
			if(!idProveedor.equals(0)){
				if(!idTipoDoc.equals(0)){
					if(!idEstado.equals(0)){
						generalDM.getConvenio().setPersona(srvPersona.buscarPorId(idPersona));
						//convenio.setPersona(srvPersona.buscarPorId(idPersona));
						generalDM.getConvenio().setProveedor(srvProveedor.buscarPorId(idProveedor));
						//convenio.setProveedor(srvProveedor.buscarPorId(idProveedor));
						generalDM.getConvenio().setEstado(srvEstado.buscarPorId(idEstado));
						//convenio.setEstado(srvEstado.buscarPorId(idEstado));
						generalDM.getConvenio().setTipodocumentoid(idTipoDoc);
						//convenio.setTipodocumentoid(idTipoDoc);
						generalDM.getConvenio().setProyecto(getGeneralDM().getProyecto());
						generalDM.getConvenio().setIndice(generalDM.getLstConvenios().size());
						//convenio.setIndice(lstConvenios.size());
						generalDM.getLstConvenios().add(generalDM.getConvenio());
						//lstConvenios.add(convenio);
						nuevoConvenio();
					}
					
				}
				
			}
						
		}
		
	}
	
	public void nuevoProyecto(){
		getGeneralDM().setProyecto(new Proyecto());
		generalDM.setLstConvenios(new ArrayList<Convenio>());
		nuevoConvenio();
		
	}
	
	public void actualizarProyecto(){
		getGeneralDM().getProyecto().setConvenios(generalDM.getLstConvenios());
		srvProyecto.actualizar(getGeneralDM().getProyecto());
	}
	
	//METODOS CRUD CONVENIOS
	public void insertarConvenios(){
		if(!generalDM.equals(0)){
			if(!generalDM.getIdProyecto().equals(0)){
				if(!idProveedor.equals(0)){
					if(!idPersona.equals(0)){
						if(!idTipoDoc.equals(0)){
							if(!idEstado.equals(0)){
								System.out.println("Insertando");
								generalDM.getConvenio().setProyecto(srvProyecto.buscarPorId(generalDM.getIdProyecto()));
								//convenio.setProyecto(srvProyecto.buscarPorId(idProyecto));
								generalDM.getConvenio().setPersona(srvPersona.buscarPorId(idPersona));
								//convenio.setPersona(srvPersona.buscarPorId(idPersona));
								generalDM.getConvenio().setEstado(srvEstado.buscarPorId(idEstado));
								//convenio.setEstado(srvEstado.buscarPorId(idEstado));
								generalDM.getConvenio().setProveedor(srvProveedor.buscarPorId(generalDM.getIdProyecto()));
								//convenio.setProveedor(srvProveedor.buscarPorId(idProyecto));
								generalDM.getConvenio().setTipodocumentoid(idTipoDoc);
								//convenio.setTipodocumentoid(idTipoDoc);
								srvConvenio.insertar(generalDM.getConvenio());
								cargarListaConvenios();
								nuevoConvenio();
							
							}else {
							GeneradorMensajes.mostrarMensajeError(MensajesError.CONVENIO_ESTADO_OBLIGATORIO);
							}
						}
						else {
							GeneradorMensajes.mostrarMensajeError(MensajesError.CONVENIO_TIPO_DOCUMENTO_OBLIGATORIO);
						}
					
					}else {
						GeneradorMensajes.mostrarMensajeError(MensajesError.CONVENIO_ADMINISTRADOR_OBLIGATORIO);
					}
				}else {
					GeneradorMensajes.mostrarMensajeError(MensajesError.CONVENIO_PROVEEDOR_OBLIGATORIO);
				}
			}else{
				GeneradorMensajes.mostrarMensajeError(MensajesError.CONVENIO_PROYECTO_OBLIGATORIO);
			}
			
		}else{
			GeneradorMensajes.mostrarMensajeError(MensajesError.INVERSION_CONVENIO_OBLIGATORIO);
		}
		
	}
	
	public void cancelarCambiosConvenios(){
		//convenio=srvConvenio.buscarPorId(convenio.getConvenioid());
		generalDM.setConvenio(srvConvenio.buscarPorId(generalDM.getConvenio().getConvenioid()));
	}
	
	public void actualizarConvenio(){
		if(!generalDM.getIdProyecto().equals(0)){
			if(!idProveedor.equals(0)){
				if(!idPersona.equals(0)){
					if(!idTipoDoc.equals(0)){
						if(!idEstado.equals(0)){
							generalDM.getConvenio().setProveedor(srvProveedor.buscarPorId(idProveedor));
							//convenio.setProveedor(srvProveedor.buscarPorId(idProveedor));
							generalDM.getConvenio().setPersona(srvPersona.buscarPorId(idPersona));
							//convenio.setPersona(srvPersona.buscarPorId(idPersona));
							generalDM.getConvenio().setTipodocumentoid(idTipoDoc);
							//convenio.setTipodocumentoid(idTipoDoc);///ver si funciona 
							generalDM.getConvenio().setEstado(srvEstado.buscarPorId(idEstado));
							//convenio.setEstado(srvEstado.buscarPorId(idEstado));
							srvConvenio.actualizar(generalDM.getConvenio());
							//srvConvenio.actualizar(convenio);
							cargarListaConvenios();
							nuevoConvenio();
						}else {
						GeneradorMensajes.mostrarMensajeError(MensajesError.CONVENIO_ESTADO_OBLIGATORIO);
						}
					}
					else {
					GeneradorMensajes.mostrarMensajeError(MensajesError.CONVENIO_TIPO_DOCUMENTO_OBLIGATORIO);
					}
				}else {
					GeneradorMensajes.mostrarMensajeError(MensajesError.CONVENIO_ADMINISTRADOR_OBLIGATORIO);
				}
			
			}else {
				GeneradorMensajes.mostrarMensajeError(MensajesError.CONVENIO_PROVEEDOR_OBLIGATORIO);
			}
		}else {
			GeneradorMensajes.mostrarMensajeError(MensajesError.CONVENIO_PROYECTO_OBLIGATORIO);
		}
	
	}

	public void eliminarConvenio(){
		try {
			srvConvenio.eliminar(generalDM.getConvenio());
			//srvConvenio.eliminar(convenio);
			generalDM.setLstConvenios(srvConvenio.buscarTodos());
			nuevoConvenio();
			GeneradorMensajes.mostrarMensajeInformacion(MensajesInformacion.COMMONS_ELIMINACION_OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			GeneradorMensajes.mostrarMensajeError(MensajesError.COMMONS_TIENE_REGISTRO_ASOCIADOS);
		}
				
					
	}
	
	//****************************************************METODOS BUSQUEDA CONVENIO******************************************************
		public void buscarConvenio(){
			System.out.println("imprimir" + nombreConvenioABuscar);
			if((nombreConvenioABuscar.toString()).compareTo("") != 0){
				generalDM.setLstConvenios(srvConvenio.buscarConvenioPorNombres(nombreConvenioABuscar));
				if (generalDM.getLstConvenios().size() > 0){
					nombreConvenioABuscar=null;
					
				}else{
					
					GeneradorMensajes.mostrarMensajeInformacion(MensajesInformacion.PARAMETRO_NO_EXISTE);	
				}
			}else{
				GeneradorMensajes.mostrarMensajeInformacion(MensajesError.PARAMETROS_BUSQUEDA);
			}
		}
		public void irInversion(){
			generalDM.setIdConvenio(generalDM.getConvenio().getConvenioid());//poniendo el id del combo de la siguiente pagina con el valor del id de la fila convenio que seleccione
			generalDM.cargarListaInversion();
			UtilRedireccion.redireccionar("paginas/GestionInversion.jsf");
		}
		
		 
	//******************** get y set ****************************

		
	public boolean isActivarBotonInsertar() {
		return activarBotonInsertar;
	}
//	public Convenio getConvenio() {
//		return convenio;
//	}
//
//	public void setConvenio(Convenio convenio) {
//		this.convenio = convenio;
//	}

	public String getNombreConvenioABuscar() {
		return nombreConvenioABuscar;
	}

	public void setNombreConvenioABuscar(String nombreConvenioABuscar) {
		this.nombreConvenioABuscar = nombreConvenioABuscar;
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

	public Boolean getBotonInsertarConvenios() {
		return botonInsertarConvenios;
	}
	public void setBotonInsertarConvenios(Boolean botonInsertarConvenios) {
		this.botonInsertarConvenios = botonInsertarConvenios;
	}
	public Boolean getBotonActualizarConvenios() {
		return botonActualizarConvenios;
	}
	public void setBotonActualizarConvenios(Boolean botonActualizarConvenios) {
		this.botonActualizarConvenios = botonActualizarConvenios;
	}
	public Boolean getBotonEliminarConvenios() {
		return botonEliminarConvenios;
	}
	public void setBotonEliminarConvenios(Boolean botonEliminarConvenios) {
		this.botonEliminarConvenios = botonEliminarConvenios;
	}

	public List<Proyecto> getLstProyectos() {
		return lstProyectos;
	}

	public void setLstProyectos(List<Proyecto> lstProyectos) {
		this.lstProyectos = lstProyectos;
	}


	public List<Programa> getLstPrograma() {
		return lstPrograma;
	}

	public void setLstPrograma(List<Programa> lstPrograma) {
		this.lstPrograma = lstPrograma;
	}

	public Integer getIdPrograma() {
		return idPrograma;
	}

	public void setIdPrograma(Integer idPrograma) {
		this.idPrograma = idPrograma;
	}

	public List<Proveedor> getLstProveedores() {
		return lstProveedores;
	}

	public void setLstProveedores(List<Proveedor> lstProveedores) {
		this.lstProveedores = lstProveedores;
	}

	public Integer getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(Integer idProveedor) {
		this.idProveedor = idProveedor;
	}

	public List<Tipodocumento> getLstTiposDocs() {
		return lstTiposDocs;
	}

	public void setLstTiposDocs(List<Tipodocumento> lstTiposDocs) {
		this.lstTiposDocs = lstTiposDocs;
	}

	public Integer getIdTipoDoc() {
		return idTipoDoc;
	}

	public void setIdTipoDoc(Integer idTipoDoc) {
		this.idTipoDoc = idTipoDoc;
	}

	public List<Estado> getLstEstados() {
		return lstEstados;
	}

	public void setLstEstados(List<Estado> lstEstados) {
		this.lstEstados = lstEstados;
	}

	public Integer getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}

	public List<Persona> getLstPersona() {
		return lstPersona;
	}

	public void setLstPersona(List<Persona> lstPersona) {
		this.lstPersona = lstPersona;
	}

	public Integer getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}

	public Tipodocumento getTipodocumento() {
		return tipodocumento;
	}

	public void setTipodocumento(Tipodocumento tipodocumento) {
		this.tipodocumento = tipodocumento;
	}

	public GeneralDataManager getGeneralDM() {
		return generalDM;
	}

	public void setGeneralDM(GeneralDataManager generalDM) {
		this.generalDM = generalDM;
	}

	
}
