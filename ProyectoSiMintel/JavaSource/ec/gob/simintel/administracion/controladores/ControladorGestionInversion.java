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
import ec.gob.simintel.administracion.servicios.ServicioGestionInversion;
import ec.gob.simintel.administracion.servicios.ServicioPlanInversion;
import ec.gob.simintel.administracion.servicios.ServicioSetAtributo;
import ec.gob.simintel.administracion.servicios.ServicioTipoInversion;
import ec.gob.simintel.commons.datamanager.GeneralDataManager;
import ec.gob.simintel.controladores.commons.GeneradorMensajes;
import ec.gob.simintel.controladores.commons.MensajesError;
import ec.gob.simintel.controladores.commons.MensajesInformacion;
import ec.gob.simintel.entidades.Convenio;
import ec.gob.simintel.entidades.Inversion;
import ec.gob.simintel.entidades.Planinversion;
import ec.gob.simintel.entidades.Setatributo;
import ec.gob.simintel.entidades.Tipoinversion;
import ec.gob.simintel.validadores.UtilRedireccion;


/**
 * Premite crear Inversiones que van estar ligados a los convenios y estas a su ves segun el tipo de inversion 
 * @author Klintozcano
 *
 */
@ManagedBean
@SessionScoped
public class ControladorGestionInversion {
	
	@EJB
	private ServicioGestionInversion srvInversion;
	//private Inversion inversion;
	//private List<Inversion> lstInversion;
	//private Integer idInversion; Pasan a ser objetos en sesion, es decir DataManager
	//private Integer idConvenio;
	
	@EJB
	private ServicioConvenio srvConvenio;
	@ManagedProperty (value="#{generalDataManager}")
	private GeneralDataManager generalDM;
	
	private List<Convenio> lstConvenios;
	
	
	@EJB
	private ServicioTipoInversion srvtipoInversion;
	private Tipoinversion tipoInversion;
	private List<Tipoinversion> lstTipoinversions;
	private Integer idTipoInversion;
	
	@EJB
	private ServicioSetAtributo srvEquipo;
	private Setatributo  equipos;
	private List<Setatributo> lstEquipos;
	private Integer idEquipo;
	
	/**
	 * botones
	 */
	
	private String nombreInversionABuscar;
	private boolean activarBotonInsertar;
	private boolean activarBotonActualizar;
	
	//BLOQUE Y DESBLOQUE O DE BOTONES INVERSION
	private Boolean botonInsertarInversion;
	private Boolean botonActualizarInversion;
	private Boolean botonEliminarInversion;
	
	//BLOQUE Y DESBLOQUE O DE BOTONES AGREGAR Y MODIFICAR INVERSION INGRESA
	private Boolean botonAgregarInversion;
	private Boolean botonModificarInversion;
		
	/**
	 * RECURSOS PARA DESARROLLLAR  EL PLAN DE INVERSION Y GENERAR EL DIALOGO  PARA INTERACTUAR CON EL INGRESO DE 
	 * DEL TIPO DE EQUIPO QUE VA A TENER EL  TIPO DE INVERSION
	 */
	
	@EJB
	private ServicioPlanInversion srvPlanInversion;
	private Planinversion planInversion;
	private List<Planinversion> lstPlanesInversion;
	
	private String nombrePlanInversionABuscar;
	
	/*Botones de operaciones CRUD*/
	private Boolean botonInsertarTipoInversion;
	private Boolean botonActualizarTipoInversion;
	private Boolean botonEliminarTipoInversion;
	
	private Boolean botonInsertarPlanInversion;
	private Boolean botonActualizarPlanInversion;
	private Boolean botonEliminarPlanInversion;

	@PostConstruct
	public void inicio(){
		nuevoInversion();
		cargarListaConvenios();
		cargarTipoInversion();
		//habilitarAgregarInversion();
		
	}
	
	
	
	public void nuevoInversion(){
		
		generalDM.setInversion(new Inversion());
		//inversion = new Inversion();
		habilitarGuardarInversion();
	}
	
	
	private void habilitarGuardarInversion(){
		botonInsertarInversion=false;
		botonActualizarInversion=true;
		botonEliminarInversion=true;
	}
	
	public void habilitarEditarInversion(){
		botonInsertarInversion = true;
		botonActualizarInversion = false;
		botonEliminarInversion = false;
	}
	
	public void cargarListaInversion(){
		generalDM.setLstInversiones(srvInversion.buscarInversionesPorConvenio(generalDM.getIdConvenio()));
		nuevoInversion();
		
	}
	
	public void  cargarListaConvenios(){
		lstConvenios = srvConvenio.buscarTodos();
		
	}
	public void cargarTipoInversion(){
		lstTipoinversions =srvtipoInversion.buscarTodos();
	}
	
	public void abrirDialogoEliminarInversion(){
	  DefaultRequestContext.getCurrentInstance().execute("dlgEliminarInversion.show()");	
	}
	
	public void abrirDialogoPlanInversion(){
		  DefaultRequestContext.getCurrentInstance().execute("dlgEliminarInversion.show()");	
		}
	
	public void recuperarInversion(){
		generalDM.setInversion(srvInversion.buscarPorId(generalDM.getInversion().getInversionid()));
		//inversion=srvInversion.buscarPorId(inversion.getInversionid());
		habilitarEditarInversion();	
	}
	//convenio
	
	private void nuevoConvenio(){
		generalDM.setConvenio(new Convenio());
		generalDM.setLstInversiones(new ArrayList<Inversion>());
		nuevoInversion();
		
	}
	public void actualizarConvenio(){
		generalDM.getConvenio().setInversions(generalDM.getLstInversiones());
		//convenio.setInversions(lstInversion);
		srvConvenio.actualizar(generalDM.getConvenio());
		//srvConvenio.actualizar(convenio);
	}
	
	
	//METODOS CRUD INVERSION
	
	public void insertarInversion(){
		if (!generalDM.getIdConvenio().equals(0)){
			System.out.println("Insertar .....");
			generalDM.getInversion().setConvenio(srvConvenio.buscarPorId(generalDM.getIdConvenio()));
			//inversion.setConvenio(srvConvenio.buscarPorId(idConvenio)));
			generalDM.getInversion().setTipoinversion(srvtipoInversion.buscarPorId(idTipoInversion));
			//inversion.setTipoinversion(srvtipoInversion.buscarPorId(idTipoInversion));
			srvInversion.insertar(generalDM.getInversion());
			cargarListaInversion();
			nuevoInversion();
		}else{
			GeneradorMensajes.mostrarMensajeError(MensajesError.INVERSION_CONVENIO_OBLIGATORIO);
		}
		
	}
	
	public void actualizarInversion(){
		generalDM.getInversion().setTipoinversion(srvtipoInversion.buscarPorId(idTipoInversion));
		//inversion.setTipoinversion(srvtipoInversion.buscarPorId(idTipoInversion));
		srvInversion.actualizar(generalDM.getInversion());
		cargarListaInversion();
		nuevoInversion();
		
	}
	
	public void eliminarInversion(){
		try {
			srvInversion.eliminar(generalDM.getInversion());
			cargarListaInversion();
			nuevoInversion();
			GeneradorMensajes.mostrarMensajeInformacion(MensajesInformacion.COMMONS_ELIMINACION_OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			GeneradorMensajes.mostrarMensajeError(MensajesError.COMMONS_TIENE_REGISTRO_ASOCIADOS);
		}
	}
	
	
	public void buscarInversion(){
		System.out.println("imprimir" + nombreInversionABuscar);
		if((nombreInversionABuscar.toString()).compareTo("") != 0){
			generalDM.setLstInversiones(srvInversion.buscarInversionPorDescripcion(nombreInversionABuscar));
			if (generalDM.getLstInversiones().size() > 0){
				nombreInversionABuscar=null;				
			}else{				
				GeneradorMensajes.mostrarMensajeInformacion(MensajesInformacion.PARAMETRO_NO_EXISTE);	
			}
		}else{
			GeneradorMensajes.mostrarMensajeInformacion(MensajesError.PARAMETROS_BUSQUEDA);
		}
		
	}
	
	
//	public void habilitarAgregarInversion(){
//		botonAgregarInversion=false;
//		botonModificarInversion=true;
//		
//	}
//	
//	public void habilitarModificarInversion(){
//		botonAgregarInversion=true;
//		botonModificarInversion=false;
//	}
//	
//	public void agregarInversion(){
//		if(!idTipoInversion.equals(0)){
//			inversion.setTipoinversion(tipoInversion);
//			inversion.setConvenio(convenio);
//			lstConvenios.add(convenio);
//			nuevoInversion();
//			habilitarModificarInversion();
//		}
//
//	}
	
//	//public void modificarInversion(){
//		inversion.setTipoinversion(tipoInversion);
//		inversion.setDescripcion(inversion.getDescripcion());
//		srvInversion.actualizar(inversion);
//		nuevoInversion();
//		habilitarAgregarInversion();
//			
//	
//	}
	
	
	 
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	//PROGRAMANDO PLAN DE INVERSION SEGUN EL TIPO DE INVERSION 
	//
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	public void nuevoInversionPlanInversion(){
		generalDM.setInversion(new Inversion());
		//inversion= new Inversion();
		lstPlanesInversion = new ArrayList<Planinversion>();///
		nuevoPlanInversion();///
		nuevoInversionPlanInversion();
		habilitarGuardarInversion();
	}
	
	public void nuevoPlanInversion(){
		planInversion = new Planinversion();
	}
		
	public void habilitarAgregarPlanInversion(){
		botonInsertarPlanInversion=false;
		botonActualizarPlanInversion=true;
		
	}
	
	public void habilitarModificarPlanInversion(){
		System.out.println("Entro a modificar planInversion");
		botonInsertarPlanInversion=true;
		botonActualizarPlanInversion=false;
		
	}
	/**
	 * Método que me permite cargar la lista Tipo de Inversion 
	 * @return
	 * lista de Tipo de Inversion
	 */
	
	
	public void cargarListaPlanInversion(){
		lstPlanesInversion=srvPlanInversion.buscarPlanInverPorInversion(generalDM.getInversion().getInversionid());
		//lstPlanesInversion=srvPlanInversion.buscarPlanInverPorInversion(inversion.getInversionid());
		
	}
	
	public void cargarListaInversionPlanInversion(){
		generalDM.setLstInversiones(srvInversion.buscarTodos());		
	}
	
	public void cargarListaEquiposPlanInversion(){
		lstEquipos = srvEquipo.buscarTodos();
		
	}
	
	public void  abrirDialogoBuscarInversionPlanInversion(){
		DefaultRequestContext.getCurrentInstance().execute("dlgInversionPlanInversion.show()");

	}
	public void abrirDialogoEliminarPlanInversion(){	
		DefaultRequestContext.getCurrentInstance().execute("dlgEliminarPlanInversion.show()");
	}
	
	public void agregarPlanInversion(){
		planInversion.setInversion(generalDM.getInversion());
		//planInversion.setInversion(inversion);
		planInversion.setIndice(lstPlanesInversion.size());
		lstPlanesInversion.add(planInversion);
		nuevoPlanInversion();
	}
	
	 public void actualizarPlanInversion(){
		 for(Planinversion pi:lstPlanesInversion){
			 if(planInversion.getIndice()== pi.getIndice()){
				 pi.setDescriptivo(planInversion.getDescriptivo());
				 pi.setPrecio(planInversion.getPrecio());
				 pi.setDetallecantidad(planInversion.getDetallecantidad());
				 break;
				 
			 }
			 
		 }
		 nuevoPlanInversion();
		 habilitarEditarInversion();
		
	}
	 
	 public void insertarInversionPlanInversion(){
		 System.out.println("Insertando inversionPlanInvesion...");
		 	generalDM.getInversion().setPlaninversions(lstPlanesInversion);
	 		//inversion.setPlaninversions(lstPlanesInversion);//CON CascadeType.PERSIST AUTOMATICAMENTE INSERTA LOS Plan Inversion
	 		srvInversion.insertar(generalDM.getInversion());
	 		nuevoInversion();		
	 }
	 
	 public void actualizarInversionPlanInversion(){
		 generalDM.getInversion().setPlaninversions(lstPlanesInversion);
		 //inversion.setPlaninversions(lstPlanesInversion);
		 srvInversion.actualizar(generalDM.getInversion());
		 //srvInversion.actualizar(inversion);//CON CascadeType.PERSIST AUTOMATICAMENTE INSERTA LOS Plan Inversion
	 }
	 
	 
	 public void eliminarInversionPlanInversion(){
		 try {
			 srvInversion.eliminar(generalDM.getInversion());
			 //srvInversion.eliminar(inversion);
			 nuevoInversion();
			 GeneradorMensajes.mostrarMensajeInformacion(MensajesInformacion.COMMONS_ELIMINACION_OK);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			GeneradorMensajes.mostrarMensajeInformacion(MensajesError.COMMONS_TIENE_REGISTRO_ASOCIADOS);
		}
	 }
	 
	 
	 public void irPlaninversio(){
			
			UtilRedireccion.redireccionar("paginas/PlanInversion.jsf");
		}
	
	

	
	public List<Tipoinversion> getLstTipoinversions() {
		return lstTipoinversions;
	}
	public void setLstTipoinversions(List<Tipoinversion> lstTipoinversions) {
		this.lstTipoinversions = lstTipoinversions;
	}
	public Integer getIdTipoInversion() {
		return idTipoInversion;
	}
	public void setIdTipoInversion(Integer idTipoInversion) {
		this.idTipoInversion = idTipoInversion;
	}
	public String getNombreInversionABuscar() {
		return nombreInversionABuscar;
	}
	public void setNombreInversionABuscar(String nombreInversionABuscar) {
		this.nombreInversionABuscar = nombreInversionABuscar;
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

	public Boolean getBotonInsertarInversion() {
		return botonInsertarInversion;
	}

	public void setBotonInsertarInversion(Boolean botonInsertarInversion) {
		this.botonInsertarInversion = botonInsertarInversion;
	}

	public Boolean getBotonActualizarInversion() {
		return botonActualizarInversion;
	}

	public void setBotonActualizarInversion(Boolean botonActualizarInversion) {
		this.botonActualizarInversion = botonActualizarInversion;
	}

	public Boolean getBotonEliminarInversion() {
		return botonEliminarInversion;
	}

	public void setBotonEliminarInversion(Boolean botonEliminarInversion) {
		this.botonEliminarInversion = botonEliminarInversion;
	}

	public List<Convenio> getLstConvenios() {
		return lstConvenios;
	}

	public void setLstConvenios(List<Convenio> lstConvenios) {
		this.lstConvenios = lstConvenios;
	}

//	public Convenio getConvenio() {
//		return convenio;
//	}
//
//	public void setConvenio(Convenio convenio) {
//		this.convenio = convenio;
//	}

	public Tipoinversion getTipoInversion() {
		return tipoInversion;
	}

	public void setTipoInversion(Tipoinversion tipoInversion) {
		this.tipoInversion = tipoInversion;
	}

//	public Inversion getInversion() {
//		return inversion;
//	}
//
//	public void setInversion(Inversion inversion) {
//		this.inversion = inversion;
//	}

	public Planinversion getPlanInversion() {
		return planInversion;
	}

	public void setPlanInversion(Planinversion planInversion) {
		this.planInversion = planInversion;
	}

	public List<Planinversion> getLstPlanesInversion() {
		return lstPlanesInversion;
	}

	public void setLstPlanesInversion(List<Planinversion> lstPlanesInversion) {
		this.lstPlanesInversion = lstPlanesInversion;
	}


	public String getNombrePlanInversionABuscar() {
		return nombrePlanInversionABuscar;
	}

	public void setNombrePlanInversionABuscar(String nombrePlanInversionABuscar) {
		this.nombrePlanInversionABuscar = nombrePlanInversionABuscar;
	}

	public Boolean getBotonInsertarTipoInversion() {
		return botonInsertarTipoInversion;
	}

	public void setBotonInsertarTipoInversion(Boolean botonInsertarTipoInversion) {
		this.botonInsertarTipoInversion = botonInsertarTipoInversion;
	}

	public Boolean getBotonActualizarTipoInversion() {
		return botonActualizarTipoInversion;
	}

	public void setBotonActualizarTipoInversion(
			Boolean botonActualizarTipoInversion) {
		this.botonActualizarTipoInversion = botonActualizarTipoInversion;
	}

	public Boolean getBotonEliminarTipoInversion() {
		return botonEliminarTipoInversion;
	}

	public void setBotonEliminarTipoInversion(Boolean botonEliminarTipoInversion) {
		this.botonEliminarTipoInversion = botonEliminarTipoInversion;
	}

	public Boolean getBotonInsertarPlanInversion() {
		return botonInsertarPlanInversion;
	}

	public void setBotonInsertarPlanInversion(Boolean botonInsertarPlanInversion) {
		this.botonInsertarPlanInversion = botonInsertarPlanInversion;
	}

	public Boolean getBotonActualizarPlanInversion() {
		return botonActualizarPlanInversion;
	}

	public void setBotonActualizarPlanInversion(
			Boolean botonActualizarPlanInversion) {
		this.botonActualizarPlanInversion = botonActualizarPlanInversion;
	}

	public Boolean getBotonEliminarPlanInversion() {
		return botonEliminarPlanInversion;
	}

	public void setBotonEliminarPlanInversion(Boolean botonEliminarPlanInversion) {
		this.botonEliminarPlanInversion = botonEliminarPlanInversion;
	}



	public Setatributo getEquipos() {
		return equipos;
	}



	public void setEquipos(Setatributo equipos) {
		this.equipos = equipos;
	}


	public Integer getIdEquipo() {
		return idEquipo;
	}



	public void setIdEquipo(Integer idEquipo) {
		this.idEquipo = idEquipo;
	}



	public List<Setatributo> getLstEquipos() {
		return lstEquipos;
	}


	public void setLstEquipos(List<Setatributo> lstEquipos) {
		this.lstEquipos = lstEquipos;
	}



	public Boolean getBotonAgregarInversion() {
		return botonAgregarInversion;
	}



	public void setBotonAgregarInversion(Boolean botonAgregarInversion) {
		this.botonAgregarInversion = botonAgregarInversion;
	}



	public Boolean getBotonModificarInversion() {
		return botonModificarInversion;
	}

	public void setBotonModificarInversion(Boolean botonModificarInversion) {
		this.botonModificarInversion = botonModificarInversion;
	}



	public GeneralDataManager getGeneralDM() {
		return generalDM;
	}



	public void setGeneralDM(GeneralDataManager generalDM) {
		this.generalDM = generalDM;
	}
	
	
	

}
