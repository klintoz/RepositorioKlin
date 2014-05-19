package ec.gob.simintel.seguridad.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import ec.gob.simintel.commons.datamanager.UsuarioDataManager;
import ec.gob.simintel.controladores.commons.Constantes;
import ec.gob.simintel.controladores.commons.GeneradorMensajes;
import ec.gob.simintel.entidades.Rol;
import ec.gob.simintel.entidades.Usuario;
import ec.gob.simintel.seguridad.servicios.ServicioPerfil;
import ec.gob.simintel.seguridad.servicios.ServicioRol;
import ec.gob.simintel.seguridad.servicios.ServicioUsuario;
import ec.gob.simintel.validadores.UtilRedireccion;


@ManagedBean
@ViewScoped
public class ControladorPerfil {
	private Rol rol;
	private Usuario usuario;
	@EJB
	private ServicioUsuario servicioUsuario;
	private String usernameUsuario;

	private List<Rol> roles;

	@ManagedProperty("#{usuarioDataManager}")
	private UsuarioDataManager usuarioDataManager;

	private List<Rol> rolAntiguos;

	@EJB
	private ServicioRol servicioRol;

	@EJB
	private ServicioPerfil servicioPerfil;

	private boolean habilitarTblRol;

	public ControladorPerfil() {
		rol = new Rol();
		usuario = new Usuario();
		roles = new ArrayList<Rol>();
		rolAntiguos = new ArrayList<Rol>();
	}

	public void cargarRoles() {
		roles = servicioRol.buscarRolUsuario(usuario);
		rolAntiguos = servicioRol.buscarRolUsuario(usuario);
		System.out.println("roooooooles de " + usuario.getUsuario()
				+ roles.size());
	}

	public void buscarUsuario() {
		try {
			usuario = servicioUsuario.buscarUsuario(usernameUsuario);
			usernameUsuario = "";
			habilitarTblRol = true;
			cargarRoles();
			GeneradorMensajes.mostrarMensajeInformacion("Usuario encontrado");
		} catch (Exception e) {
			GeneradorMensajes.mostrarMensajeError(e.getMessage());
		}
	}

	public void guardarCambios() {
		System.out.println("********nuevos***********");
		for (Rol r : roles) {
			System.out.println(r.isRolSeleccionado());
		}
		System.out.println("--------antiguos--------");
		for (Rol rol : rolAntiguos) {
			System.out.println(rol.isRolSeleccionado());
		}
		System.out.println("**************************");
		try {
			servicioPerfil.modificarPerfil(roles, rolAntiguos,
					usuarioDataManager.getPerfil().getId(), usuario);
			cargarRoles();
			GeneradorMensajes.mostrarMensajeInformacion("Perfiles Actualizados");
		} catch (Exception e) {
			GeneradorMensajes.mostrarMensajeError(e.getMessage());
		}
	}

	public void buscarNuevo() {
		habilitarTblRol = false;
		roles = new ArrayList<Rol>();
		rolAntiguos = new ArrayList<Rol>();
	}

	public void redireccionGestionPagina() {
		UtilRedireccion.redireccionar(Constantes.URL_PAGINA);
	}

	public void redireccionGestionRol() {
		UtilRedireccion.redireccionar(Constantes.URL_ROL);
	}

	public void redireccionAsignarPermisos() {
		UtilRedireccion.redireccionar(Constantes.URL_PAGINA_ROL);
	}

	/* GETTERS Y SETTERS */
	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getUsernameUsuario() {
		return usernameUsuario;
	}

	public void setUsernameUsuario(String usernameUsuario) {
		this.usernameUsuario = usernameUsuario;
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

	public List<Rol> getRolAntiguos() {
		return rolAntiguos;
	}

	public void setRolAntiguos(List<Rol> rolAntiguos) {
		this.rolAntiguos = rolAntiguos;
	}

	public boolean isHabilitarTblRol() {
		return habilitarTblRol;
	}

	public void setHabilitarTblRol(boolean habilitarTblRol) {
		this.habilitarTblRol = habilitarTblRol;
	}

}
