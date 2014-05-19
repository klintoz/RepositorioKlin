package ec.gob.simintel.commons.datamanager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ec.gob.simintel.entidades.Menu;
import ec.gob.simintel.seguridad.servicios.ServicioMenu;

/**
 * Guarda en sesión los datos de los menús del Usuario que se logeo.
 * 
 * @author ClearMinds-it
 */
@ManagedBean
@SessionScoped
public class MenuDataManager {

	private List<Menu> menus;
	// El menu al que se le da click.
	private Menu menuClick;
	@EJB
	private ServicioMenu srvMenu;
	private Map<Integer, List<Menu>> mapaRolMenu;
	private Menu menuNavegacion;

	/**
	 * Constructor. Inicializa.
	 */
	public MenuDataManager() {
		this.menus = new ArrayList<Menu>();
		this.menuNavegacion = new Menu();
		// Para poder pintar inicialmente la navegacion sin errores.
		// this.menuNavegacion.getSubMenus().add(0, new Menu());
	}

	/**
	 * Obtiene los menús del servicio para llenar el mapa.
	 */
	@PostConstruct
	public void obtenerMapa() {
		this.mapaRolMenu = srvMenu.getMapaRolMenu();
	}

	/* Getters y setters */
	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	public Map<Integer, List<Menu>> getMapaRolMenu() {
		return mapaRolMenu;
	}

	public void setMapaRolMenu(Map<Integer, List<Menu>> mapaRolMenu) {
		this.mapaRolMenu = mapaRolMenu;
	}

	public Menu getMenuNavegacion() {
		return menuNavegacion;
	}

	public void setMenuNavegacion(Menu menuNavegacion) {
		this.menuNavegacion = menuNavegacion;
	}

	public Menu getMenuClick() {
		return menuClick;
	}

	public void setMenuClick(Menu menuClick) {
		this.menuClick = menuClick;
	}
}
