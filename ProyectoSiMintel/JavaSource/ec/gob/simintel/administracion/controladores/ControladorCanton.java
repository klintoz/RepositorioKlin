package ec.gob.simintel.administracion.controladores;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.DefaultRequestContext;
import ec.gob.simintel.administracion.servicios.ServicioCanton;
import ec.gob.simintel.administracion.servicios.ServicioProvincia;
import ec.gob.simintel.controladores.commons.GeneradorMensajes;
import ec.gob.simintel.controladores.commons.MensajesError;
import ec.gob.simintel.controladores.commons.MensajesInformacion;
import ec.gob.simintel.entidades.Canton;
import ec.gob.simintel.entidades.Institucion;
import ec.gob.simintel.entidades.Provincia;

@ManagedBean
@ViewScoped
public class ControladorCanton {
	
		@EJB
		private ServicioCanton srvCanton;
		private Canton canton;
		private String nombreCantonABuscar;
		private boolean activarBotonInsertar;
		private boolean activarBotonActualizar;
			
		//CARGAR COMBO DE CANTON
		private Institucion instituciones;
		private List<Canton> lstCanton;

		//BLOQUE Y DESBLOQUE O DE BOTONES CANTONES
		private Boolean botonInsertarCantones;
		private Boolean botonActualizarCantones;
		private Boolean botonEliminarCantones;
		
		/*
		 * COMBOS
		 * 
		 */
		
		@EJB
		private ServicioProvincia srvProvincia;
		private List<Provincia> lstProvincia;
		private Integer  idProvincia;
		
		
		@PostConstruct
		public void incio(){
			nuevoCanton();
			cargarListaProvincias();
		} 
		
		public void nuevoCanton(){
			canton = new Canton();
			habilitarGuardarCanton();
		}
		
		private void habilitarGuardarCanton(){
			botonInsertarCantones = false;
			botonActualizarCantones =true;
			botonEliminarCantones = true;
		}
		
		public void habilitarEditarCanton(){
			botonInsertarCantones = true;
			botonActualizarCantones = false;
			botonEliminarCantones = false;
			
		}
		/**
		 * Carga las cantones del pais que seleccione en el combo Provincia 
		 */
		
		public void cargarListaCantones(){
		  lstCanton=srvCanton.buscarCantonesPorProvincia(idProvincia);
		  nuevoCanton();     
		}
		
		
		/**
		 * Carga las Provincias del pais 
		 */
		public void cargarListaProvincias(){
			lstProvincia = srvProvincia.buscarTodos();
		}
		
		
		public void abrirDialogoEliminarCantones(){
			DefaultRequestContext.getCurrentInstance().execute("dlgEliminarCanton.show()");
		}
		
		
	//metodo recuperar canton
			
		public void recuperarCanton(){
				canton=srvCanton.buscarPorId(canton.getCantonid());
				habilitarEditarCanton();
		}
		
		//++++++++++++++++++++++++++++++++++++++++++++++++++++METODOS CRUD CANTONES++++++++++++++++++++++++++++++++++++++
		
		public void insertarCantones(){
				if (!idProvincia.equals(0)){
					   System.out.println("Insertando");
					   canton.setProvincia(srvProvincia.buscarPorId(idProvincia));
					   srvCanton.insertar(canton);
					   cargarListaCantones();
					   nuevoCanton();  
				   }else {
					GeneradorMensajes.mostrarMensajeError(MensajesError.CANTON_PROVINCIA_OBLIGATORIO);
				   }
			}
				
	    
		
		public void actualizarCanton(){
			srvCanton.actualizar(canton);
			cargarListaCantones();
			nuevoCanton();	
		}

		public void eliminarCanton(){
			try {
				srvCanton.eliminar(canton);
				lstCanton=srvCanton.buscarTodos();
				nuevoCanton();
				GeneradorMensajes.mostrarMensajeInformacion(MensajesInformacion.COMMONS_ELIMINACION_OK);
			} catch (Exception e) {
				e.printStackTrace();
				GeneradorMensajes.mostrarMensajeError(MensajesError.COMMONS_TIENE_REGISTRO_ASOCIADOS);
			}
			
			
						
		}
		
		//****************************************************METODOS BUSQUEDA CANTON*************************************************
		public void buscarCanton(){
			System.out.println("imprimir" + nombreCantonABuscar);
			if((nombreCantonABuscar.toString()).compareTo("") != 0){
				lstCanton= srvCanton.buscarCantonPorNombres(nombreCantonABuscar);
				if (lstCanton.size() > 0){
					nombreCantonABuscar=null;
						
				}else{
						
					GeneradorMensajes.mostrarMensajeInformacion(MensajesInformacion.PARAMETRO_NO_EXISTE);
					
					}
				}else{
					GeneradorMensajes.mostrarMensajeInformacion(MensajesError.PARAMETROS_BUSQUEDA);
				}
				
			}
		//********************get y set*********************************

		public Canton getCanton() {
			return canton;
		}

		public void setCanton(Canton canton) {
			this.canton = canton;
		}

		public String getNombreCantonABuscar() {
			return nombreCantonABuscar;
		}

		public void setNombreCantonABuscar(String nombreCantonABuscar) {
			this.nombreCantonABuscar = nombreCantonABuscar;
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

		public List<Canton> getLstCanton() {
			return lstCanton;
		}

		public void setLstCanton(List<Canton> lstCanton) {
			this.lstCanton = lstCanton;
		}

		public Boolean getBotonInsertarCantones() {
			return botonInsertarCantones;
		}

		public void setBotonInsertarCantones(Boolean botonInsertarCantones) {
			this.botonInsertarCantones = botonInsertarCantones;
		}

		public Boolean getBotonActualizarCantones() {
			return botonActualizarCantones;
		}

		public void setBotonActualizarCantones(Boolean botonActualizarCantones) {
			this.botonActualizarCantones = botonActualizarCantones;
		}

		public Boolean getBotonEliminarCantones() {
			return botonEliminarCantones;
		}

		public void setBotonEliminarCantones(Boolean botonEliminarCantones) {
			this.botonEliminarCantones = botonEliminarCantones;
		}

		public List<Provincia> getLstProvincia() {
			return lstProvincia;
		}

		public void setLstProvincia(List<Provincia> lstProvincia) {
			this.lstProvincia = lstProvincia;
		}

		public Integer getIdProvincia() {
			return idProvincia;
		}

		public void setIdProvincia(Integer idProvincia) {
			this.idProvincia = idProvincia;
		}

		
	
		
}
