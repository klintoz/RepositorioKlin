package ec.gob.simintel.administracion.controladores;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.DefaultRequestContext;
import ec.gob.simintel.administracion.servicios.ServicioCanton;
import ec.gob.simintel.administracion.servicios.ServicioParroquia;
import ec.gob.simintel.controladores.commons.GeneradorMensajes;
import ec.gob.simintel.controladores.commons.MensajesError;
import ec.gob.simintel.controladores.commons.MensajesInformacion;
import ec.gob.simintel.entidades.Canton;
import ec.gob.simintel.entidades.Parroquia;

/**Permite crear Parroquias y ligar con provinvias,cantones e instituciones beneficiadas 
 * 
 * @author Klintozcano
 *
 */

@ManagedBean
@ViewScoped
public class ControladorParroquia {
	
	@EJB
	private ServicioParroquia srvParroquia;
	private Parroquia parroquia;
	private List<Parroquia> lstParroquias;
	private String nombreParroquiaABuscar;
	private boolean activarBotonInsertar;
	private boolean activarBotonActualizar;
	
	
	//BLOQUE Y DESBLOQUE O DE BOTONES PARROQUIA
	private Boolean botonInsertarParroquia;
	private Boolean botonActualizarParroquia;
	private Boolean botonEliminarParroquia;
	
	//CARGAR COMBO DE PARROQUIA
	
	@EJB
	private ServicioCanton srvCanton;
	private List<Canton> lstCanton;
	private Integer idCanton;
	
	@PostConstruct
	public void incio(){
		nuevoParroquia();
		cargarListaCantones();
	} 
	
	public void nuevoParroquia(){
		parroquia= new Parroquia();
		habilitarGuardarParroquia();
	
	}
	
	private void habilitarGuardarParroquia(){
		botonInsertarParroquia = false;
		botonActualizarParroquia =true;
		botonEliminarParroquia = true;
	}
	
	public void habilitarEditarParroquia(){
		botonInsertarParroquia = true;
		botonActualizarParroquia= false;
		botonEliminarParroquia = false;
	}
	
	/**
	 * Carga las parroquias del pais que seleccione en el combo Canton 
	 */
	public void cargarListaParroquia(){
		lstParroquias=srvParroquia.buscarParroquiasPorCanton(idCanton);
		nuevoParroquia();
		}
	
	/**
	 * Carga las Parroquias  del pais que seleccione en el combo Cantones
	 */
	public  void cargarListaCantones(){
		lstCanton = srvCanton.buscarTodos();
	}
	
	public void abrirDialogoEliminarParroquia(){
		DefaultRequestContext.getCurrentInstance().execute("dlgEliminarParroquia.show()");

	}
	
	
	//metodo recuperar Parroquia
		
	public void recuperarParroquia(){
				parroquia=srvParroquia.buscarPorId(parroquia.getParroquiaid());
				habilitarEditarParroquia();
	}
		
	
	//METODOS CRUD PARROQUIA
	
	public void insertarParroquia(){
		if(!idCanton.equals(0)){
			System.out.println("Insertando");
			parroquia.setCanton(srvCanton.buscarPorId(idCanton));
			srvParroquia.insertar(parroquia);
			cargarListaParroquia();
			nuevoParroquia();
		}else{
			GeneradorMensajes.mostrarMensajeError(MensajesError.PARROQUIA_CANTON_OBLIGATORIO);
		}
		
			
	}
						
	public void cancelarCambiosParroquia(){
		parroquia=srvParroquia.buscarPorId(parroquia.getParroquiaid());
			
		
	}
	
	public void actualizarParroquia(){
		srvParroquia.actualizar(parroquia);
		cargarListaParroquia();
		nuevoParroquia();		
	}

	public void eliminarParroquia(){
		try {
			srvParroquia.eliminar(parroquia);
			lstParroquias=srvParroquia.buscarTodos();
			nuevoParroquia();
			GeneradorMensajes.mostrarMensajeInformacion(MensajesInformacion.COMMONS_ELIMINACION_OK);
		} catch (Exception e) {
			e.printStackTrace();
			GeneradorMensajes.mostrarMensajeError(MensajesError.COMMONS_TIENE_REGISTRO_ASOCIADOS);
		}
		
				
	}
	
	//***************************************************METODOS BUSQUEDA PARROQUIA****************************************************
		public void buscarParroquia(){
			System.out.println("imprimir" + nombreParroquiaABuscar);
			if((nombreParroquiaABuscar.toString()).compareTo("") != 0){
				lstParroquias= srvParroquia.buscarParroquiaPorNombres(nombreParroquiaABuscar);
				if (lstParroquias.size() > 0){
					nombreParroquiaABuscar=null;
					
				}else{
					
					GeneradorMensajes.mostrarMensajeInformacion(MensajesInformacion.PARAMETRO_NO_EXISTE);	
				}
			}else{
				GeneradorMensajes.mostrarMensajeInformacion(MensajesError.PARAMETROS_BUSQUEDA);
			}
			
		}

		public Parroquia getParroquia() {
			return parroquia;
		}

		public void setParroquia(Parroquia parroquia) {
			this.parroquia = parroquia;
		}

		public String getNombreParroquiaABuscar() {
			return nombreParroquiaABuscar;
		}

		public void setNombreParroquiaABuscar(String nombreParroquiaABuscar) {
			this.nombreParroquiaABuscar = nombreParroquiaABuscar;
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

		public List<Parroquia> getLstParroquias() {
			return lstParroquias;
		}

		public void setLstParroquias(List<Parroquia> lstParroquias) {
			this.lstParroquias = lstParroquias;
		}

		public Boolean getBotonInsertarParroquia() {
			return botonInsertarParroquia;
		}

		public void setBotonInsertarParroquia(Boolean botonInsertarParroquia) {
			this.botonInsertarParroquia = botonInsertarParroquia;
		}

		public Boolean getBotonActualizarParroquia() {
			return botonActualizarParroquia;
		}

		public void setBotonActualizarParroquia(Boolean botonActualizarParroquia) {
			this.botonActualizarParroquia = botonActualizarParroquia;
		}

		public Boolean getBotonEliminarParroquia() {
			return botonEliminarParroquia;
		}

		public void setBotonEliminarParroquia(Boolean botonEliminarParroquia) {
			this.botonEliminarParroquia = botonEliminarParroquia;
		}

		public Integer getIdCanton() {
			return idCanton;
		}

		public void setIdCanton(Integer idCanton) {
			this.idCanton = idCanton;
		}

		public List<Canton> getLstCanton() {
			return lstCanton;
		}

		public void setLstCanton(List<Canton> lstCanton) {
			this.lstCanton = lstCanton;
		}
		 		
		
}
