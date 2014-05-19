package ec.gob.simintel.seguridad.servicios;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import ec.gob.simintel.controladores.commons.Constantes;
import ec.gob.simintel.controladores.commons.MensajesError;
import ec.gob.simintel.entidades.Menu;
import ec.gob.simintel.entidades.Pagina;
import ec.gob.simintel.entidades.PaginaRol;
import ec.gob.simintel.servicios.commons.ServicioBase;

/**
 * Servicio para la Entidad Menu. 
 */
@Singleton
public class ServicioMenu extends ServicioBase<Menu> {

	private List<Menu> menus;
	// El integer es la id_rol, y tambien tiene una lista de menus.
	private Map<Integer, List<Menu>> mapaRolMenu;
	private HashSet<String> iconos;
	@EJB
	ServicioPaginaRol srvPaginaRol;

	/**
	 * Inicializa las listas de los menus y submenus.
	 */
	public ServicioMenu() {
		super(Menu.class, ServicioMenu.class);
		mapaRolMenu = new HashMap<Integer, List<Menu>>();
		menus = new ArrayList<Menu>();
		iconos = new HashSet<String>();
	}

	/**
	 * Llena el mapa de menus.
	 * 
	 */
	@PostConstruct
	public void llenarMapa() {
		if (mapaRolMenu.size() != 0) {
			mapaRolMenu = new HashMap<Integer, List<Menu>>();
			menus = new ArrayList<Menu>();
		}
		// Obtiene todas las paginas disponibles de todos los roles.
		for (PaginaRol auxPagRol : srvPaginaRol.buscarPagRol()) {
			LOG.debug("----> ID:" + auxPagRol.getRol().getId()
					+ " NOMBRE PAGINA: " + auxPagRol.getPagina().getNombre());
		}

		// Agrega TODOS los iconos de la carpeta a la lista.
		ServletContext servletContext = (ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext();
		String realPath = (String) servletContext
				.getRealPath(Constantes.DIRECTORIO_ICONOS);

		File f = new File(realPath);
		if (f.exists())
			for (String auxIcon : f.list()) {
				if (!iconos.contains(auxIcon)) {
					iconos.add("imagenes/icons/" + auxIcon);
					LOG.debug("--> Agregado Icono: " + auxIcon);

				}
			}
		else
			LOG.debug(MensajesError.NO_DIRECTORIO);

		// Itera la lista de paginas disponibles para los roles, y agrega al
		// mapa las ids de los roles, para luego llenarlos.
		for (PaginaRol auxPagRol : srvPaginaRol.buscarPagRol()) {

			// Si no tiene menus, los agrega, de acuerdo al rol.
			if (mapaRolMenu.get(auxPagRol.getRol().getId()) == null) {
				mapaRolMenu.put(auxPagRol.getRol().getId(),
						llenarMenuDelRol(auxPagRol.getRol().getId()));
			}
		}
	}

	/**
	 * Llena el menu de un rol establecido.
	 * 
	 * @param idRol
	 *            Id del rol a ser llenado.
	 * @return Lista con los objetos del tipo <code>Menu</code>.
	 */
	public List<Menu> llenarMenuDelRol(int idRol) {
		List<PaginaRol> paginasRol = srvPaginaRol.buscarPagRol(idRol);

		// Se instancia de nuevo en caso de que el método sea llamado mas de una
		// vez, para evitar datos basura o duplicados.
		this.menus = new ArrayList<Menu>();
		LOG.debug("*** COMENZO A LLENAR MENUS PARA EL ROL " + idRol + " ***");
		for (PaginaRol auxPagRol : paginasRol) {
			if (auxPagRol.getPagina().getEsMenu()) {
				if (menus.contains(new Menu(auxPagRol.getPagina().getNombre(),
						"../" + auxPagRol.getPagina().getIcon(), auxPagRol
								.getPagina().getUrl()))) {
					LOG.debug("VA A ENTRAR AL CONTINUE POR QUE YA EXISTE ---> "
							+ auxPagRol.getPagina().getNombre());
					continue;
				} else {
					try {
						// Trata de instanciar un menu Padre.
						// Si esto no lanza error significa que es un Menu Hijo.
						new Menu(auxPagRol.getPagina().getPagina().getNombre(),
								auxPagRol.getPagina().getPagina().getIcon(),
								("../" + auxPagRol.getPagina().getPagina()
										.getUrl()), auxPagRol.getPagina()
										.getPagina().getOrden());

						LOG.debug("Info que deberia tener el menu padre. ----> "
								+ auxPagRol.getPagina().getPagina().getNombre());
						// Si tiene un hija.
						LOG.debug("---> Es un hijo.");
						LOG.debug("---> " + auxPagRol.getPagina().getNombre());

						// Envia la pagina hija.
						llenarSubMenus(auxPagRol.getPagina());
					} catch (NullPointerException e) {
						// Significa que es PADRE y no a sido agregado antes.
						LOG.debug("Menu PADRE AGREGADO!!!"
								+ auxPagRol.getPagina().getNombre());

						menus.add(new Menu(auxPagRol.getPagina().getNombre(),
								"../" + auxPagRol.getPagina().getIcon(), "../"
										+ auxPagRol.getPagina().getUrl(),
								auxPagRol.getPagina().getOrden()));

						// Como es padre, puede que tenga SubMenus, entonces
						// inicializa una lista.
						menus.get(
								menus.indexOf(new Menu(auxPagRol.getPagina()
										.getNombre(), "../"
										+ auxPagRol.getPagina().getIcon(),
										"../" + auxPagRol.getPagina().getUrl(),
										auxPagRol.getPagina().getOrden())))
								.setSubMenus(new ArrayList<Menu>());
					}

				}

			}
		}
		// Ordena la lista, de acuerdo a la sobreescritura en el metodo
		// compareTo, de la entidad Menu.
		Collections.sort(this.menus);
		// Corrige el ID de cada uno de acuerdo al ordenamiento que se dio.
		if (!menus.isEmpty()) {
			for (int i = 0; i < menus.size(); i++) {
				menus.get(i).setId(i);

				LOG.debug("DESPUES ----->  ID en Lista(param): "
						+ menus.get(i).getId() + " ORDEN:"
						+ menus.get(i).getOrden() + " MENU: "
						+ menus.get(i).getNombre());
			}
			return this.menus;
		}
		return null;
	}

	/**
	 * Llena los SubMenus.
	 * 
	 * @param pagina
	 *            La pagina que es este caso tambien es menu y es el hijo.
	 * @param posicion
	 *            Indica la posicion dentro de la lista de menus del padre al
	 *            cual se le quiere agregar el hijo.
	 * 
	 */
	public void llenarSubMenus(Pagina pagina) {
		LOG.debug("LLego a llenarSubmenus: " + pagina.getNombre() + " ID: "
				+ pagina.getId() + " Con un padre: "
				+ pagina.getPagina().getNombre() + " ID: "
				+ pagina.getPagina().getId());
		Menu menuPadreAux = new Menu(pagina.getPagina().getNombre(), "../"
				+ pagina.getPagina().getIcon(), ("../" + pagina.getPagina()
				.getUrl()), pagina.getPagina().getOrden());

		// Comprueba si su padre esta agregado, sino lo agrega.
		if (!(menus.contains(menuPadreAux))) {

			LOG.debug("TRATO DE AGREGAR UN HIJO QUE NO TENIA PADRE!!!");
			LOG.debug("HIJA: " + pagina.getNombre() + " PADRE: "
					+ pagina.getPagina().getNombre());
			menus.add(menuPadreAux);
			// Como es padre, puede que tenga SubMenus, entonces inicializa una
			// lista.
			menus.get(menus.indexOf(menuPadreAux)).setSubMenus(
					new ArrayList<Menu>());
		}

		// Setea por defecto el orden si no se puso por alguna razón.
		// if (pagina.getOrden() == null) {
		// pagina.setOrden(0);
		// }

		LOG.info("Intento obtener al padre --> "
				+ menus.get(menus.indexOf(menuPadreAux)).getNombre()
				+ " PARA AGREGAR AL HIJO "
				+ new Menu(pagina.getNombre(), "../" + pagina.getIcon(), "../"
						+ pagina.getUrl(), pagina.getOrden()).getNombre());

		LOG.debug("Index de lista del padre: " + menuPadreAux.getNombre()
				+ " ID" + menus.indexOf(menuPadreAux));
		LOG.debug("Padres hasta el momento");
		for (Menu menuAux : menus) {
			System.out.println("---> " + menuAux.getNombre());
		}

		// menus.get(menus.indexOf(menuPadreAux))
		// .getSubMenus()
		// .add(new Menu(pagina.getNombre(), "../" + pagina.getIcon(),
		// "../" + pagina.getUrl(), pagina.getOrden()));
		menus.get(menus.indexOf(menuPadreAux))
				.getSubMenus()
				.add(new Menu(pagina.getNombre(), "../" + pagina.getIcon(),
						pagina.getUrl(), pagina.getOrden()));
		LOG.info("---> Pagina hija: " + pagina.getNombre()
				+ "  Se agrego al padre: "
				+ menus.get(menus.indexOf(menuPadreAux)).getNombre());
		LOG.info("--> Tamaño de la lista de submenus de: "
				+ pagina.getPagina().getNombre() + " ES: "
				+ menus.get(menus.indexOf(menuPadreAux)).getSubMenus().size());
		// Para organizar tambien la lista de submenus.
		Collections.sort(menus.get(menus.indexOf(menuPadreAux)).getSubMenus());
	}

	/* Getters y Setters */
	public Map<Integer, List<Menu>> getMapaRolMenu() {
		return mapaRolMenu;
	}

	public HashSet<String> getIconos() {
		return iconos;
	}

}
