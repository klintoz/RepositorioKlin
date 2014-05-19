package ec.gob.simintel.seguridad.controladores;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.DefaultRequestContext;

import ec.gob.simintel.commons.datamanager.UsuarioDataManager;
import ec.gob.simintel.controladores.commons.Constantes;
import ec.gob.simintel.controladores.commons.MensajesError;
import ec.gob.simintel.controladores.commons.MensajesInformacion;
import ec.gob.simintel.controladores.commons.GeneradorMensajes;
import ec.gob.simintel.entidades.Persona;
import ec.gob.simintel.entidades.Usuario;
import ec.gob.simintel.seguridad.servicios.ServicioPersona;
import ec.gob.simintel.seguridad.servicios.ServicioUsuario;
import ec.gob.simintel.validadores.UtilRedireccion;

/**
 * Controlador para la PaginaUsuario.
 */
@ManagedBean
@ViewScoped
public class ControladorUsuario {

	private Usuario usuario;
	private Persona persona;
	private Usuario auxUsuario;
	private List<Usuario> usuarios;
	
	@EJB
	private ServicioUsuario srvUsuario;
	@EJB
	private ServicioPersona srvPersona;
	@ManagedProperty("#{usuarioDataManager}")
	private UsuarioDataManager usuarioDataManager;

	/**
	 * Inicializa los atributos.
	 */
	public ControladorUsuario() {
		usuario = new Usuario();
		auxUsuario = new Usuario();
		persona = new Persona();
	}

	
	
	
	/**
	 * Llena la lista de usuarios.
	 */
	@PostConstruct
	private void init() {
		usuarios = srvUsuario.buscarTodos();
	}
	
	/**
	 * Permite editar un usuario e insertarlo en la BDD, primero comprobando la
	 * existencia de duplicados.
	 */
	public void editar() { 
		if (srvUsuario.existeUsuarioNombre(usuario)) {
			GeneradorMensajes.mostrarMensajeError(MensajesError.USUARIO_REPETIDO);
			// Para evitar que se actualice la tabla
			cancelarEditar();
			return;
		} else {
			srvPersona.actualizar(persona);
			usuario.setPersona(persona);
			usuario.setFechaModificacion(new Date());
			usuario.setPerfilModificacion(usuarioDataManager.getPerfil().getId());
			srvUsuario.actualizar(usuario);
			DefaultRequestContext.getCurrentInstance().execute("dlgEditUsuario.hide()");
			GeneradorMensajes.mostrarMensajeInformacion(MensajesInformacion.USUARIO_ACTUALIZADO);
			return;
		}
	}
	
	/**
	 * Abre el dialogo de editar y setea los objetos adquiridos de la página a
	 * un auxiliar de la clase.
	 */
	public void abrirEditar() {
		auxUsuario = new Usuario();
		this.auxUsuario = (Usuario) usuario.clone();
		persona = usuario.getPersona();
		DefaultRequestContext.getCurrentInstance().execute("dlgEditUsuario.show()");
	}
	
	/**
	 * Cierra el dialogo de editar y setea los objetos adquiridos del auxiliar a
	 * la clase.
	 */
	public void cancelarEditar() {
		usuario.setUsuario(auxUsuario.getUsuario());
		usuario.setContrasenia(auxUsuario.getUsuario());
		usuario.setFechaCreacion(auxUsuario.getFechaCreacion());
		usuario.setFechaModificacion(auxUsuario.getFechaModificacion());
		usuario.setPerfilCreacion(auxUsuario.getPerfilCreacion());
		usuario.setPerfilModificacion(auxUsuario.getPerfilModificacion());
		usuario.setPersona(auxUsuario.getPersona());
	}
	
	
	
	/**
	 * Permite agregar un usuario e insertarla en la BDD, primero comprobando la
	 * existencia de duplicados.
	 */
	public void agregar() {
		if (srvUsuario.existeUsuarioNombre(usuario.getUsuario())) {
			GeneradorMensajes.mostrarMensajeError(MensajesError.ERROR_ROL_NOMBRE_REPETIDO);
			return;
		} else {
			srvPersona.insertar(persona);
			usuario.setPersona(persona);
			usuario.setFechaCreacion(new Date());
			usuario.setPerfilCreacion(usuarioDataManager.getPerfil().getId());
			srvUsuario.insertar(usuario);
			usuarios.add(usuario);
			cancelarAgregar();
			GeneradorMensajes.mostrarMensajeInformacion(MensajesInformacion.USUARIO_INSERTADO);
			return;
		}
	}
	
	/**
	 * Abre el dialogo para agregar un nuevo Usuario.
	 */
	public void abrirAgregar() {
		usuario = new Usuario();
		persona = new Persona();
		DefaultRequestContext.getCurrentInstance().execute("dlgAgreUsuario.show()");
	}
	
	/**
	 * Permite cerrar el dialogo de creación e instanciar un nuevo objeto
	 * <code>Usuario</code>.
	 */
	public void cancelarAgregar() {
		usuario = new Usuario();
		persona = new Persona();
		DefaultRequestContext.getCurrentInstance().execute("dlgAgreUsuario.hide()");
	}
	
	
	
	

	/**
	 * Permite eliminar un usuario de la BDD, además actualiza el mapa de
	 * menu/rol.
	 */
	public void eliminar() {
		try {
			persona = usuario.getPersona();
			srvUsuario.eliminar(usuario);
			srvPersona.eliminar(persona);
			usuarios.remove(usuario);
			GeneradorMensajes.mostrarMensajeInformacion(MensajesInformacion.USUARIO_ELIMINADO);
		} catch (Exception e) {
			GeneradorMensajes.mostrarMensajeError(MensajesError.ERROR_USUARIO_ELIMINADO);
			System.out.println(e.toString());
		} finally {
			cancelarEliminar();
		}
	}
	
	/**
	 * Permite cerrar el dialogo de eliminación e instanciar un nuevo objeto
	 * <code>Usuario</code>.
	 */
	public void cancelarEliminar() {
		this.usuario = new Usuario();
		this.persona = new Persona();
		DefaultRequestContext.getCurrentInstance().execute("dlgElimUsuario.hide()");
	}
 
	public void gestionPerfiles() {
		UtilRedireccion.redireccionar(Constantes.URL_PERFIL);
	}

	
	
	/* Getters y Setters */
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public UsuarioDataManager getUsuarioDataManager() {
		return usuarioDataManager;
	}

	public void setUsuarioDataManager(UsuarioDataManager usuarioDataManager) {
		this.usuarioDataManager = usuarioDataManager;
	}

}
