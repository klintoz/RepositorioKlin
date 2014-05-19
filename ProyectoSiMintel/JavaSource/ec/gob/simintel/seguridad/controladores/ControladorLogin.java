package ec.gob.simintel.seguridad.controladores;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.DefaultRequestContext;

import ec.gob.simintel.commons.datamanager.PaginasDataManager;
import ec.gob.simintel.commons.datamanager.UsuarioDataManager;
import ec.gob.simintel.controladores.commons.MensajesError;
import ec.gob.simintel.controladores.commons.GeneradorMensajes;
import ec.gob.simintel.entidades.Perfil;
import ec.gob.simintel.entidades.Usuario;
import ec.gob.simintel.seguridad.servicios.ServicioLogin;
import ec.gob.simintel.seguridad.servicios.ServicioMenu;
import ec.gob.simintel.seguridad.servicios.ServicioPaginaRol;
import ec.gob.simintel.seguridad.servicios.ServicioPerfil;
import ec.gob.simintel.seguridad.servicios.ServicioUsuario;
import ec.gob.simintel.validadores.UtilRedireccion;

/**
 * Maneja los eventos de la página Login. 
 */
@ManagedBean
@ViewScoped
public class ControladorLogin {

	@ManagedProperty("#{usuarioDataManager}")
	private UsuarioDataManager usuarioDataManager;
	@ManagedProperty("#{paginasDataManager}")
	private PaginasDataManager paginasDataManager;

	private Usuario usuario;

	private List<Perfil> perfiles;
	private int idPerfSeleccionado;
	private Perfil perfilSeleccionado;
	@EJB
	private ServicioLogin srvLogin;
	@EJB
	private ServicioPerfil srvPerfil;
	@EJB
	private ServicioUsuario srvUsuario;
	@EJB
	private ServicioPaginaRol srvPaginaRol;
	@EJB
	private ServicioMenu srvMenu;

	/**
	 * Controlador que inicializa al <code>usuario</code>
	 */
	public ControladorLogin() {
		usuario = new Usuario();
	}

	@PostConstruct
	public void estaLogeado() {
		if (usuarioDataManager.getPerfil().getRol() != null) {
			UtilRedireccion.redireccionar(usuarioDataManager.getPerfil()
					.getRol().getPagina().getUrl());
		}
	}

	/**
	 * Permite autenticar al usuario con las credenciales correspondientes, y
	 * llena el DataManager correspondiente.
	 */
	public void autenticar() {
		if (srvLogin.autenticar(usuario)) {
			usuarioDataManager.getPerfil().setUsuario(
					srvUsuario.buscarUsuarioUserPass(usuario));
			// usuarioDataManager.getPersonaCurso().setPersona(
			// usuarioDataManager.getPerfil().getUsuario().getPersona());
			seleccionarPerfil();
			// parqueaderoDM.setParqueadero(srvParqueadero.buscarPorID(srvUsuario.buscarUsuarioUserPass(usuario).getIdParqueadero));
		} else {
			usuario.setContrasenia("");
			GeneradorMensajes.mostrarMensajeError(MensajesError.LOGIN_ERROR);
		}

	}

	/**
	 * Busca el perfil en la BDD de acuerdo al usuario autenticado usando el
	 * servicio correspondiente.
	 * 
	 */
	public void perfilSelecionado() {
		perfilSeleccionado = srvPerfil.buscarPorId(idPerfSeleccionado);
		usuarioDataManager.setPerfil(perfilSeleccionado);
		// Llena el manager con las paginas que puede ver el Perfil.
		paginasDataManager.setPaginasVisibles(srvPaginaRol
				.buscarPagRol(usuarioDataManager.getPerfil()));
		// Setea la pagina por defecto del perfil dentro de la lista
		paginasDataManager.getPaginasVisibles().add(
				usuarioDataManager.getPerfil().getRol().getPagina());

		UtilRedireccion.redireccionar(perfilSeleccionado.getRol().getPagina()
				.getUrl());
	}

	/**
	 * Llena los datos en el combo de seleccion de perfiles, y evalua la
	 * longitud de la lista.
	 */
	private void seleccionarPerfil() {
		perfiles = srvPerfil.buscarPerfPorUsuario(usuarioDataManager
				.getPerfil().getUsuario());
		if (perfiles.size() == 0) {
			GeneradorMensajes
					.mostrarMensajeError(MensajesError.LOGIN_USR_SIN_PERFIL);
			usuario = new Usuario();
		} else if (perfiles.size() == 1) {
			usuarioDataManager.setPerfil(perfiles.get(0));

			// Llena el manager con las paginas que puede ver el Perfil.
			paginasDataManager.setPaginasVisibles(srvPaginaRol
					.buscarPagRol(usuarioDataManager.getPerfil()));
			// Setea la pagina por defecto del perfil dentro de la lista
			paginasDataManager.getPaginasVisibles().add(
					usuarioDataManager.getPerfil().getRol().getPagina());

			UtilRedireccion.redireccionar(perfiles.get(0).getRol().getPagina()
					.getUrl());

		} else if (perfiles.size() > 1) {
			mostrarDlgPerfil();
		}

	}

	/**
	 * Muestra el dialogo de seleccion de perfiles
	 */
	public void mostrarDlgPerfil() {
		DefaultRequestContext.getCurrentInstance().execute(
				"dlgSeleccionePerfil.show()");
	}

	/**
	 * Oculta el dialogo de seleccion de perfiles e instancia un nuevo objeto
	 * del tipo <code>usuario</code>
	 */
	public void ocultarDlgPerfil() {
		usuario = new Usuario();
		DefaultRequestContext.getCurrentInstance().execute(
				"dlgSeleccionePerfil.hide()");
		usuarioDataManager.logout();
	}

	// Getters y Setters
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public UsuarioDataManager getUsuarioDataManager() {
		return usuarioDataManager;
	}

	public void setUsuarioDataManager(UsuarioDataManager usuarioDataManager) {
		this.usuarioDataManager = usuarioDataManager;
	}

	public List<Perfil> getPerfiles() {
		return perfiles;
	}

	public void setPerfiles(List<Perfil> perfiles) {
		this.perfiles = perfiles;
	}

	public int getIdperfSeleccionado() {
		return idPerfSeleccionado;
	}

	public int getIdPerfSeleccionado() {
		return idPerfSeleccionado;
	}

	public void setIdPerfSeleccionado(int idPerfSeleccionado) {
		this.idPerfSeleccionado = idPerfSeleccionado;
	}

	public Perfil getPerfilSeleccionado() {
		return perfilSeleccionado;
	}

	public void setPerfilSeleccionado(Perfil perfilSeleccionado) {
		this.perfilSeleccionado = perfilSeleccionado;
	}

	public PaginasDataManager getPaginasDataManager() {
		return paginasDataManager;
	}

	public void setPaginasDataManager(PaginasDataManager paginasDataManager) {
		this.paginasDataManager = paginasDataManager;
	}
}
