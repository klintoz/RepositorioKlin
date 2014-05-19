package ec.gob.simintel.administracion.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.DefaultRequestContext;

import ec.gob.simintel.administracion.servicios.ServicioAtributosEquipo;
import ec.gob.simintel.administracion.servicios.ServicioConvenio;
import ec.gob.simintel.administracion.servicios.ServicioGestionInversion;
import ec.gob.simintel.administracion.servicios.ServicioGrupoDatos;
import ec.gob.simintel.administracion.servicios.ServicioPlanInversion;
import ec.gob.simintel.administracion.servicios.ServicioSetAtributo;
import ec.gob.simintel.administracion.servicios.ServicioTipoInversion;
import ec.gob.simintel.controladores.commons.Constantes;
import ec.gob.simintel.controladores.commons.GeneradorMensajes;
import ec.gob.simintel.controladores.commons.MensajesError;
import ec.gob.simintel.controladores.commons.MensajesInformacion;
import ec.gob.simintel.entidades.Atributossetatrib;
import ec.gob.simintel.entidades.Convenio;
import ec.gob.simintel.entidades.Grupodato;
import ec.gob.simintel.entidades.Inversion;
import ec.gob.simintel.entidades.Planinversion;
import ec.gob.simintel.entidades.Setatributo;
import ec.gob.simintel.entidades.Tipoinversion;

/**
 * Premite crear el Plan de Inversión  que van estar ligados a la Inversión
 * @author Klintozcano
 *
 */

@ManagedBean
@SessionScoped
public class ControladorPlanInversion {
	
	@EJB
	private ServicioPlanInversion srvPlanInversion;
	private Planinversion planInversion;
	private List<Planinversion> lstPlanInversiones;
	private Integer idPlanInversion;
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * eliminar esta parte una ves que se haya integrado con la gestion de Inversion ¡¡¡¡muy importante hacer este cambio!!!
	 */
	
	@EJB
	private ServicioGestionInversion  srvInversion;
	private Inversion inversion;
	private List<Inversion> lstInversiones;
	private Integer idInversion;
	
	
	@EJB
	private ServicioTipoInversion srvTipoInversion;
	private Tipoinversion tipoInversion;
	private List<Tipoinversion> lstTipoInversiones;
	private Integer idTipoInversion;
	
	
	@EJB
	private ServicioGrupoDatos srvGrupoDatos;
	private Grupodato grupoDato;
	private Integer idGrupo;
	
	@EJB
	private ServicioConvenio srvConvenio;
	private Convenio  convenio;
	private List<Convenio> lstConvenios;
	private Integer idConvenio;
	
	@EJB
	private ServicioGrupoDatos srvGrupoDato;
	private Grupodato grupodato;
	private List<Grupodato> lstGrupoDatos;
	private Integer IdGrupodato;
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@EJB
	private ServicioSetAtributo srvEquipos;
	private Setatributo equiposatributos;
	private List<Setatributo> lstEquipos;
	private Integer idEquipos;
	
	@EJB
	private ServicioAtributosEquipo srvAtributoEquipos;
	private Atributossetatrib atributosEquipo;
	private List<Atributossetatrib> lstAtributos;
	private Integer idAtributos;
	
	
	//Botones de busqueda 
	
	private String nombreInversionPlanABuscar;
	private boolean activarBotonInsertar;
	private boolean activarBotonActualizar;
	private int idDetalledeCantidad;
	
	
	/*Botones de operaciones CRUD*/
	private Boolean botonInsertarInversionPlan;
	private Boolean botonActualizarInversionPlan;
	private Boolean botonEliminarInversionPlan;
	
	private Boolean botonInsertarPlanInversion;
	private Boolean botonActualizarPlanInversion;
	private Boolean botonEliminarPlanInversion;
	
	/**
	 * Al momento de unir con gestion de inventario revisar 
	 */
	
	
	@EJB
	private ServicioSetAtributo srvSetAtributo;
	

	@PostConstruct
	public void inicio(){
		System.out.println("Ejecuntado post");
		nuevoInversionPlan();
		cargarListaConvenios();
		//cargarListaInversionPlan();
		//lstPlanInversiones = new ArrayList<Planinversion>();
		habilitarAgregarPlanInversion();
		cargarEquipos();
		
	}
	
	public void nuevoInversionPlan(){
		inversion = new Inversion();
		lstPlanInversiones = new ArrayList<Planinversion>();
		nuevoPlanInversion();
		habilitarGuardarInversionPlan();
		
	}
	
	public void nuevoPlanInversion(){
		planInversion= new Planinversion();
		idDetalledeCantidad=0;
		idEquipos=0;
	}
	
	
	private void habilitarGuardarInversionPlan(){
		setBotonInsertarInversionPlan(false);
		setBotonActualizarInversionPlan(true);
		setBotonEliminarInversionPlan(true);
		
	}
		
	public void habilitarEditarInversionPlan(){
		setBotonInsertarInversionPlan(true);
		setBotonActualizarInversionPlan(false);
		setBotonEliminarInversionPlan(false);
		cargarListaPlanInversion();
		//pintarCombos();	
	}
	
	public void habilitarAgregarPlanInversion(){
		botonInsertarPlanInversion=false;
		botonActualizarPlanInversion=true;
		
	}
	public void habilitarModificarPlanInversion(){
		System.out.println("Entro a modificar PlanInversion");
		botonInsertarPlanInversion=true;
		botonActualizarPlanInversion=false;
		getDetalle();//visualizar el combo 
		idEquipos = planInversion.getSetatributosid();
	}
	
	/**
	 * Método que me permite cargar la lista Inversiones 
	 * @return
	 * lista de Inversiones
	 */
	
	public void cargarListaInversionPlan(){
		lstInversiones =srvInversion.buscarTodos();
	}
	
	public void cargarListaPlanInversion(){
		lstPlanInversiones =srvPlanInversion.buscarPlanInverPorInversion(inversion.getInversionid());
	}
	
	public void cargarEquipos(){
		lstEquipos = srvEquipos.buscarTodos();
	}
	
	public void abrirDialogoBuscarInversionPlan(){
		cargarListaInversionPlan();
		DefaultRequestContext.getCurrentInstance().execute("dlgBuscarInversionPlan.show()");
		
	}
	
	public void abrirDialogoEliminarPlanInversion(){
		DefaultRequestContext.getCurrentInstance().execute("dlgEliminarPlanInversion.show()");
	}
	
	
	/**
	 * Método cambio para cargar la lista de convenios 
	 * @return
	 * Lista de Convenios
	 */
	
	public void cargarListaConvenios(){
		lstConvenios = srvConvenio.buscarTodos();	
		lstInversiones = new ArrayList<Inversion>();
		lstTipoInversiones = new ArrayList<Tipoinversion>();
		lstPlanInversiones = new ArrayList<Planinversion>();
		idConvenio=0;
		idInversion=0;
		idTipoInversion=0;
		idPlanInversion=0;
	
	}
	/**
	 * Carga las inversiones  del Convenio seleccionado en el combo
	 */
	
	public void cargarListaInversionPorConvenio(){
		lstInversiones = srvInversion.buscarInversionesPorConvenio(idConvenio);
		//lstTipoInversiones = new ArrayList<Tipoinversion>();
		lstPlanInversiones = new ArrayList<Planinversion>();
	
	}
	
	/**
	 * Carga las Tipo de Inversion de la  Inversion seleccionado en el combo
	 */
	
	public void pintarTipoInversion(){
		inversion =srvInversion.buscarPorId(idInversion);
		habilitarEditarInversionPlan();
	}

	/**
	 * Carga el Plan de Inversion segun el tipo de Inversion  del  seleccionado en el combo
	 */
	
	public void cargarPlanInversionTipoInversion(){
		
	 lstPlanInversiones = srvPlanInversion.buscarPlanInverPorInversion(idTipoInversion);
		
	}
	
	/**
	 * Setea el valor del Detalle Cantidad seleccionado en el combo 
	 * @return True = si lo escogio y se seteo, False = si no se escogio 
	 */
	
	public void setDetalle(){
		switch(idDetalledeCantidad){
		case 1:
			planInversion.setDetallecantidad(Constantes.DETALLECANTIDADSI);
		  	break;
		case 2:
			planInversion.setDetallecantidad(Constantes.DETALLECANTIDADNO);
			break;
		}
		
	}
	
	/**
	 * Recupera el valor correspondiente de idDetalle para pintar en el combo
	 */
	public void getDetalle(){
		idDetalledeCantidad=0;
	
		System.out.println("entro a getseriado " + planInversion.getDetallecantidad());
		if (planInversion.getDetallecantidad().compareTo(Constantes.DETALLECANTIDADSI)==0){
			idDetalledeCantidad=1;
			
		}else if (planInversion.getDetallecantidad().compareTo(Constantes.DETALLECANTIDADNO)==0) {
			idDetalledeCantidad=2;
		}
	}
	
	
	/**
	 * Métodos que permiten agregar y modificar los planes de Inversion según la Inversion Generada
	 * @return
	 */
	
	public void agregarPlanInversion(){	
		if(idInversion!=0){
			planInversion.setSetatributosid(idEquipos);//Creamos atributo en base de datos para setear el id del equipo
			planInversion.setSetatributo(srvSetAtributo.buscarPorId(idEquipos));//Creamos este atributo @transient para llenar el objeto completo y pintarlo en la tabla   
			planInversion.setInversion(inversion);
			planInversion.setIndice(lstPlanInversiones.size());
			setDetalle();
			lstPlanInversiones.add(planInversion);
			nuevoPlanInversion();
		}else{
			//mensaje escoger inverion
		}
	}
	
	public void actualizarPlanInversion(){
		
		for(Planinversion pi: lstPlanInversiones){
			if(planInversion.getIndice()==pi.getIndice())
				{
				pi.setDescriptivo(planInversion.getDescriptivo());
				pi.setPrecio(planInversion.getPrecio());
				//setDetalle();
				pi.setDetallecantidad(planInversion.getDetallecantidad());
				
				break;	
			}
			
		}
		habilitarAgregarPlanInversion();
		nuevoPlanInversion();
	}
	
	/**
	 * Creamos los Métodos de las operaciones CRUD Inversion
	 * @return
	 */
	
	public void insertarInversionPlan(){
		System.out.println("Insertando Inversion Plan.....");
		inversion.setPlaninversions(lstPlanInversiones);
		setDetalle();
		srvInversion.insertar(inversion);
		nuevoInversionPlan();
	}
	
	public void actualizarInversionPlan(){
		inversion.setPlaninversions(getLstPlanInversiones());
		srvInversion.actualizar(inversion);//CON CascadeType.PERSIST AUTOMATICAMENTE INSERTA LOS EQUIPOS
		nuevoInversionPlan();
	}
	
	public void eliminarInversionPlan(){
		try {
			srvInversion.eliminar(inversion);
			nuevoInversionPlan();
			GeneradorMensajes.mostrarMensajeInformacion(MensajesInformacion.COMMONS_ELIMINACION_OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			GeneradorMensajes.mostrarMensajeInformacion(MensajesError.COMMONS_TIENE_REGISTRO_ASOCIADOS);
		}
	}
	
	//METODOOS DE BUSQUEDA
		/**
		 * Busca Inversion  con caracteres ingresados
		 *y setea los plan inversion @Transient descripcion de los equipos
		 */
		
		public void buscarInversionPlanPorNombre(){
			System.out.println("imprimir"+ nombreInversionPlanABuscar);
			if((nombreInversionPlanABuscar.toString().compareTo("")!=0)){
				setLstInversiones(srvInversion.buscarInversionPorDescripcion(nombreInversionPlanABuscar));
			
				if(getLstInversiones().size() > 0){
					nombreInversionPlanABuscar=null;
				}
				else{
					GeneradorMensajes.mostrarMensajeInformacion(MensajesInformacion.COMMONS_NO_EXISTEN_REGISTROS_CON_PARAMETRO);
					}
			}else{
				GeneradorMensajes.mostrarMensajeError(MensajesError.COMMONS_INGRESAR_PARAMETRO_BUSQUEDA);
			}
		}
	
	
	//////////////////////////////////////////////////////////////gets and setes///////////////////////////////////////////////////////
	
	public Inversion getInversion() {
		return inversion;
	}
	public void setInversion(Inversion inversion) {
		this.inversion = inversion;
	}
	public List<Inversion> getLstInversiones() {
		return lstInversiones;
	}
	public void setLstInversiones(List<Inversion> lstInversiones) {
		this.lstInversiones = lstInversiones;
	}
	
	public Tipoinversion getTipoInversion() {
		return tipoInversion;
	}
	public void setTipoInversion(Tipoinversion tipoInversion) {
		this.tipoInversion = tipoInversion;
	}
	public Integer getIdTipoInversion() {
		return idTipoInversion;
	}
	public void setIdTipoInversion(Integer idTipoInversion) {
		this.idTipoInversion = idTipoInversion;
	}
	public Grupodato getGrupoDato() {
		return grupoDato;
	}
	public void setGrupoDato(Grupodato grupoDato) {
		this.grupoDato = grupoDato;
	}
	public Integer getIdGrupo() {
		return idGrupo;
	}
	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}
	public Setatributo getEquiposatributos() {
		return equiposatributos;
	}
	public void setEquiposatributos(Setatributo equiposatributos) {
		this.equiposatributos = equiposatributos;
	}
	public List<Setatributo> getLstEquipos() {
		return lstEquipos;
	}
	public void setLstEquipos(List<Setatributo> lstEquipos) {
		this.lstEquipos = lstEquipos;
	}
	public Integer getIdEquipos() {
		return idEquipos;
	}
	public void setIdEquipos(Integer idEquipos) {
		this.idEquipos = idEquipos;
	}
	public Atributossetatrib getAtributosEquipo() {
		return atributosEquipo;
	}
	public void setAtributosEquipo(Atributossetatrib atributosEquipo) {
		this.atributosEquipo = atributosEquipo;
	}
	public List<Atributossetatrib> getLstAtributos() {
		return lstAtributos;
	}
	public void setLstAtributos(List<Atributossetatrib> lstAtributos) {
		this.lstAtributos = lstAtributos;
	}
	public Integer getIdAtributos() {
		return idAtributos;
	}
	public void setIdAtributos(Integer idAtributos) {
		this.idAtributos = idAtributos;
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
	public String getNombreInversionPlanABuscar() {
		return nombreInversionPlanABuscar;
	}
	public void setNombreInversionPlanABuscar(String nombreInversionPlanABuscar) {
		this.nombreInversionPlanABuscar = nombreInversionPlanABuscar;
	}
	public Boolean getBotonInsertarInversionPlan() {
		return botonInsertarInversionPlan;
	}
	public void setBotonInsertarInversionPlan(Boolean botonInsertarInversionPlan) {
		this.botonInsertarInversionPlan = botonInsertarInversionPlan;
	}
	public Boolean getBotonActualizarInversionPlan() {
		return botonActualizarInversionPlan;
	}
	public void setBotonActualizarInversionPlan(
			Boolean botonActualizarInversionPlan) {
		this.botonActualizarInversionPlan = botonActualizarInversionPlan;
	}
	public Boolean getBotonEliminarInversionPlan() {
		return botonEliminarInversionPlan;
	}
	public void setBotonEliminarInversionPlan(Boolean botonEliminarInversionPlan) {
		this.botonEliminarInversionPlan = botonEliminarInversionPlan;
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

	public Planinversion getPlanInversion() {
		return planInversion;
	}

	public void setPlanInversion(Planinversion planInversion) {
		this.planInversion = planInversion;
	}

	public List<Planinversion> getLstPlanInversiones() {
		return lstPlanInversiones;
	}

	public void setLstPlanInversiones(List<Planinversion> lstPlanInversiones) {
		this.lstPlanInversiones = lstPlanInversiones;
	}

	public Integer getIdPlanInversion() {
		return idPlanInversion;
	}

	public void setIdPlanInversion(Integer idPlanInversion) {
		this.idPlanInversion = idPlanInversion;
	}

	public int getIdDetalledeCantidad() {
		return idDetalledeCantidad;
	}

	public void setIdDetalledeCantidad(int idDetalledeCantidad) {
		this.idDetalledeCantidad = idDetalledeCantidad;
	}

	public Convenio getConvenio() {
		return convenio;
	}

	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
	}

	public Integer getIdConvenio() {
		return idConvenio;
	}

	public void setIdConvenio(Integer idConvenio) {
		this.idConvenio = idConvenio;
	}

	public List<Convenio> getLstConvenios() {
		return lstConvenios;
	}

	public void setLstConvenios(List<Convenio> lstConvenios) {
		this.lstConvenios = lstConvenios;
	}

	public List<Tipoinversion> getLstTipoInversiones() {
		return lstTipoInversiones;
	}

	public void setLstTipoInversiones(List<Tipoinversion> lstTipoInversiones) {
		this.lstTipoInversiones = lstTipoInversiones;
	}

	public Grupodato getGrupodato() {
		return grupodato;
	}

	public void setGrupodato(Grupodato grupodato) {
		this.grupodato = grupodato;
	}

	public Integer getIdGrupodato() {
		return IdGrupodato;
	}

	public void setIdGrupodato(Integer idGrupodato) {
		IdGrupodato = idGrupodato;
	}

	public List<Grupodato> getLstGrupoDatos() {
		return lstGrupoDatos;
	}

	public void setLstGrupoDatos(List<Grupodato> lstGrupoDatos) {
		this.lstGrupoDatos = lstGrupoDatos;
	}

	public Integer getIdInversion() {
		return idInversion;
	}

	public void setIdInversion(Integer idInversion) {
		this.idInversion = idInversion;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	

}
