package ec.gob.simintel.commons.datamanager;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import ec.gob.simintel.entidades.Perfil;
import ec.gob.simintel.validadores.UtilRedireccion;

/**
 * Guarda en sesión los datos del Usuario que se logeo.
 * 
 * @author ClearMinds-it
 */
@ManagedBean
@SessionScoped
public class UsuarioDataManager {

	private Perfil perfil;
	private boolean ingreso;

	/**
	 * Constructor vacio. Inicializa.
	 */
	public UsuarioDataManager() {
		this.perfil = new Perfil();
		this.ingreso = false;
	}

	/**
	 * Cierra la sesion del usuario y redirige al Login
	 * 
	 */
	public void logout() {
		Map<String, Object> session = FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap();
		System.out
				.println("****-----****---- ESTO ME TRAE EL MAPA DE SESIONES: "
						+ session);
		// session.remove("permisosDataManager");
		// session.remove("usuarioDataManager");
		for (String clave : session.keySet()) {
			System.out.println("//**//**//**//** elimina: " + clave);
			session.remove(clave);
		}
		System.out.println("MAPA QUE TIENE: " + session);
		UtilRedireccion.redireccionar("Login.jsf");
	}

	/* Getters y Setters */

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public boolean isIngreso() {
		return ingreso;
	}

	public void setIngreso(boolean ingreso) {
		this.ingreso = ingreso;
	}
}
