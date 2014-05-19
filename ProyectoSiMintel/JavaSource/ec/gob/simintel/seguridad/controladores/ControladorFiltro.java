package ec.gob.simintel.seguridad.controladores;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;

import ec.gob.simintel.commons.datamanager.PaginasDataManager;
import ec.gob.simintel.commons.datamanager.UsuarioDataManager;
import ec.gob.simintel.entidades.Pagina;
import ec.gob.simintel.validadores.UtilRedireccion;

/**
 * Permite acceder o no a una URL de acuerdo a los permisos del usuario
 */
@ManagedBean
@ViewScoped
public class ControladorFiltro {
	private String filtro;
	@ManagedProperty("#{paginasDataManager}")
	private PaginasDataManager paginasDataManager;
	@ManagedProperty("#{usuarioDataManager}")
	private UsuarioDataManager usuarioDataManager;
	private static final Logger logger = Logger
			.getLogger(ControladorFiltro.class.getCanonicalName());

	String login = "Login.jsf";
	String noPermitido = "NoPermitido.jsf";

	/**
	 * * Invoca al filtro para determinar si el usuario puede o no ver la
	 * página. Si no tiene permisos redirecciona a la página de login
	 * 
	 * @return cualquier cadena
	 */
	public String getFiltro() {
		boolean exito = false;
		boolean entrar = false;

		Map<String, Object> session = FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap();
		for (String clave : session.keySet()) {
			if (clave.equals("usuarioDataManager")) {
				if (((UsuarioDataManager) session.get("usuarioDataManager"))
						.getPerfil().getRol() != null) {
					entrar = true;
					break;
				}
			}
		}

		if (entrar) {
			if (paginasDataManager.getPaginasVisibles() != null) {
				logger.debug("Ingreso al control Lista Paginas diferente de NULL");
				for (Pagina m : paginasDataManager.getPaginasVisibles()) {

					if ((UtilRedireccion.getContextRoot() + "/" + m.getUrl())
							.equals(getRequestURL())
							|| ((UtilRedireccion.getContextRoot() + "/" + m
									.getUrl()).equals(usuarioDataManager
									.getPerfil().getRol().getPagina().getUrl()))) {
						exito = true;
						break;
					}
				}

				if (!exito) {
					logger.debug("Ingreso al control de exito FALSE");
					if (getRequestURL().equals(
							UtilRedireccion.getContextRoot() + "")) {
						UtilRedireccion.redireccionar(login);
					} else {
						UtilRedireccion.redireccionar(noPermitido);
					}
				}

			} else {
				if (!(getRequestURL().equals(UtilRedireccion.getContextRoot()
						+ "/"
						+ usuarioDataManager.getPerfil().getRol().getPagina()
								.getUrl()))) {
					UtilRedireccion.redireccionar(noPermitido);
				}
			}
		} else {
			logger.log(Level.INFO, "No está logueado");
			UtilRedireccion.redireccionar(login);
		}
		return "";
	}

	/**
	 * * Obtiene el url de la petición que llega.
	 * 
	 * @return url de la petición
	 */
	public String getRequestURL() {
		Object request = FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		if (request instanceof HttpServletRequest) {
			return ((HttpServletRequest) request).getRequestURI().toString();
		} else {
			return "";
		}
	}

	/**
	 * Si el usuario tiene un usuario en sesión retorna dicho usuario, caso
	 * contrario redirecciona a la página de login.
	 * 
	 * @return El datamanager del usuario o null si no tiene sesión abierta.
	 */
	public UsuarioDataManager getUsuarioDataManager() {
		if (usuarioDataManager == null) {
			return usuarioDataManager;
		} else {
			UtilRedireccion.redireccionar(login);
		}
		return usuarioDataManager;
	}

	/**
	 * * Obtiene el menú del usuario, si no tiene menú retorna null
	 * 
	 * @return El datamanager de las paginas o null si no está logueado y
	 *         redirecciona a la página de Login.
	 */
	public PaginasDataManager getPaginasDataManager() {
		if (paginasDataManager.getPaginasVisibles().size() != 0) {
			return paginasDataManager;
		}
		UtilRedireccion.redireccionar(login);
		return null;
	}

	public void setPaginasDataManager(PaginasDataManager paginasDataManager) {
		this.paginasDataManager = paginasDataManager;
	}

	public void setUsuarioDataManager(UsuarioDataManager usuarioDataManager) {
		this.usuarioDataManager = usuarioDataManager;
	}
}
