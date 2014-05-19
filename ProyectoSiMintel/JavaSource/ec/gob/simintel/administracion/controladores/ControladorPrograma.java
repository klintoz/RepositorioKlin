package ec.gob.simintel.administracion.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.DefaultRequestContext;

import ec.gob.simintel.administracion.servicios.ServicioPlan;
import ec.gob.simintel.administracion.servicios.ServicioPrograma;
import ec.gob.simintel.commons.datamanager.GeneralDataManager;
import ec.gob.simintel.controladores.commons.GeneradorMensajes;
import ec.gob.simintel.controladores.commons.MensajesError;
import ec.gob.simintel.controladores.commons.MensajesInformacion;
import ec.gob.simintel.entidades.Persona;
import ec.gob.simintel.entidades.Plan;
import ec.gob.simintel.entidades.Programa;
import ec.gob.simintel.seguridad.servicios.ServicioPersona;
import ec.gob.simintel.validadores.UtilRedireccion;


/**Permite crear Programas  y ligar con instituciones beneficiadas 
 * 
 * @author Klintozcano
 *
 */

@ManagedBean
@SessionScoped
public class ControladorPrograma {
	
	@EJB
	private ServicioPrograma srvPrograma;
	private Programa  programa;
	@ManagedProperty (value="#{generalDataManager}")
	private GeneralDataManager generalDM;
	
	/*Busquedas*/
	private String nombreProgramaABuscar;
	private boolean activarBotonInsertar;
	private boolean activarBotonActualizar;
	

	/*Botones de operaciones CRUD*/
	private Boolean botonInsertarPlan;
	private Boolean botonActualizarPlan;
	private Boolean botonEliminarPlan;
	
	private Boolean botonInsertarPrograma;
	private Boolean botonActualizarPrograma;
	private Boolean botonEliminarPrograma;

	/*
	 * Combos
	 */
	@EJB
	private ServicioPlan srvPlan;
	private List<Plan> lstPlans;
	private Plan  plan;
	private String nombrePlanABuscar;
	
	
	@EJB
	private ServicioPersona srvPersona;
	private List<Persona> lstPersonas;
	private Integer idPersona;
	
	
	@PostConstruct
	public void incio(){
		System.out.println("Ejecuntado post");
		nuevoPlan();
		cargarListaPlanes();
	    cargarListaAdministradores();
	    getGeneralDM().setLstProgramas(new ArrayList<Programa>());
	    habilitarAgregarPrograma();
	}
		
	public void nuevoPlan(){
		plan = new Plan();
		getGeneralDM().setLstProgramas(new ArrayList<Programa>());
		nuevoPrograma();
		habilitarGuardarPlan();
	}
	
	public void nuevoPrograma(){
		programa = new Programa();
		idPersona=0;
	}
		
	private void habilitarGuardarPlan(){
		setBotonInsertarPlan(false);
		setBotonActualizarPlan(true);
		setBotonEliminarPlan(true);
	}
		
	public void habilitarEditarPlan(){
		setBotonInsertarPlan(true);
		setBotonActualizarPlan(false);
		setBotonEliminarPlan(false);
		cargarListaProgramas();
	}
	
	public void  habilitarAgregarPrograma(){
		botonInsertarPrograma=false;
		botonActualizarPrograma=true;
	}
	
	public void habilitarModificarPrograma(){
		botonInsertarPrograma=true;
		botonActualizarPrograma=false;
	}
	
		
	public void cargarListaProgramas(){
		getGeneralDM().setLstProgramas(srvPrograma.buscarProgramaPorPlan(plan.getPlanid()));
		 
	}
	
	public void cargarListaPlanes(){
		lstPlans=srvPlan.buscarTodos();
	}
	
	public void cargarListaAdministradores(){
		lstPersonas=srvPersona.buscarTodos();
		
	}
	
		
	public void abrirDialogoEliminarPrograma(){
		DefaultRequestContext.getCurrentInstance().execute("dlgEliminarPrograma.show()");	
			
	}
	
	public void abrirDialogoBuscarPlan(){
		cargarListaPlanes();
		DefaultRequestContext.getCurrentInstance().execute("dlgBuscarPlan.show()");
		
	}
	
	public void agregarPrograma(){
		if(!idPersona.equals(0)){
			programa.setPersona(srvPersona.buscarPorId(idPersona));
			programa.setPlan(plan);// PARA QUE SE SETEE EL ID DEL PLAN EN EL PROGRAMA EN LA BASE DE DATOS.
			programa.setIndice(getGeneralDM().getLstProgramas().size());
			getGeneralDM().getLstProgramas().add(programa);
			nuevoPrograma();
		}
		
	}
	
	public void actualizarPrograma(){
		for(Programa p:getGeneralDM().getLstProgramas()){
			if(programa.getIndice()==p.getIndice()){
				p.setDescripcion(programa.getDescripcion());
				p.setPersona(srvPersona.buscarPorId(idPersona));
				break;
			}
		}
		nuevoPrograma();
		habilitarAgregarPrograma();
			
	}
	
	
	/*
	 * *****************************METODOS CRUD PROGRAMA
	 */
	
		public void insertarPlan(){
				System.out.println("Insertando");
				plan.setProgramas(getGeneralDM().getLstProgramas());//CON CascadeType.PERSIST AUTOMATICAMENTE INSERTA LOS PROGRAMAS
				srvPlan.insertar(plan);
				nuevoPlan();	
		}
		
		public void actualizarPlan(){
			plan.setProgramas(getGeneralDM().getLstProgramas());//CON CascadeType.MERGE AUTOMATICAMENTE INSERTA O ACTUALIZA LOS PROGRAMAS DEL PLAN
			srvPlan.actualizar(plan);
			nuevoPlan();	
					
		}

		public void eliminarPlan(){
			try {
				srvPlan.eliminar(plan);
				nuevoPlan();
				GeneradorMensajes.mostrarMensajeInformacion(MensajesInformacion.COMMONS_ELIMINACION_OK);
			} catch (Exception e) {
				e.printStackTrace();
				GeneradorMensajes.mostrarMensajeError(MensajesError.COMMONS_TIENE_REGISTRO_ASOCIADOS);
			}
					
						
		}
		
			
			//METODOOS DE BUSQUEDA
			/**
			 * Busca instituciones con caracteres ingresados
			 *y setea los atributos @Transient programa y plan
			 */
			public void buscarPlanPorNombres(){
				System.out.println("imprimir"+ nombrePlanABuscar);
				if((nombrePlanABuscar.toString().compareTo("")!=0)){
					setLstPlans(srvPlan.buscarPorNombres(nombrePlanABuscar));
					if(getLstPlans().size() > 0){
						nombrePlanABuscar=null;
					}
					else{
						GeneradorMensajes.mostrarMensajeInformacion(MensajesInformacion.COMMONS_NO_EXISTEN_REGISTROS_CON_PARAMETRO);
						}
				}else{
					GeneradorMensajes.mostrarMensajeError(MensajesError.COMMONS_INGRESAR_PARAMETRO_BUSQUEDA);
				}
			}
			
			
			public void irProyectos(){
				generalDM.setIdPrograma(programa.getProgramaid());
				generalDM.cargarListaProyectos();				
				UtilRedireccion.redireccionar("paginas/Proyectos.jsf");
			}
			
			//GENERAION DE GETTES Y SETTES 
				


			public Programa getPrograma() {
				return programa;
			}


			public void setPrograma(Programa programa) {
				this.programa = programa;
			}


			public String getNombreProgramaABuscar() {
				return nombreProgramaABuscar;
			}


			public void setNombreProgramaABuscar(String nombreProgramaABuscar) {
				this.nombreProgramaABuscar = nombreProgramaABuscar;
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
			
			public List<Plan> getLstPlans() {
				return lstPlans;
			}


			public void setLstPlans(List<Plan> lstPlans) {
				this.lstPlans = lstPlans;
			}


			public List<Persona> getLstPersonas() {
				return lstPersonas;
			}


			public void setLstPersonas(List<Persona> lstPersonas) {
				this.lstPersonas = lstPersonas;
			}


			public Integer getIdPersona() {
				return idPersona;
			}


			public void setIdPersona(Integer idPersona) {
				this.idPersona = idPersona;
			}


			public Plan getPlan() {
				return plan;
			}


			public void setPlan(Plan plan) {
				this.plan = plan;
			}


			public String getNombrePlanABuscar() {
				return nombrePlanABuscar;
			}


			public void setNombrePlanABuscar(String nombrePlanABuscar) {
				this.nombrePlanABuscar = nombrePlanABuscar;
			}

			public Boolean getBotonInsertarPlan() {
				return botonInsertarPlan;
			}

			public void setBotonInsertarPlan(Boolean botonInsertarPlan) {
				this.botonInsertarPlan = botonInsertarPlan;
			}

			public Boolean getBotonActualizarPlan() {
				return botonActualizarPlan;
			}

			public void setBotonActualizarPlan(Boolean botonActualizarPlan) {
				this.botonActualizarPlan = botonActualizarPlan;
			}

			public Boolean getBotonEliminarPlan() {
				return botonEliminarPlan;
			}

			public void setBotonEliminarPlan(Boolean botonEliminarPlan) {
				this.botonEliminarPlan = botonEliminarPlan;
			}
			
			public Boolean getBotonInsertarPrograma() {
				return botonInsertarPrograma;
			}

			public void setBotonInsertarPrograma(Boolean botonInsertarPrograma) {
				this.botonInsertarPrograma = botonInsertarPrograma;
			}

			public Boolean getBotonActualizarPrograma() {
				return botonActualizarPrograma;
			}

			public void setBotonActualizarPrograma(Boolean botonActualizarPrograma) {
				this.botonActualizarPrograma = botonActualizarPrograma;
			}

			public Boolean getBotonEliminarPrograma() {
				return botonEliminarPrograma;
			}

			public void setBotonEliminarPrograma(Boolean botonEliminarPrograma) {
				this.botonEliminarPrograma = botonEliminarPrograma;
			}

			public GeneralDataManager getGeneralDM() {
				return generalDM;
			}

			public void setGeneralDM(GeneralDataManager generalDM) {
				this.generalDM = generalDM;
			}
	
			
}
