package ec.gob.simintel.seguridad.controladores;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.DefaultRequestContext;
import org.primefaces.event.SelectEvent;

import ec.gob.simintel.commons.datamanager.UsuarioDataManager;
import ec.gob.simintel.controladores.commons.Constantes;
import ec.gob.simintel.controladores.commons.MensajesError;
import ec.gob.simintel.controladores.commons.MensajesInformacion;
import ec.gob.simintel.controladores.commons.GeneradorMensajes;
import ec.gob.simintel.entidades.Pagina;
import ec.gob.simintel.entidades.Rol;
import ec.gob.simintel.seguridad.servicios.ServicioPagina;
import ec.gob.simintel.seguridad.servicios.ServicioRol;
import ec.gob.simintel.validadores.UtilRedireccion;

/**
 * Controlador para la PaginaRol.
 */
@ManagedBean
@ViewScoped
public class ControladorRol {

	private Rol rol;
	private Pagina pagina;
	private Rol auxRol;
	private List<Rol> roles;
	private List<Pagina> paginas;
	@EJB
	private ServicioRol srvRol;
	@EJB
	private ServicioPagina srvPagina;
	@ManagedProperty("#{usuarioDataManager}")
	private UsuarioDataManager usuarioDataManager;

	/**
	 * Inicializa los atributos.
	 */
	public ControladorRol() {
		rol = new Rol();
		auxRol = new Rol();
	}
 
	/**
	 * Llena la lista de roles.
	 */
	@PostConstruct
	private void init() {
		roles = srvRol.buscarTodos();
		paginas = srvPagina.buscarTodos();
	}

	/**
	 * Permite editar un rol e insertarla en la BDD, primero comprobando la
	 * existencia de duplicados.
	 */
	public void editar() {
		if (srvRol.existeRolNombre(rol)) {
			GeneradorMensajes
					.mostrarMensajeError(MensajesError.ERROR_ROL_NOMBRE_REPETIDO);
			// Para evitar que se actualice la tabla
			cancelarEditar();
			return;
		} else {
			rol.setFechaModificacion(new Date());
			rol.setPerfilModificacion(usuarioDataManager.getPerfil().getId());
			srvRol.actualizar(rol);
			GeneradorMensajes
					.mostrarMensajeInformacion(MensajesInformacion.ROL_ACTUALIZO);
			return;
		}
	}

	/**
	 * Permite agregar un rol e insertarla en la BDD, primero comprobando la
	 * existencia de duplicados.
	 */
	public void agregar() {
		if (srvRol.existeRolNombre(rol.getNombre())) {
			GeneradorMensajes
					.mostrarMensajeError(MensajesError.ERROR_ROL_NOMBRE_REPETIDO);
			return;
		} else if (rol.getPagina().getNombre() == null) {
			GeneradorMensajes
					.mostrarMensajeError(MensajesError.ERROR_ROL_SELECCION_REDIRECCION);
		} else {
			rol.setFechaCreacion(new Date());
			rol.setPerfilCreacion(usuarioDataManager.getPerfil().getId());
			srvRol.insertar(rol);
			roles.add(rol);
			cancelarAgregar();
			GeneradorMensajes
					.mostrarMensajeInformacion(MensajesInformacion.ROL_CREADO);
			return;
		}
	}

	/**
	 * Permite eliminar una pagina de la BDD, además actualiza el mapa de
	 * menu/rol.
	 */
	public void eliminar() {
		try {
			srvRol.eliminar(rol);
			roles.remove(rol);
			GeneradorMensajes
					.mostrarMensajeInformacion(MensajesInformacion.ROL_ELIMINADO);
		} catch (Exception e) {
			GeneradorMensajes
					.mostrarMensajeError(MensajesError.ERROR_ROL_ELIMINADO);
			System.out.println(e.toString());
		} finally {
			cancelarEliminar();
		}
	}

	public void asignarPermisos() {
		UtilRedireccion.redireccionar(Constantes.URL_PAGINA_ROL);
	}

	/**
	 * Abre el dialogo para agregar un nuevo Rol.
	 */
	public void abrirAgregar() {
		rol = new Rol();
		DefaultRequestContext.getCurrentInstance().execute("dlgAgreRol.show()");
	}

	/**
	 * Abre el dialogo de editar y setea los objetos adquiridos de la página a
	 * un auxiliar de la clase.
	 */
	public void abrirEditar() {
		auxRol = new Rol();
		this.auxRol = (Rol) rol.clone();
		DefaultRequestContext.getCurrentInstance().execute("dlgEditRol.show()");
	}

	/**
	 * Cierra el dialogo de editar y setea los objetos adquiridos del auxiliar a
	 * la clase.
	 */
	public void cancelarEditar() {
		rol.setFechaCreacion(auxRol.getFechaCreacion());
		rol.setFechaModificacion(auxRol.getFechaModificacion());
		rol.setNombre(auxRol.getNombre());
		rol.setPagina(auxRol.getPagina());
		rol.setPerfilCreacion(auxRol.getPerfilCreacion());
		rol.setPerfilModificacion(auxRol.getPerfilModificacion());
		DefaultRequestContext.getCurrentInstance().execute("dlgEditRol.hide()");
	}

	/**
	 * Permite cerrar el dialogo de eliminación e instanciar un nuevo objeto
	 * <code>Rol</code>.
	 */
	public void cancelarEliminar() {
		this.rol = new Rol();
		DefaultRequestContext.getCurrentInstance().execute("dlgElimRol.hide()");
	}

	/**
	 * Permite cerrar el dialogo de creación e instanciar un nuevo objeto
	 * <code>Rol</code>.
	 */
	public void cancelarAgregar() {
		this.rol = new Rol();
		DefaultRequestContext.getCurrentInstance().execute("dlgAgreRol.hide()");
	}

	/**
	 * Permite usar una dataTable con al posibilidad de seleccionar mediante
	 * click.
	 * 
	 * @param event
	 *            Evento que usa el componente, y el Ajax.
	 */
	public void filaPagina(SelectEvent event) {
		rol.setPagina((Pagina) event.getObject());
	}

	/**
	 * Permite realizar la redirección a la página solicitada.
	 */
	public void gestionPaginas() {
		UtilRedireccion.redireccionar(Constantes.URL_PAGINA);
	}

	/* Getters y Setters */

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

	public UsuarioDataManager getUsuarioDataManager() {
		return usuarioDataManager;
	}

	public void setUsuarioDataManager(UsuarioDataManager usuarioDataManager) {
		this.usuarioDataManager = usuarioDataManager;
	}

	public List<Pagina> getPaginas() {
		return paginas;
	}

	public void setPaginas(List<Pagina> paginas) {
		this.paginas = paginas;
	}

	public Pagina getPagina() {
		return pagina;
	}

	public void setPagina(Pagina pagina) {
		this.pagina = pagina;
	}

}
