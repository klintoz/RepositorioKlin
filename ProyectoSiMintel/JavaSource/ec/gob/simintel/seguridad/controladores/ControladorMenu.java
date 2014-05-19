package ec.gob.simintel.seguridad.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.DefaultRequestContext;

import ec.gob.simintel.commons.datamanager.MenuDataManager;
import ec.gob.simintel.commons.datamanager.PaginasDataManager;
import ec.gob.simintel.commons.datamanager.UsuarioDataManager;
import ec.gob.simintel.entidades.Menu;
import ec.gob.simintel.entidades.Pagina;
import ec.gob.simintel.validadores.UtilRedireccion;

/**
 * Controlador para la Entidad Menu.
 */
@ManagedBean
@SessionScoped
public class ControladorMenu {

	private List<Menu> menus;
	private List<Menu> subMenus;
	private Menu menuLogout;
	private boolean menuSiempreAbierto;
	@ManagedProperty("#{paginasDataManager}")
	private PaginasDataManager paginasDataManager;
	@ManagedProperty("#{menuDataManager}")
	private MenuDataManager menuDataManager;
	@ManagedProperty("#{usuarioDataManager}")
	private UsuarioDataManager usuarioDataManager;
	private String pagina;

	/**
	 * Constructor que inicializa atributos.
	 */
	public ControladorMenu() {
		this.menus = new ArrayList<Menu>();
		this.subMenus = new ArrayList<Menu>();
		this.menuLogout = new Menu("Logout", "../imagenes/icons/logout.png",
				"#");
		this.menuSiempreAbierto = false;
	}

	/**
	 * Llena las listas de menus, obteniendo los datos del mapa del
	 * menuDataManager.
	 */
	@PostConstruct
	public void llenar() {
		try {
			menus = menuDataManager.getMapaRolMenu().get(
					usuarioDataManager.getPerfil().getRol().getId());

		} catch (NullPointerException e) {
			menus = null;
		}
		try {
			subMenus = menuDataManager.getMenuClick().getSubMenus();
		} catch (NullPointerException e) {
			subMenus = null;
		}
	}

	/**
	 * Permite repintar el submenu de acuerdo al menu que dimos clic.
	 */
	public void cambiarSubMenu() {
		String redireccion = "#";

		// Permite que si un submenu tiene permisos y el menu no lo tiene,
		// solamente pinte el submenu pero no redirija.
		for (Pagina auxPag : paginasDataManager.getPaginasVisibles()) {
			if (menuDataManager.getMenuClick().getNombre()
					.compareTo(auxPag.getNombre()) == 0) {
				redireccion = menuDataManager.getMenuClick().getUrl()
						.replace("../", "");
				break;
			}
		}

		System.out.println("Variable REDIRECCION: " + redireccion);

		if ((redireccion.compareTo("null") == 0)
				|| (redireccion.compareTo("#") == 0)) {

			subMenus = menuDataManager.getMenuClick().getSubMenus();

			UIComponent.getCurrentComponent(FacesContext.getCurrentInstance())
					.findComponent(" :stkBot")
					.setRendered(pintarSubmenus());

			// Actualiza todo lo que se encuentra dentro del form "frmStk", es
			// decir el submenu.
			DefaultRequestContext.getCurrentInstance().update("frmStk");

			// menuDataManager.setMenuNavegacion(menuDataManager.getMenuClick());

		} else {
			try {
				subMenus = menuDataManager.getMenuClick().getSubMenus();
			} catch (NullPointerException e) {
				subMenus = null;
			} finally {
				UtilRedireccion.redireccionar(redireccion);
				// menuDataManager.setMenuNavegacion(menuDataManager
				// .getMenuClick());
			}
		}
	}

	/**
	 * Permite saber cuando pintar el submenu.
	 * 
	 * @return <code>true</code>, si el submenu a pintar tiene elementos, caso
	 *         contrario <code>false</code>.
	 */
	public boolean pintarSubmenus() {
		if ((subMenus == null) || (subMenus.size() == 0)) {
			return false;
		}
		return true;
	}

	/**
	 * Permite asegurarnos que la lista de menus siempre este llena.
	 * 
	 * @return Lista con objetos del tipo menu, llena si es que antes estaba
	 *         vacia.
	 */
	public List<Menu> getMenus() {
		if (menus == null) {
			llenar();
		}
		return menus;
	}

	/**
	 * Hace que el panel de menú se mantenga abierto aunque se cambie de página,
	 * ademas actualiza el menú.
	 */
	public void mantenerMenuAbierto() {
		if (menuSiempreAbierto) {
			menuSiempreAbierto = false;
		} else {
			menuSiempreAbierto = true;
		}
		// Actualiza el menú.
		DefaultRequestContext.getCurrentInstance().update("frmDock");
	}

	/**
	 * Permite abrir el menú.
	 * 
	 * @param evt
	 *            Evento que se recibe de la página.
	 */
	/*public void abrirMenu(ActionEvent evt) {
		DefaultRequestContext.getCurrentInstance().execute("menu.show()");

	}*/

	/**
	 * Permite cerrar el menú y lo actualiza.
	 * 
	 * @param evt
	 *            Evento que se recibe de la página.
	 */
	public void cerrarMenu(ActionEvent evt) {
		menuSiempreAbierto = false;
		DefaultRequestContext.getCurrentInstance().execute("menu.hide()");
		// Actualiza el menú para que el icono de "siempreAbierto" se actualice.
		DefaultRequestContext.getCurrentInstance().update("frmDock");
	}
	
	public void navegar(){
		System.out.println("Redirecciona a "+pagina);
		UtilRedireccion.redireccionar(pagina);
	}

	/* Getter y Setters */

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	public List<Menu> getSubMenus() {
		return subMenus;
	}

	public void setSubMenus(List<Menu> subMenus) {
		this.subMenus = subMenus;
	}

	public PaginasDataManager getPaginasDataManager() {
		return paginasDataManager;
	}

	public void setPaginasDataManager(PaginasDataManager paginasDataManager) {
		this.paginasDataManager = paginasDataManager;
	}

	public MenuDataManager getMenuDataManager() {
		return menuDataManager;
	}

	public void setMenuDataManager(MenuDataManager menuDataManager) {
		this.menuDataManager = menuDataManager;
	}

	public UsuarioDataManager getUsuarioDataManager() {
		return usuarioDataManager;
	}

	public void setUsuarioDataManager(UsuarioDataManager usuarioDataManager) {
		this.usuarioDataManager = usuarioDataManager;
	}

	public Menu getMenuLogout() {
		return menuLogout;
	}

	public void setMenuLogout(Menu menuLogout) {
		this.menuLogout = menuLogout;
	}

	public boolean isMenuSiempreAbierto() {
		return menuSiempreAbierto;
	}

	public void setMenuSiempreAbierto(boolean menuSiempreAbierto) {
		this.menuSiempreAbierto = menuSiempreAbierto;
	}

	public String getPagina() {
		return pagina;
	}

	public void setPagina(String pagina) {
		this.pagina = pagina;
	}
	
}
