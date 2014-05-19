package ec.gob.simintel.busqueda.controlador;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.DefaultRequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import ec.gob.simintel.administracion.servicios.ServicioCanton;
import ec.gob.simintel.administracion.servicios.ServicioConvenio;
import ec.gob.simintel.administracion.servicios.ServicioEstado;
import ec.gob.simintel.administracion.servicios.ServicioGestionInversion;
import ec.gob.simintel.administracion.servicios.ServicioInstBeneficiada;
import ec.gob.simintel.administracion.servicios.ServicioInstituciones;
import ec.gob.simintel.administracion.servicios.ServicioPais;
import ec.gob.simintel.administracion.servicios.ServicioParroquia;
import ec.gob.simintel.administracion.servicios.ServicioPlan;
import ec.gob.simintel.administracion.servicios.ServicioPrograma;
import ec.gob.simintel.administracion.servicios.ServicioProvincia;
import ec.gob.simintel.administracion.servicios.ServicioProyecto;
import ec.gob.simintel.administracion.servicios.ServicioTipoInstitucion;
import ec.gob.simintel.administracion.servicios.ServicioTipoSostenimiento;
import ec.gob.simintel.commons.datamanager.InstitucionDataModel;
//import ec.gob.simintel.controladores.commons.Constantes;
import ec.gob.simintel.controladores.commons.GeneradorMensajes;
import ec.gob.simintel.controladores.commons.MensajesError;
import ec.gob.simintel.controladores.commons.MensajesInformacion;
import ec.gob.simintel.entidades.Canton;
import ec.gob.simintel.entidades.Convenio;
import ec.gob.simintel.entidades.Estado;
import ec.gob.simintel.entidades.Instbeneficiada;
import ec.gob.simintel.entidades.Institucion;
import ec.gob.simintel.entidades.Inversion;
import ec.gob.simintel.entidades.Pai;
import ec.gob.simintel.entidades.Parroquia;
import ec.gob.simintel.entidades.Plan;
import ec.gob.simintel.entidades.Programa;
import ec.gob.simintel.entidades.Provincia;
import ec.gob.simintel.entidades.Proyecto;
import ec.gob.simintel.entidades.Tipoinstitucion;
import ec.gob.simintel.entidades.Tiposostenimiento;


@ManagedBean
@ViewScoped
public class ControladorBuscarInstituciones {
	
	
	/*Busquedas*/
	private String nombreABuscar;
	private String identificacionABuscar;
	@EJB
	private ServicioInstituciones  srvInstitucion;
	private List<Institucion> lstInstituciones;
	private Institucion institucion;
	private InstitucionDataModel institucionDataModel;
	private int numeroInstituciones;
	
	/*Botones de operaciones CRUD*/
	/**private boolean botonInsertarInstitucion;
	private boolean botonActualizarInstitucion;
	private boolean botonEliminarInstitucion;**/
	
	/*Combos*/
	@EJB
	private ServicioPais srvPais;
	private List<Pai> lstPaises;
	private Integer idPais;
	@EJB
	private ServicioProvincia srvProvincia;
	private List<Provincia> lstProvincias;
	private Integer idProvincia;
	@EJB
	private ServicioCanton srvCanton;
	private List<Canton> lstCatones;
	private Integer idCanton;
	@EJB
	private ServicioParroquia srvParroquia;
	private List<Parroquia> lstParroquias;
	private Integer idParroquia;
	private int idMeridiano;
	
	@EJB
	private ServicioPlan srvPlan;
	private List<Plan> lstPlanes;
	private Integer idPlan;
	
	@EJB 
	private ServicioPrograma srvPrograma;
	private List<Programa> lstProgramas;
	private Integer idPrograma;
	
	@EJB
	private ServicioProyecto srvProyecto;
	private List<Proyecto> lstProyectos;
	private Integer idProyecto;
	
	@EJB
	private ServicioConvenio srvConvenio;
	private List<Convenio> lstConvenios;
	private Integer idConvenio;
	
	@EJB
	private ServicioGestionInversion srvInversion;
	private List<Inversion> lstInversiones;
	private Integer idInversion;
	
	@EJB
	private ServicioEstado srvEstado;
	private List<Estado> lstEstado;
	private Integer idEstado;
	
	@EJB
	private ServicioTipoInstitucion srvTipoInstitucion;
	private List<Tipoinstitucion> lstTipoinstituciones;
	private Integer idTipoInstitucion;
	
	@EJB
	private ServicioTipoSostenimiento srvTipoSostenimiento;
	private List<Tiposostenimiento> lstTipoSostenimientos;
	private Integer idTipoSostenimiento;
	
	@EJB
	private ServicioInstBeneficiada srvInstBeneficiada;
	private List<Instbeneficiada> lstInstBeneficiadas;
	private Integer idInstBeneficiada;
	private Instbeneficiada instBeneficiada;
	

	@PostConstruct
	public void inicio(){
		System.out.println("Ejecuntado post");
		cargarListaInstituciones();
		setInstitucionDataModel(new InstitucionDataModel(lstInstituciones));
		
	}
	
	
	public void nuevaInstitucion(){
		institucion = new Institucion();
		
	}

	/**
	 * Carga las todas las instituciones y setea los atributos @Transient canton y provincia 
	 */
	public void cargarListaInstituciones(){
		lstInstituciones=srvInstitucion.buscarTodos();
		for(Institucion i : lstInstituciones){
			i.setCanton(i.getParroquia().getCanton().getNombre());
			i.setProvincia(i.getParroquia().getCanton().getProvincia().getNombre());
		}
	}
	public void abirDialogoEliminarInstitucion(){
		DefaultRequestContext.getCurrentInstance().execute("dlgEliminarInstitucion.show()");	
	}
		
	public void abrirDialogoBuscarInstitucion(){
		cargarListaInstituciones();
		numeroInstituciones=lstInstituciones.size();
		DefaultRequestContext.getCurrentInstance().execute("dlgBuscarInstitucion.show()");	
	}
	
	public void abirDialogoDetalleInstitucion(){
		DefaultRequestContext.getCurrentInstance().execute("InstDialogo.show()");
		cargarListaInstituciones();	
	}

	//VALIDACIONES INSTITUCION
	
	/**
	 * Valida si la institucion es unica por su identificacion que es un atributo unico  
	 * @return 
	 * true: Si la institucion pintada en la pagina aun no existe en la base
	 * false: Si ya existe y es distinta a la que esta pintada en la pagina
	 */
	
	public boolean validarInstitucionUnica(){
		Institucion iEncontrada = srvInstitucion.buscarPorIdentificacion(institucion.getIdentificacion());
		if(iEncontrada == null){
			return true;
		}else{
			/* Se considera el caso en el que ya exista en la base entonces si busco 
			 por identificacion la va a encontrar y es necesario comparar los ids para 
			 verificar si es la misma que esta pintada o es otra */
			if(iEncontrada.getInstitucionid().equals(institucion.getInstitucionid())){
				return true;
			}else{
				return false;
			}
		}
	}
	//METODOOS DE BUSQUEDA
	/**
	 * Busca instituciones con caracteres ingresados
	 *y setea los atributos @Transient canton y provincia
	 */
	
	public void buscarPorNombres(){
		System.out.println("imprimir"+ nombreABuscar);
		if((nombreABuscar.toString().compareTo("")!=0)){
			
			lstInstituciones= srvInstitucion.buscarPorNombres(nombreABuscar);
			if(lstInstituciones.size() > 0){
				for(Institucion i : lstInstituciones){
					i.setCanton(i.getParroquia().getCanton().getNombre());
					i.setProvincia(i.getParroquia().getCanton().getProvincia().getNombre());
				}
				nombreABuscar=null;
				institucionDataModel=new InstitucionDataModel(lstInstituciones);
				
			}else{	
				
			GeneradorMensajes.mostrarMensajeInformacion(MensajesInformacion.COMMONS_NO_EXISTEN_REGISTROS_CON_PARAMETRO);
			}
			
		}else{
			
			GeneradorMensajes.mostrarMensajeError(MensajesError.COMMONS_INGRESAR_PARAMETRO_BUSQUEDA);
			
		}
		
	}
	/**
	 * Busca instituciones con identificacion ingresada
	 *y setea los atributos @Transient canton y provincia
	 */
	public void buscarPorIdentificacion() {
		if(identificacionABuscar.toString().compareTo("")!=0){
			//mediunInstitucionModel.setWrappedData(srvInstitucion.buscarPorIdentificacion(identificacionABuscar));
			Institucion iEncontrada = srvInstitucion.buscarPorIdentificacion(identificacionABuscar);
			if(iEncontrada!=null){
				setLstInstituciones(new ArrayList<Institucion>());
				iEncontrada.setCanton(iEncontrada.getParroquia().getCanton().getNombre());
				iEncontrada.setProvincia(iEncontrada.getParroquia().getCanton().getProvincia().getNombre());
				getLstInstituciones().add(iEncontrada);
				identificacionABuscar=null;
				institucionDataModel=new InstitucionDataModel(lstInstituciones);
			}
			
		}else{
			GeneradorMensajes.mostrarMensajeError(MensajesError.COMMONS_INGRESAR_PARAMETRO_BUSQUEDA);
		}
	}
	
	/////////////////////////////////////////////////

	//Método de seleccion y  despliegue de información 
	
	  public void onRowSelect(SelectEvent event) {  
	        FacesMessage msg = new FacesMessage("Institución Seleccionada",((Institucion) event.getObject()).getNombre());  
	        FacesContext.getCurrentInstance().addMessage(null, msg);  
	    }
	  
	  
	  public void onRowUnselect(UnselectEvent event) {  
	        FacesMessage msg = new FacesMessage("No a seleccionado ninguna Institucion",((Institucion) event.getObject()).getNombre());  
	  
	        FacesContext.getCurrentInstance().addMessage(null, msg);  
	    }  
	  
//	  private void populateRandomInstitucion(List<Institucion> list, int size) {  
//	        for(int i = 0 ; i < size ; i++)  
//	            list.add(new Institucion());  
//	    } 
	  
	/*
	 **************************************************setters y getters
	 */
	public Institucion getInstitucion() {
		return institucion;
	}

	public void setInstitucion(Institucion institucion) {
		this.institucion = institucion;
	}
	public int getNumeroInstituciones() {
		return numeroInstituciones;
	}

	public void setNumeroInstituciones(int numeroInstituciones) {
		this.numeroInstituciones = numeroInstituciones;
	}

	public String getIdentificacionABuscar() {
		return identificacionABuscar;
	}

	public void setIdentificacionABuscar(String identificacionABuscar) {
		this.identificacionABuscar = identificacionABuscar;
	}

	public String getNombreABuscar() {
		return nombreABuscar;
	}

	public void setNombreABuscar(String nombreABuscar) {
		this.nombreABuscar = nombreABuscar;
	}

	public List<Pai> getLstPaises() {
		return lstPaises;
	}

	public void setLstPaises(List<Pai> lstPaises) {
		this.lstPaises = lstPaises;
	}

	public List<Provincia> getLstProvincias() {
		return lstProvincias;
	}

	public void setLstProvincias(List<Provincia> lstProvincias) {
		this.lstProvincias = lstProvincias;
	}

	public List<Canton> getLstCatones() {
		return lstCatones;
	}

	public void setLstCatones(List<Canton> lstCatones) {
		this.lstCatones = lstCatones;
	}

	public List<Institucion> getLstInstituciones() {
		return lstInstituciones;
	}

	public void setLstInstituciones(List<Institucion> lstInstituciones) {
		this.lstInstituciones = lstInstituciones;
	}

	public Integer getIdPais() {
		return idPais;
	}

	public void setIdPais(Integer idPais) {
		this.idPais = idPais;
	}

	public Integer getIdProvincia() {
		return idProvincia;
	}

	public void setIdProvincia(Integer idProvincia) {
		this.idProvincia = idProvincia;
	}

	public Integer getIdCanton() {
		return idCanton;
	}

	public void setIdCanton(Integer idCanton) {
		this.idCanton = idCanton;
	}

	public Integer getIdParroquia() {
		return idParroquia;
	}

	public void setIdParroquia(Integer idParroquia) {
		this.idParroquia = idParroquia;
	}

	public List<Parroquia> getLstParroquias() {
		return lstParroquias;
	}

	public void setLstParroquias(List<Parroquia> lstParroquias) {
		this.lstParroquias = lstParroquias;
	}

	public int getIdMeridiano() {
		return idMeridiano;
	}

	public void setIdMeridiano(int idMeridiano) {
		this.idMeridiano = idMeridiano;
	}

	public List<Plan> getLstPlanes() {
		return lstPlanes;
	}

	public void setLstPlanes(List<Plan> lstPlanes) {
		this.lstPlanes = lstPlanes;
	}

	public Integer getIdPlan() {
		return idPlan;
	}

	public void setIdPlan(Integer idPlan) {
		this.idPlan = idPlan;
	}

	public List<Programa> getLstProgramas() {
		return lstProgramas;
	}

	public void setLstProgramas(List<Programa> lstProgramas) {
		this.lstProgramas = lstProgramas;
	}

	public List<Proyecto> getLstProyectos() {
		return lstProyectos;
	}

	public void setLstProyectos(List<Proyecto> lstProyectos) {
		this.lstProyectos = lstProyectos;
	}

	public Integer getIdProyecto() {
		return idProyecto;
	}

	public void setIdProyecto(Integer idProyecto) {
		this.idProyecto = idProyecto;
	}

	public List<Convenio> getLstConvenios() {
		return lstConvenios;
	}

	public void setLstConvenios(List<Convenio> lstConvenios) {
		this.lstConvenios = lstConvenios;
	}

	public Integer getIdPrograma() {
		return idPrograma;
	}

	public void setIdPrograma(Integer idPrograma) {
		this.idPrograma = idPrograma;
	}

	public List<Estado> getLstEstado() {
		return lstEstado;
	}

	public void setLstEstado(List<Estado> lstEstado) {
		this.lstEstado = lstEstado;
	}

	public Integer getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}

	public List<Tipoinstitucion> getLstTipoinstituciones() {
		return lstTipoinstituciones;
	}

	public void setLstTipoinstituciones(List<Tipoinstitucion> lstTipoinstituciones) {
		this.lstTipoinstituciones = lstTipoinstituciones;
	}

	public List<Tiposostenimiento> getLstTipoSostenimientos() {
		return lstTipoSostenimientos;
	}

	public void setLstTipoSostenimientos(List<Tiposostenimiento> lstTipoSostenimientos) {
		this.lstTipoSostenimientos = lstTipoSostenimientos;
	}

	public Integer getIdConvenio() {
		return idConvenio;
	}

	public void setIdConvenio(Integer idConvenio) {
		this.idConvenio = idConvenio;
	}

	public Integer getIdTipoInstitucion() {
		return idTipoInstitucion;
	}

	public void setIdTipoInstitucion(Integer idTipoInstitucion) {
		this.idTipoInstitucion = idTipoInstitucion;
	}

	public Integer getIdTipoSostenimiento() {
		return idTipoSostenimiento;
	}

	public void setIdTipoSostenimiento(Integer idTipoSostenimiento) {
		this.idTipoSostenimiento = idTipoSostenimiento;
	}

	public List<Instbeneficiada> getLstInstBeneficiadas() {
		return lstInstBeneficiadas;
	}

	public void setLstInstBeneficiadas(List<Instbeneficiada> lstInstBeneficiadas) {
		this.lstInstBeneficiadas = lstInstBeneficiadas;
	}

	public Integer getIdInstBeneficiada() {
		return idInstBeneficiada;
	}

	public void setIdInstBeneficiada(Integer idInstBeneficiada) {
		this.idInstBeneficiada = idInstBeneficiada;
	}

	public Instbeneficiada getInstBeneficiada() {
		return instBeneficiada;
	}

	public void setInstBeneficiada(Instbeneficiada instBeneficiada) {
		this.instBeneficiada = instBeneficiada;
	}

	public List<Inversion> getLstInversiones() {
		return lstInversiones;
	}

	public void setLstInversiones(List<Inversion> lstInversiones) {
		this.lstInversiones = lstInversiones;
	}

	public Integer getIdInversion() {
		return idInversion;
	}

	public void setIdInversion(Integer idInversion) {
		this.idInversion = idInversion;
	}


	public InstitucionDataModel getInstitucionDataModel() {
		return institucionDataModel;
	}


	public void setInstitucionDataModel(InstitucionDataModel institucionDataModel) {
		this.institucionDataModel = institucionDataModel;
	}


	
}

