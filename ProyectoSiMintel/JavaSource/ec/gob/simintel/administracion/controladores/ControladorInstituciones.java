package ec.gob.simintel.administracion.controladores;

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
import com.sun.org.apache.xerces.internal.impl.dv.dtd.IDDatatypeValidator;
import ec.gob.simintel.administracion.servicios.ServicioBeneficiarios;
import ec.gob.simintel.administracion.servicios.ServicioCanton;
import ec.gob.simintel.administracion.servicios.ServicioContactos;
import ec.gob.simintel.administracion.servicios.ServicioConvenio;
import ec.gob.simintel.administracion.servicios.ServicioDatosContactos;
import ec.gob.simintel.administracion.servicios.ServicioEstado;
import ec.gob.simintel.administracion.servicios.ServicioGestionInversion;
import ec.gob.simintel.administracion.servicios.ServicioGrupoDatos;
import ec.gob.simintel.administracion.servicios.ServicioInstBeneficiada;
import ec.gob.simintel.administracion.servicios.ServicioInstituciones;
import ec.gob.simintel.administracion.servicios.ServicioMedioContacto;
import ec.gob.simintel.administracion.servicios.ServicioPais;
import ec.gob.simintel.administracion.servicios.ServicioParroquia;
import ec.gob.simintel.administracion.servicios.ServicioPlan;
import ec.gob.simintel.administracion.servicios.ServicioPlanInversion;
import ec.gob.simintel.administracion.servicios.ServicioPrograma;
import ec.gob.simintel.administracion.servicios.ServicioProvincia;
import ec.gob.simintel.administracion.servicios.ServicioProyecto;
import ec.gob.simintel.administracion.servicios.ServicioTipoInstitucion;
import ec.gob.simintel.administracion.servicios.ServicioTipoMedio;
import ec.gob.simintel.administracion.servicios.ServicioTipoSostenimiento;
import ec.gob.simintel.commons.datamanager.InstitucionDataModel;
import ec.gob.simintel.controladores.commons.Constantes;
import ec.gob.simintel.controladores.commons.GeneradorMensajes;
import ec.gob.simintel.controladores.commons.MensajesError;
import ec.gob.simintel.controladores.commons.MensajesInformacion;
import ec.gob.simintel.entidades.Beneficiario;
import ec.gob.simintel.entidades.Canton;
import ec.gob.simintel.entidades.Contacto;
import ec.gob.simintel.entidades.Convenio;
import ec.gob.simintel.entidades.Datoscontacto;
import ec.gob.simintel.entidades.Estado;
import ec.gob.simintel.entidades.Grupodato;
import ec.gob.simintel.entidades.Instbeneficiada;
import ec.gob.simintel.entidades.Institucion;
import ec.gob.simintel.entidades.Inversion;
import ec.gob.simintel.entidades.Mediocontacto;
import ec.gob.simintel.entidades.Pai;
import ec.gob.simintel.entidades.Parroquia;
import ec.gob.simintel.entidades.Plan;
import ec.gob.simintel.entidades.Planinversion;
import ec.gob.simintel.entidades.Programa;
import ec.gob.simintel.entidades.Provincia;
import ec.gob.simintel.entidades.Proyecto;
import ec.gob.simintel.entidades.Tipoinstitucion;
import ec.gob.simintel.entidades.Tipomedio;
import ec.gob.simintel.entidades.Tiposostenimiento;

@ManagedBean
@ViewScoped

public class ControladorInstituciones {
	
	private Institucion institucion;
	
	/*Busquedas*/
	private String nombreABuscar;
	private String identificacionABuscar;
	@EJB
	private ServicioInstituciones  srvInstitucion;
	private List<Institucion> lstInstituciones;
	private int numeroInstituciones;
	
	/*Botones de operaciones CRUD*/
	private boolean botonInsertarInstitucion;
	private boolean botonActualizarInstitucion;
	private boolean botonEliminarInstitucion;
	
	private Boolean botonInsertarDatosContacto;
	private Boolean botonActualizarDatosContacto;
	private Boolean botonEliminarDatosContacto;
	
	private Boolean botonInsertarMedioContacto;
	private Boolean botonActualizarMedioContacto;
	private Boolean botonEliminarMedioContacto;
	
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
	
	
	
	/**
	 * Servicios de Inversion/PlanInversion/GrupoDatos
	 */
	@EJB
	private ServicioPlanInversion srvPlanInversion;
	private List<Planinversion> lstPlanesInversion;
	private Integer idPlanInversion;
	
	@EJB
	private ServicioGrupoDatos srvGrupoDatos;
	private List<Grupodato> lstGrupoDatos;
	private Integer idGrupoDato;
	

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
	
	@EJB
	private ServicioContactos srvContactos;
	private List<Contacto> lstContactos;
	private  Contacto contacto;
	private Integer idContacto;
	
	
	

	
	
	
	/*************
	 contactos
	 *************/
	
	@EJB
	private ServicioDatosContactos srvDatosContacto;
	private List<Datoscontacto> lstDatoscontactos;
	private Datoscontacto datocontacto;
	private String nombreContacto;
	private String identificacionContacto;
	private String nombreOidentificacion;
	private Integer seleccionBusqueda;
	
	private Integer seleccionDesbloqueo;
	private boolean identificacionBloqueo;
	private boolean nombreBloqueo;
	
	@EJB
	private ServicioMedioContacto srvMedioContacto;
	private List<Mediocontacto> lstMediosContactos;
	private Mediocontacto mediocontacto;
	private Integer idMedioContacto;
	
	@EJB
	private ServicioBeneficiarios srvBeneficiados;
	private List<Beneficiario> lstBeneficiarios;
	private Integer idBeneficiador;
	
	@EJB
	private ServicioTipoMedio srvTipoMedio;
	private Tipomedio tipoMedio;
	private List<Tipomedio> lstTipoMedio;
	private Integer idTipoMedio;
	
	private Boolean visiblepnlEdicionContacto;
	private Boolean accionGuardar;
	

	@PostConstruct
	public void inicio(){
		System.out.println("Ejecuntado post");
		nuevaInstitucion();
			
	}
	
	public void nuevaInstitucion(){
		institucion = new Institucion();
		habilitarGuardarInstitucion();
		cargarListaPaises();
		cargarListaPlanes();
		cargarListaTipoInstituciones();
		cargarListaTipoSostenimiento();
		cargarlistaEstados();
		idMeridiano=0;
		idEstado=0;
		idTipoInstitucion=0;
		idTipoSostenimiento=0;
		inicializarlstContactos();	
		
	}
	
	private void habilitarGuardarInstitucion(){
		botonInsertarInstitucion= false;
		botonEliminarInstitucion=true;
		botonActualizarInstitucion=true;
	}
	
	public void habilitarEditarInstitucion(){
		botonInsertarInstitucion= true;
		botonEliminarInstitucion=false;
		botonActualizarInstitucion=false;
		pintarCombos();
	}
	
	
	
	
	/**
	 * Asigna los values que pintan los combos de pais provincia cantón parroquia y meridiano
	 */
	public void pintarCombos(){
		getMeridiano();
		//pintar combos ubicacion
		idPais=institucion.getParroquia().getCanton().getProvincia().getPai().getPaisid();
		cargarListaProvincias();
		idProvincia= institucion.getParroquia().getCanton().getProvincia().getProvinciaid();
		cargarListaCantones();
		idCanton=institucion.getParroquia().getCanton().getCantonid();
		cargarListaParroquias();
		idParroquia=institucion.getParroquia().getParroquiaid();
		
		//pintar combos inversion
		idPlan = instBeneficiada.getInversion().getConvenio().getProyecto().getPrograma().getPlan().getPlanid();
		cargarListaProgramas();
		idPrograma = instBeneficiada.getInversion().getConvenio().getProyecto().getPrograma().getProgramaid();
		cargarListaProyectos();
		idProyecto = instBeneficiada.getInversion().getConvenio().getProyecto().getProyectoid();
		cargarListaConvenio();
		idConvenio = instBeneficiada.getInversion().getConvenio().getConvenioid();
		cargarListaInversion();
		idInversion = instBeneficiada.getInversion().getInversionid();
		
		
		//pintar combos tipos y estado
		idEstado=institucion.getEstado().getEstadoid();
		idTipoInstitucion=institucion.getTipoinstitucion().getTipoinstitucionid();
		idTipoSostenimiento=institucion.getTiposostenimiento().getTiposostenimientoid();
		
		//pintar lista de contactos
		lstContactos = srvContactos.buscarPorInstitucion(institucion.getInstitucionid());
		for(Contacto contacto : lstContactos){
			//seteo la lista de medios contactos (telefono, mail, etc) de una persona (datocontacto)
			contacto.getDatoscontacto().setMediocontactos(srvMedioContacto.buscarPorDatosContacto(contacto.getDatoscontacto().getDatoscontactoid()));
		}
		
	}
	
	/**
	 * Carga el combo paises
	 * Inicializa las listas de provincias - cantones - parroquias
	 * Inicializa los values que pintan los combos de provincias - cantones - parroquias
	 */
	public void cargarListaPaises(){
		lstPaises = srvPais.buscarTodos();	
		lstProvincias =new ArrayList<Provincia>();
		lstCatones = new ArrayList<Canton>();
		lstParroquias = new ArrayList<Parroquia>();
		idPais=0;
		idProvincia=0;
		idCanton=0;
		idParroquia=0;
	}
	
	/**
	 * Carga las provincias del pais seleccionado en el combo
	 */
	public void cargarListaProvincias(){
		lstProvincias =srvProvincia.buscarProvinciasPorPais(idPais);
		lstCatones = new ArrayList<Canton>();
		lstParroquias = new ArrayList<Parroquia>();
	}
	
	/**
	 * Carga los cantones de la provincia seleccionada en el combo
	 */
	public void cargarListaCantones(){
		lstCatones=srvCanton.buscarCantonesPorProvincia(idProvincia);
		lstParroquias = new ArrayList<Parroquia>();
	}
	
	/**
	 * Carga las parroquias del cantón seleccionado en el combo
	 */
	public void cargarListaParroquias(){
		lstParroquias =srvParroquia.buscarParroquiasPorCanton(idCanton);
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
	
	/**
	 * Carga todos los planes
	 */
	
	public void cargarListaPlanes(){
		lstPlanes =srvPlan.buscarTodos();
		lstProgramas =new ArrayList<Programa>();
		lstProyectos =new ArrayList<Proyecto>();
		lstConvenios =new ArrayList<Convenio>();
		lstInversiones = new ArrayList<Inversion>();
		idPlan=0; 
		idPrograma=0;
		idProyecto=0;
		idConvenio=0;
		idInversion=0;
	}
	
	/**
	 * Carga los programas del plan  seleccionado en el combo
	 */
	
	public void cargarListaProgramas(){
		lstProgramas =srvPrograma.buscarProgramaPorPlan(idPlan);
		lstProyectos = new ArrayList<Proyecto>();
		lstConvenios = new ArrayList<Convenio>();
	}
	
	/**
	 * Carga las proyecto del programa  seleccionado en el combo
	 */
	
	public void cargarListaProyectos(){
		lstProyectos = srvProyecto.buscarProyectoPorPrograma(idPrograma);
		lstConvenios= new ArrayList<Convenio>();
	}
	
	/**
	 * Carga las convenios del proyecto seleccionado en el combo
	 */

	public void cargarListaConvenio(){
		lstConvenios = srvConvenio.buscarConvenioPorProyecto(idProyecto);
		lstInversiones = new ArrayList<Inversion>();
	}
	
	public void cargarListaInversion(){
		lstInversiones = srvInversion.buscarInversionesPorConvenio(idConvenio);
	}
	
	public void cargarlistaEstados (){
		lstEstado =srvEstado.buscarTodos();	
	}
	
	public void cargarListaTipoInstituciones(){
		lstTipoinstituciones =srvTipoInstitucion.buscarTodos();
	
	}
	
	public void cargarListaTipoSostenimiento(){
		lstTipoSostenimientos = srvTipoSostenimiento.buscarTodos();
	}
	
	public void abirDialogoEliminarInstitucion(){
		DefaultRequestContext.getCurrentInstance().execute("dlgEliminarInstitucion.show()");
		
	}
		
	public void abrirDialogoBuscarInstitucion(){
		cargarListaInstituciones();
		numeroInstituciones=lstInstituciones.size();
		DefaultRequestContext.getCurrentInstance().execute("dlgBuscarInstitucion.show()");
		
	}
	
	public void abrirDialogoInstBeneficadas(){
		
		DefaultRequestContext.getCurrentInstance().execute("dlgInstBeneficadoEn.show()");
		cargarListaInvPorInstitucion();
	}
		
	
	public void abrirDialogoInversionEquipamiento(){
		DefaultRequestContext.getCurrentInstance().execute("dlgInversionEquipaminento.show()");
	}
	
	public void abrirDialogoInversionConectividad(){
		DefaultRequestContext.getCurrentInstance().execute("dlgInvConectividad.show()");
	}
	
	public void cargarListaInvPorInstitucion(){
		lstInstBeneficiadas = srvInstBeneficiada.buscarInstBeneficios(institucion.getInstitucionid());
		System.out.println("tamaño de la lista"+lstInstBeneficiadas.size());
	}
	
	public void abirDialogoDetalleInstitucion(){
		DefaultRequestContext.getCurrentInstance().execute("InstDialogo.show()");
		cargarListaInstituciones();
	
		
	}
	
	public void cargarDetalleInstitucion(){
		institucion = srvInstitucion.buscarDetalleInstitucion(institucion.getInstitucionid());
		
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
	
	/**
	 * Setea el valor del meridiano seleccionado en el combo 
	 * @return True = si lo escogio y se seteo, False = si no se escogio 
	 */
	public void setMeridiano(){
		switch(idMeridiano){
		case 1:
			institucion.setMeridiano(Constantes.MERIDIANO17);
			break;
		case 2:
			institucion.setMeridiano(Constantes.MERIDIANO18);
			break;
		}
	}
	
	/**
	 * Recupera el valor correspondiente de idMeridiano para pintar en el combo  
	 */
	public void getMeridiano(){
		idMeridiano=0;
		if(institucion.getMeridiano().compareTo(Constantes.MERIDIANO17)==0){
			idMeridiano=1;
		}else if(institucion.getMeridiano().compareTo(Constantes.MERIDIANO18)==0){
			idMeridiano=2;
		}
	}
	

	
	/*
	 * *******************************************METODOS CRUD INSTITUCIONES 
	 */

	public void insertarInstitucion(){
		if(validarInstitucionUnica()){
			if(!idParroquia.equals(0)){//Si es diferente de cero es por que SI se ha seleccionado parroquia
				if(!idConvenio.equals(0)){
					institucion.setParroquia(srvParroquia.buscarPorId(idParroquia));
					institucion.setTipoinstitucion(srvTipoInstitucion.buscarPorId(idTipoInstitucion));
					institucion.setTiposostenimiento(srvTipoSostenimiento.buscarPorId(idTipoSostenimiento));
					institucion.setEstado(srvEstado.buscarPorId(idEstado));
					institucion.setBeneficiarios(lstBeneficiarios);
					institucion.setContactos(lstContactos);
					setMeridiano();
					guardarEquipamiento();
					srvInstitucion.insertar(institucion);
					nuevaInstitucion();	
				}else{
					GeneradorMensajes.mostrarMensajeError(MensajesError.INSTITUCION_CONVENIO_OBLIGATORIO);
				}					
			}else{
				GeneradorMensajes.mostrarMensajeError(MensajesError.INSTITUCION_PARROQUIA_OBLIGATORIO);
			}
		}else{
			GeneradorMensajes.mostrarMensajeError(MensajesError.COMMONS_TIENE_REGISTRO_DUPLICADO);
		}	
	}
	
	 /********
	 * Mëtodo que permite cargar la plantilla de Equipos
	 * que se le entregan a la insstitucion beneficia por el MINTEL
	 * 
	 *
	 * Previo programar : equipos, caracteristicas, inversion
	 */
	
	public void guardarEquipamiento(){
		
		
		
	}
	
	
	
	public void actualizarInstitucion(){
		if (validarInstitucionUnica()) {
			if(!idParroquia.equals(0)){
				if(!idInversion.equals(0)){
					institucion.setParroquia(srvParroquia.buscarPorId(idParroquia));
					setIdInversion(idInversion);
					setMeridiano();
					institucion.setContactos(lstContactos);
					institucion.setTipoinstitucion(srvTipoInstitucion.buscarPorId(idTipoInstitucion));
					institucion.setTiposostenimiento(srvTipoSostenimiento.buscarPorId(idTipoSostenimiento));
					institucion.setEstado(srvEstado.buscarPorId(idEstado));
					srvInstitucion.actualizar(institucion);
					nuevaInstitucion();	
				}else{
					GeneradorMensajes.mostrarMensajeError(MensajesError.INSTITUCION_CONVENIO_OBLIGATORIO);
				}	
			}else{
				GeneradorMensajes.mostrarMensajeError(MensajesError.INSTITUCION_PARROQUIA_OBLIGATORIO);
			}
		} else {
			GeneradorMensajes.mostrarMensajeError(MensajesError.COMMONS_TIENE_REGISTRO_DUPLICADO);
		}
		
	}
	
	public void eliminarInstitucion(){
		try {
			srvInstitucion.eliminar(institucion);
			nuevaInstitucion();	
		} catch (Exception e) {
			e.printStackTrace();
			GeneradorMensajes.mostrarMensajeError(MensajesError.COMMONS_TIENE_REGISTRO_DUPLICADO);
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
			setLstInstituciones(srvInstitucion.buscarPorNombres(nombreABuscar));
			if(getLstInstituciones().size() > 0){
				for(Institucion i : lstInstituciones){
					i.setCanton(i.getParroquia().getCanton().getNombre());
					i.setProvincia(i.getParroquia().getCanton().getProvincia().getNombre());
				}
				nombreABuscar=null;
			}
			else{
				
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
			Institucion iEncontrada = srvInstitucion.buscarPorIdentificacion(identificacionABuscar);
			if(iEncontrada!=null){
				setLstInstituciones(new ArrayList<Institucion>());
				iEncontrada.setCanton(iEncontrada.getParroquia().getCanton().getNombre());
				iEncontrada.setProvincia(iEncontrada.getParroquia().getCanton().getProvincia().getNombre());
				getLstInstituciones().add(iEncontrada);
				identificacionABuscar=null;
			}
		}else{
			GeneradorMensajes.mostrarMensajeError(MensajesError.COMMONS_INGRESAR_PARAMETRO_BUSQUEDA);
		}
	}
	
	/*******************
	 inicio datoContacto
	 *******************/	
	
	public void cargarListaDatoContactos(){
		lstDatoscontactos=srvDatosContacto.buscarTodos();
	}
	
	public void abrirDialogoBuscarContacto(){	
		visiblepnlEdicionContacto=false;
		cargarListaTipoMedio();		
		cargarListaDatoContactos();
		habilitarGuardarContacto();
		identificacionBloqueo = true;
		nombreBloqueo= true;
		nuevoContacto();
		DefaultRequestContext.getCurrentInstance().execute("dlgBuscarContacto.show()");
		
	}
	
	public void mostrarPanelGuardar(){ 
		visiblepnlEdicionContacto=true;
	}
	
	public void mostrarPanelActualizar(){
		visiblepnlEdicionContacto=true;
		habilitarActualizarContacto();
	}
	
	public void habilitarGuardarContacto(){
		botonInsertarDatosContacto=false;
		botonActualizarDatosContacto=true;
		habilitarAgregarMedioContacto();
		inicilizarlstMediosContactos();
		nuevoDatoContacto();
		nuevoMedioContacto();
	}
	
	public void habilitarActualizarContacto(){
		botonInsertarDatosContacto=true;
		botonActualizarDatosContacto=false;
		habilitarAgregarMedioContacto();
		cargarAgenda();
	}
	
	
	/////////////Formas de busqueda ////////
	
	public void formasdeBusqueda(){
		if(seleccionBusqueda != 0){
			if((nombreOidentificacion.toString()).compareTo("") !=0){
				switch (seleccionBusqueda) {
				case 1:
					buscarContactoPorNombre();
					break;
				case 2:
					buscarIdentificacionContato();
					break;
				default:
					break;
				}
				
			}else {
				GeneradorMensajes.mostrarMensajeError(MensajesError.COMMONS_INGRESAR_PARAMETRO_BUSQUEDA);
			}
			
		}else {
				GeneradorMensajes.mostrarMensajeError(MensajesError.COMMONS_SELECCIONAR_PARAMETRO_BUSQUEDA);
		}
		
	}
	
	
	
	/**
	 * Busca el Contacto de la institucion  con caracteres ingresados
	 *y setea los atributos @Transient Datos de Contacto  y Tipo de Medio 
	 */
	
	public void buscarContactoPorNombre(){
		System.out.println("imprimir" + nombreOidentificacion);
		if(nombreOidentificacion.toString().compareTo("")!=0){
			setLstDatoscontactos(srvDatosContacto.buscarDatoContacto(nombreOidentificacion));
			if(lstDatoscontactos.size()>0) {
				nombreOidentificacion=null;
			}
			else {
				GeneradorMensajes.mostrarMensajeInformacion(MensajesInformacion.COMMONS_NO_EXISTEN_REGISTROS_CON_PARAMETRO);
			}
		}else {
				GeneradorMensajes.mostrarMensajeError(MensajesError.COMMONS_INGRESAR_PARAMETRO_BUSQUEDA);
		}
	}
	/**
	 * Busca Dato Contacto   con identificacion ingresada
	 *en la lista de contactos ingresados 
	 */

	public void buscarIdentificacionContato(){

			if(nombreOidentificacion.toString().compareTo("")!=0){
				lstDatoscontactos= srvDatosContacto.buscarIdenticacionContacto(nombreOidentificacion);
				//Datoscontacto  identificacion =srvDatosContacto.buscarIdentificacion(nombreOidentificacion);
				if( lstDatoscontactos.size()>0){
					nombreOidentificacion=null;
				}
				else{
				GeneradorMensajes.mostrarMensajeError(MensajesError.COMMONS_INGRESAR_PARAMETRO_BUSQUEDA);
				}
				
			}else {
				GeneradorMensajes.mostrarMensajeError(MensajesError.COMMONS_SELECCIONAR_PARAMETRO_BUSQUEDA);
			}
				
		}
	
	public void activadorBusquedaContacto(){
		switch (seleccionDesbloqueo) {
		case 1:
			nombreBloqueo =false;
			identificacionBloqueo= true;
			datocontacto.setIdentificacion(null);
			break;
		case 2:
			nombreBloqueo =true;
			identificacionBloqueo= false;
			datocontacto.setNombre(null);
		default:
			nombreBloqueo = true;
			identificacionBloqueo= true;
			break;
		}
		
	}
	
	public void insertarDatoContacto(){
		System.out.println("Insertando Datos Contacto");
		datocontacto.setMediocontactos(lstMediosContactos);
		srvDatosContacto.insertar(datocontacto);
		habilitarActualizarContacto();
		cargarListaDatoContactos();
	}
	
	public void actualizarDatoContactos(){
		if(lstContactos.size()>0){
			System.out.println("la lista esta con " + lstContactos.size());
			for (Contacto c : lstContactos) {
				
				if (c.getContactosid()== c.getDatoscontacto().getDatoscontactoid()){
					datocontacto.setMediocontactos(lstMediosContactos);
					srvDatosContacto.actualizar(datocontacto);
					contacto.getDatoscontacto().setMediocontactos(srvMedioContacto.buscarPorDatosContacto(contacto.getDatoscontacto().getDatoscontactoid()));
				}  
				
				}
			}
	}
	
	
	
//   public void actualizarVerContacto(){
//		if(lstContactos.size()>0){
//			System.out.println("la lista esta con " + lstContactos.size());
//			for (Contacto c : lstContactos) {
//				
//				if (c.getContactosid()== c.getDatoscontacto().getDatoscontactoid()){
//					datocontacto.setMediocontactos(lstMediosContactos);
//					srvDatosContacto.actualizar(datocontacto);
//					contacto.getDatoscontacto().setMediocontactos(srvMedioContacto.buscarPorDatosContacto(contacto.getDatoscontacto().getDatoscontactoid()));
//				}  
//				
//				}
//			}	
//		
//	}
	
	
	
	
	
	
	/*************
	 fin datoContacto
	 *************/	

	
	
	
	/*************
	 inicio agenda
	 *************/
	public void  habilitarAgregarMedioContacto(){		
		botonInsertarMedioContacto=false;
		botonActualizarMedioContacto=true;
	}
	
	
	public void  habilitarActualizarMedioContacto(){		
		botonInsertarMedioContacto=true;
		botonEliminarMedioContacto=true;
		botonActualizarMedioContacto=false;
		
		if(botonInsertarDatosContacto==false){//guardando
			accionGuardar=true;
			botonInsertarDatosContacto=true;
		}else{//actualizando
			accionGuardar=false;
			botonActualizarDatosContacto=true;
		}
	}
	
	private  void nuevoMedioContacto(){
		mediocontacto = new Mediocontacto();
		idTipoMedio=0;
	}
	 
	private void inicilizarlstMediosContactos(){
		lstMediosContactos = new ArrayList<Mediocontacto>();
	}
	
	public void cargarAgenda(){
		nuevoMedioContacto();		
		lstMediosContactos=srvMedioContacto.buscarPorDatosContacto(datocontacto.getDatoscontactoid());
		ponerIndices();
		
	}
	
	public void ponerIndices(){
		int indice = 0; 
		for(Mediocontacto m : lstMediosContactos){
			 m.setIndice(indice);
			 indice++;
		 }
	}
	
	public void agregarMedioContacto(){
		if(!idTipoMedio.equals(0)){
			mediocontacto.setTipomedio(srvTipoMedio.buscarPorId(idTipoMedio));						
			mediocontacto.setDatoscontacto(datocontacto);
			/* Cuando agregue un nuevo elemento a la lista debo setear el valor de la posicion que ocupará en el campo indice que es @transient,
			 * para recuperarlo despúes por la posición y poder editarlo
			 */
			mediocontacto.setIndice(lstMediosContactos.size());			
			lstMediosContactos.add(mediocontacto);
			nuevoMedioContacto();			
		}else{
			GeneradorMensajes.mostrarMensajeError(MensajesError.CONTACTO_DATOSAGENDA_OBLIGATORIOS);
		}
	}
	
	public void actualizarMedioContacto(){		
		for(Mediocontacto filaMedio: lstMediosContactos){
			System.out.println(">>>>>>indice: "+filaMedio.getIndice());
			if(mediocontacto.getIndice()== filaMedio.getIndice()){
				//filaMedio.setDatocontacto(mediocontacto.getDatocontacto()); hace por defecto
				System.out.println("Entro al if ");			
				filaMedio.setTipomedio(srvTipoMedio.buscarPorId(idTipoMedio));
				break ;
				
			}
			
		}
		nuevoMedioContacto(); 
		if(accionGuardar){
			botonInsertarDatosContacto=false;
		}else{
			botonActualizarDatosContacto=false;
		}
		habilitarAgregarMedioContacto();
		botonEliminarMedioContacto=false;
	}
	
	public void cargarListaTipoMedio(){
		lstTipoMedio=srvTipoMedio.buscarTodos();
	}
	public void nuevoDatoContacto(){
		datocontacto= new Datoscontacto();
		
	}
	
	public void eliminarFilaMedioContacto(){
		if(mediocontacto.getMediocontactoid()!=null){
			srvMedioContacto.eliminar(mediocontacto);
		}
		lstMediosContactos.remove(mediocontacto);
		ponerIndices();		
	}
	
	
	/*************
	 fin agenda
	 *************/
		
	
	
	
	/*************
	 inicio contacto
	 *************/
	private void nuevoContacto(){
		contacto = new Contacto();
	}
	
	private void inicializarlstContactos(){
		lstContactos = new ArrayList<Contacto>();
	}
	
	/**
	 * Cierra el dialogo y agrega a los contactos de la institucion
	 */
	public void seleccionarDatoContacto(){
		
		cargarAgenda();		
		datocontacto.setMediocontactos(lstMediosContactos);
		contacto.setDatoscontacto(datocontacto);
		System.out.println("************"+institucion.getNombre());
		contacto.setInstitucion(institucion);
		lstContactos.add(contacto);
		
		//limpiar toda la pantalla que se va a cerrar osea el dialogo y ojo update
	}
	
	
	/**
	 * Abre el Dialogo y con la agenda del contacto
	 */
	public void verAgendaContacto(){
		
		datocontacto = contacto.getDatoscontacto();
		lstMediosContactos=datocontacto.getMediocontactos();
		cargarListaDatoContactos();/////
		cargarListaTipoMedio();/////
		mostrarPanelActualizar();
		DefaultRequestContext.getCurrentInstance().execute("dlgBuscarContacto.show()");

	} 
	
	public void eliminarFilaContacto(){
		System.out.println("contacto1 " + lstContactos.size());
		if(contacto.getContactosid()!= null){
			System.out.println("contacto2 " + lstContactos.size());
			srvContactos.eliminar(contacto);
			System.out.println("contacto3 " + contacto.getContactosid());
		}
		lstContactos.remove(contacto);		
	}
	
	public void ponerIndicesContacto(){
		int indice = 0; 
		for(Contacto c : lstContactos){
			 c.setIndice(indice);
			 indice++;
		 }
	}
	
	/*************
	 fin contacto
	 *************/
	
	/*****************
	 * Data Model
	 *****************/
	
	//metodo de despliegue de información 
	 private InstitucionDataModel mediumInstitucion;
	
	  public void onRowSelect(SelectEvent event) {  
	        FacesMessage msg = new FacesMessage("Institución Seleccionada", ((Institucion)event.getObject()).getIdentificacion());  
	  
	        FacesContext.getCurrentInstance().addMessage(null, msg);  
	    }
	  
	  
	  public void onRowUnselect(UnselectEvent event) {  
	        FacesMessage msg = new FacesMessage("No a seleccionado ninguna Institucion ",((Institucion) event.getObject()).getIdentificacion());  
	  
	        FacesContext.getCurrentInstance().addMessage(null, msg);  
	    }

	  
	
	  
	
	
	/*
	 **************************************************setters y getters
	 */
	public Institucion getInstitucion() {
		return institucion;
	}

	public void setInstitucion(Institucion institucion) {
		this.institucion = institucion;
	}

	public boolean isBotonInsertarInstitucion() {
		return botonInsertarInstitucion;
	}

	public void setBotonInsertarInstitucion(boolean botonInsertarInstitucion) {
		this.botonInsertarInstitucion = botonInsertarInstitucion;
	}

	public boolean isBotonActualizarInstitucion() {
		return botonActualizarInstitucion;
	}

	public void setBotonActualizarInstitucion(boolean botonActualizarInstitucion) {
		this.botonActualizarInstitucion = botonActualizarInstitucion;
	}

	public boolean isBotonEliminarInstitucion() {
		return botonEliminarInstitucion;
	}

	public void setBotonEliminarInstitucion(boolean botonEliminarInstitucion) {
		this.botonEliminarInstitucion = botonEliminarInstitucion;
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

	public InstitucionDataModel getMediumInstitucion() {
		return mediumInstitucion;
	}

	public void setMediumInstitucion(InstitucionDataModel mediumInstitucion) {
		this.mediumInstitucion = mediumInstitucion;
	}

	public List<Contacto> getLstContactos() {
		return lstContactos;
	}

	public void setLstContactos(List<Contacto> lstContactos) {
		this.lstContactos = lstContactos;
	}

	public Integer getIdContacto() {
		return idContacto;
	}

	public void setIdContacto(Integer idContacto) {
		this.idContacto = idContacto;
	}

	public List<Datoscontacto> getLstDatoscontactos() {
		return lstDatoscontactos;
	}

	public void setLstDatoscontactos(List<Datoscontacto> lstDatoscontactos) {
		this.lstDatoscontactos = lstDatoscontactos;
	}

	public List<Mediocontacto> getLstMediosContactos() {
		return lstMediosContactos;
	}

	public void setLstMediosContactos(List<Mediocontacto> lstMediosContactos) {
		this.lstMediosContactos = lstMediosContactos;
	}

	public Integer getIdMedioContacto() {
		return idMedioContacto;
	}

	public void setIdMedioContacto(Integer idMedioContacto) {
		this.idMedioContacto = idMedioContacto;
	}

	public Integer getIdBeneficiador() {
		return idBeneficiador;
	}

	public void setIdBeneficiador(Integer idBeneficiador) {
		this.idBeneficiador = idBeneficiador;
	}

	public List<Beneficiario> getLstBeneficiarios() {
		return lstBeneficiarios;
	}

	public void setLstBeneficiarios(List<Beneficiario> lstBeneficiarios) {
		this.lstBeneficiarios = lstBeneficiarios;
	}

	public List<Tipomedio> getLstTipoMedio() {
		return lstTipoMedio;
	}

	public void setLstTipoMedio(List<Tipomedio> lstTipoMedio) {
		this.lstTipoMedio = lstTipoMedio;
	}

	public Integer getIdTipoMedio() {
		return idTipoMedio;
	}

	public void setIdTipoMedio(Integer idTipoMedio) {
		this.idTipoMedio = idTipoMedio;
	}

	public Datoscontacto getDatocontacto() {
		return datocontacto;
	}

	public void setDatocontacto(Datoscontacto datocontacto) {
		this.datocontacto = datocontacto;
	}

	public Mediocontacto getMediocontacto() {
		return mediocontacto;
	}

	public void setMediocontacto(Mediocontacto mediocontacto) {
		this.mediocontacto = mediocontacto;
	}

	public Contacto getContacto() {
		return contacto;
	}

	public void setContacto(Contacto contacto) {
		this.contacto = contacto;
	}

	public Tipomedio getTipoMedio() {
		return tipoMedio;
	}

	public void setTipoMedio(Tipomedio tipoMedio) {
		this.tipoMedio = tipoMedio;
	}

	public Boolean getBotonInsertarDatosContacto() {
		return botonInsertarDatosContacto;
	}

	public void setBotonInsertarDatosContacto(Boolean botonInsertarDatosContacto) {
		this.botonInsertarDatosContacto = botonInsertarDatosContacto;
	}

	public Boolean getBotonActualizarDatosContacto() {
		return botonActualizarDatosContacto;
	}

	public void setBotonActualizarDatosContacto(
			Boolean botonActualizarDatosContacto) {
		this.botonActualizarDatosContacto = botonActualizarDatosContacto;
	}

	public Boolean getBotonEliminarMedioContacto() {
		return botonEliminarMedioContacto;
	}

	public void setBotonEliminarMedioContacto(Boolean botonEliminarMedioContacto) {
		this.botonEliminarMedioContacto = botonEliminarMedioContacto;
	}

	public Boolean getBotonEliminarDatosContacto() {
		return botonEliminarDatosContacto;
	}

	public void setBotonEliminarDatosContacto(Boolean botonEliminarDatosContacto) {
		this.botonEliminarDatosContacto = botonEliminarDatosContacto;
	}

	public Boolean getBotonInsertarMedioContacto() {
		return botonInsertarMedioContacto;
	}

	public void setBotonInsertarMedioContacto(Boolean botonInsertarMedioContacto) {
		this.botonInsertarMedioContacto = botonInsertarMedioContacto;
	}

	public Boolean getBotonActualizarMedioContacto() {
		return botonActualizarMedioContacto;
	}

	public void setBotonActualizarMedioContacto(
			Boolean botonActualizarMedioContacto) {
		this.botonActualizarMedioContacto = botonActualizarMedioContacto;
	}

	public String getNombreContacto() {
		return nombreContacto;
	}

	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}

	public Boolean getVisiblepnlEdicionContacto() {
		return visiblepnlEdicionContacto;
	}

	public void setVisiblepnlEdicionContacto(Boolean visiblepnlEdicionContacto) {
		this.visiblepnlEdicionContacto = visiblepnlEdicionContacto;
	}

	public String getIdentificacionContacto() {
		return identificacionContacto;
	}

	public void setIdentificacionContacto(String identificacionContacto) {
		this.identificacionContacto = identificacionContacto;
	}

	public Integer getSeleccionBusqueda() {
		return seleccionBusqueda;
	}

	public void setSeleccionBusqueda(Integer seleccionBusqueda) {
		this.seleccionBusqueda = seleccionBusqueda;
	}

	public String getNombreOidentificacion() {
		return nombreOidentificacion;
	}

	public void setNombreOidentificacion(String nombreOidentificacion) {
		this.nombreOidentificacion = nombreOidentificacion;
	}

	public boolean isIdentificacionBloqueo() {
		return identificacionBloqueo;
	}

	public void setIdentificacionBloqueo(boolean identificacionBloqueo) {
		this.identificacionBloqueo = identificacionBloqueo;
	}

	public boolean isNombreBloqueo() {
		return nombreBloqueo;
	}

	public void setNombreBloqueo(boolean nombreBloqueo) {
		this.nombreBloqueo = nombreBloqueo;
	}

	public Integer getSeleccionDesbloqueo() {
		return seleccionDesbloqueo;
	}

	public void setSeleccionDesbloqueo(Integer seleccionDesbloqueo) {
		this.seleccionDesbloqueo = seleccionDesbloqueo;
	}

	public List<Planinversion> getLstPlanesInversion() {
		return lstPlanesInversion;
	}

	public void setLstPlanesInversion(List<Planinversion> lstPlanesInversion) {
		this.lstPlanesInversion = lstPlanesInversion;
	}

	public Integer getIdPlanInversion() {
		return idPlanInversion;
	}

	public void setIdPlanInversion(Integer idPlanInversion) {
		this.idPlanInversion = idPlanInversion;
	}

	public ServicioGrupoDatos getSrvGrupoDatos() {
		return srvGrupoDatos;
	}

	public void setSrvGrupoDatos(ServicioGrupoDatos srvGrupoDatos) {
		this.srvGrupoDatos = srvGrupoDatos;
	}

	public List<Grupodato> getLstGrupoDatos() {
		return lstGrupoDatos;
	}

	public void setLstGrupoDatos(List<Grupodato> lstGrupoDatos) {
		this.lstGrupoDatos = lstGrupoDatos;
	}

	public Integer getIdGrupoDato() {
		return idGrupoDato;
	}

	public void setIdGrupoDato(Integer idGrupoDato) {
		this.idGrupoDato = idGrupoDato;
	}

	
	
}
