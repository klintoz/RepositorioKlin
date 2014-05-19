package ec.gob.simintel.seguridad.controladores;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.DefaultRequestContext;
import org.primefaces.event.SelectEvent;

import ec.gob.simintel.commons.datamanager.MenuDataManager;
import ec.gob.simintel.commons.datamanager.PaginasDataManager;
import ec.gob.simintel.commons.datamanager.UsuarioDataManager;
import ec.gob.simintel.controladores.commons.Constantes;
import ec.gob.simintel.controladores.commons.MensajesError;
import ec.gob.simintel.controladores.commons.MensajesInformacion;
import ec.gob.simintel.controladores.commons.GeneradorMensajes;
import ec.gob.simintel.entidades.Pagina;
import ec.gob.simintel.seguridad.servicios.ServicioMenu;
import ec.gob.simintel.seguridad.servicios.ServicioPagina;
import ec.gob.simintel.validadores.UtilRedireccion;

/**
 * Controlador para la gestión de páginas. 
 */
@ManagedBean
@ViewScoped
public class ControladorPagina {

	private List<Pagina> paginas;
	private Pagina paginaSeleccionada;
	private Pagina auxPagina;
	private String quitarPadre;
	private List<Pagina> paginasPadres;
	private List<Pagina> iconos;
	private List<Pagina> paginasMenu;
	@EJB
	private ServicioPagina srvPagina;
	@EJB
	private ServicioMenu srvMenu;
	@ManagedProperty("#{menuDataManager}")
	private MenuDataManager menuDataManager;
	@ManagedProperty("#{usuarioDataManager}")
	private UsuarioDataManager usuarioDataManager;
	@ManagedProperty("#{controladorMenu}")
	private ControladorMenu controladorMenu;
	@ManagedProperty("#{paginasDataManager}")
	private PaginasDataManager paginasDataManager;

	/**
	 * Constructor. Inicializa atributos.
	 */
	public ControladorPagina() {
		this.paginas = new ArrayList<Pagina>();
		this.paginasPadres = new ArrayList<Pagina>();
		this.paginasMenu = new ArrayList<Pagina>();
		this.paginaSeleccionada = new Pagina();
		this.iconos = new ArrayList<Pagina>();
	}

	/**
	 * Llena listas con datos del servicio, ademas itera la lista de iconos para
	 * evitar duplicados.
	 */
	@PostConstruct
	private void init() {
		try {
			this.paginas = srvPagina.buscarTodos();
			this.paginasMenu = srvPagina.buscarPaginaMenu();
			this.paginasPadres = paginasDataManager.getPaginasPadres();
			int i = 0;
			HashSet<String> auxIcon = srvMenu.getIconos();
			Iterator<String> it = auxIcon.iterator();
			while (it.hasNext()) {
				Pagina aux = new Pagina();
				aux.setId(i);
				aux.setIcon(it.next().toString());
				iconos.add(aux);
				++i;
			}
			auxIcon = null;
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	/**
	 * Permite insertar una nueva pagina en la BDD, primero comprobando la
	 * existencia de duplicados, además actualiza el mapa de menu/rol.
	 */
	public void agregar() {
		if (srvPagina.existePagNombre(paginaSeleccionada.getNombre())) {
			GeneradorMensajes
					.mostrarMensajeError(MensajesError.ERROR_PAGINA_NOMBRE_REPETIDO);
			return;
		} else if (srvPagina.existePagUrl(paginaSeleccionada.getUrl())) {
			GeneradorMensajes
					.mostrarMensajeError(MensajesError.ERROR_PAGINA_URL_REPETIDA);
			return;
		}
		try {
			paginaSeleccionada.setFechaCreacion(new Date());
			paginaSeleccionada.setPerfilCreacion(usuarioDataManager.getPerfil()
					.getId());
			srvPagina.insertar(paginaSeleccionada);
			paginas.add(paginaSeleccionada);
			actualizarMenusRoles();

			GeneradorMensajes
					.mostrarMensajeInformacion(MensajesInformacion.PAGINA_CREADA);

		} catch (Exception exc) {
			GeneradorMensajes
					.mostrarMensajeError(MensajesError.ERROR_PAGINA_CREADA);
			exc.printStackTrace();
		} finally {
			this.paginaSeleccionada = new Pagina();
			this.paginasMenu = srvPagina.buscarPaginaMenu();
		}

	}

	/**
	 * Permite editar una pagina e insertarla en la BDD, primero comprobando la
	 * existencia de duplicados, además actualiza el mapa de menu/rol.
	 */
	public void editar() {
		if (srvPagina.existePagNombreId(paginaSeleccionada) == true) {
			GeneradorMensajes.mostrarMensajeError(MensajesError.ERROR_PAGINA_NOMBRE_REPETIDO);
			// Para evitar que se actualice la tabla
			cancelarEditar();
			return;
		} else if ((srvPagina.existePagUrlId(paginaSeleccionada) == true)) {
			GeneradorMensajes.mostrarMensajeError(MensajesError.ERROR_PAGINA_URL_REPETIDA);
			// Para evitar que se actualice la tabla
			cancelarEditar();
			return;
		}
		// En caso de algun error desconocido.
		try {
			System.out.println("*** Entro al try");
			if ((paginaSeleccionada.getPagina() == null)
					|| (paginaSeleccionada.getPagina().getNombre()
							.compareTo("") == 0)) {
				paginaSeleccionada.setPagina(null);
				System.out.println("*** Entro al if");
			}
			paginaSeleccionada.setFechaModificacion(new Date());
			paginaSeleccionada.setPerfilModificacion(usuarioDataManager.getPerfil().getId());
			srvPagina.actualizar(paginaSeleccionada);
			actualizarMenusRoles();
			//Para que se pinte bien la pagina update no vale ?
			UtilRedireccion.redireccionar(Constantes.URL_PAGINA);
			GeneradorMensajes.mostrarMensajeInformacion(MensajesInformacion.PAGINA_ACTUALIZADA);
		} catch (Exception exc) {
			GeneradorMensajes.mostrarMensajeError(MensajesError.ERROR_PAGINA_EDITADA);
			exc.printStackTrace();
		} finally {
			this.paginaSeleccionada = new Pagina();
		}
	}

	/**
	 * Abre el dialogo de editar y setea los objetos adquiridos de la pagina a
	 * un auxiliar de la clase.
	 */
	public void abrirEditar() {
		this.auxPagina = new Pagina(paginaSeleccionada.getId(),
				paginaSeleccionada.getEsMenu(), paginaSeleccionada.getIcon(),
				paginaSeleccionada.getNombre(), paginaSeleccionada.getOrden(),
				paginaSeleccionada.getUrl(), paginaSeleccionada.getPagina());
		DefaultRequestContext.getCurrentInstance().execute("dlgEditPag.show()");
	}

	/**
	 * Abre el dialogo para agregar un nuevo Rol.
	 */
	public void abrirAgregar() {
		paginaSeleccionada = new Pagina();
		DefaultRequestContext.getCurrentInstance().execute("dlgAgrePag.show()");
	}

	/**
	 * Cierra el dialogo de editar y setea los objetos adquiridos del auxiliar a
	 * la clase.
	 */
	public void cancelarEditar() {
		this.paginaSeleccionada.setEsMenu(this.auxPagina.getEsMenu());
		this.paginaSeleccionada.setIcon(this.auxPagina.getIcon());
		this.paginaSeleccionada.setNombre(this.auxPagina.getNombre());
		this.paginaSeleccionada.setOrden(this.auxPagina.getOrden());
		this.paginaSeleccionada.setUrl(this.auxPagina.getUrl());
		this.paginaSeleccionada.setPagina(this.auxPagina.getPagina());
		DefaultRequestContext.getCurrentInstance().execute("dlgEditPag.hide()");
	}

	/**
	 * Permite cerrar el dialogo de eliminación e instanciar un nuevo objeto
	 * <code>Pagina</code>.
	 */
	public void cancelarEliminar() {
		this.paginaSeleccionada = new Pagina();
		DefaultRequestContext.getCurrentInstance().execute("dlgElimPag.hide()");
	}

	/**
	 * Permite cerrar el dialogo de creación e instanciar un nuevo objeto
	 * <code>Pagina</code>.
	 */
	public void cancelarAgregar() {
		this.paginaSeleccionada = new Pagina();
		DefaultRequestContext.getCurrentInstance().execute("dlgAgrePag.hide()");
	}

	public void asignarPermisos() {
		UtilRedireccion.redireccionar(Constantes.URL_PAGINA_ROL);
	}

	/**
	 * Permite eliminar una pagina de la BDD, además actualiza el mapa de
	 * menu/rol.
	 */
	public void eliminar() {
		try {
			srvPagina.eliminar(paginaSeleccionada);
			paginas.remove(paginaSeleccionada);
			actualizarMenusRoles();
			GeneradorMensajes
					.mostrarMensajeInformacion(MensajesInformacion.PAGINA_ELIMINADA);

		} catch (Exception e) {
			GeneradorMensajes
					.mostrarMensajeError(MensajesError.ERROR_PAGINA_ELIMINADA);
			System.out.println(e.toString());
		} finally {
			this.paginaSeleccionada = new Pagina();
		}
	}

	/**
	 * Permite actualizar los Menus de todos los roles con los nuevos datos.
	 */
	private void actualizarMenusRoles() {
		// Llena el mapa cargando los datos desde la BDD.
		srvMenu.llenarMapa();
		// Obtiene el mapa del Servicio.
		menuDataManager.obtenerMapa();
		// Llena de nuevo los menus.
		controladorMenu.llenar();
		// Obtiene nuevamente los menus de todos los roles
		this.paginasMenu = srvPagina.buscarPaginaMenu();
		// Obtiene solo los menus padres y los guarda en el DataManager.
		this.paginasPadres = srvPagina.buscarPaginasPadre();
		paginasDataManager.setPaginasPadres(this.paginasPadres);
		// Cambia el estado rendered del componente
//		UIComponent.getCurrentComponent(FacesContext.getCurrentInstance())
//				.findComponent(" :stkBot").setRendered(false);
		// Actualiza todo lo que se encuentra dentro del form "frmStk", es
		// decir el submenu.
		DefaultRequestContext.getCurrentInstance().update("frmStk");

	}

	/**
	 * Permite usar una dataTable con al posibilidad de seleccionar mediante
	 * click.
	 * 
	 * @param event
	 *            Evento que usa el componente, y el Ajax.
	 */
	public void filaPadre(SelectEvent event) {
		paginaSeleccionada.setPagina((Pagina) ((Pagina) event.getObject())
				.clone());
	}

	/**
	 * Permite usar una dataTable con al posibilidad de seleccionar mediante
	 * click.
	 * 
	 * @param event
	 *            Evento que usa el componente, y el Ajax.
	 */
	public void filaIcon(SelectEvent event) {
		paginaSeleccionada.setIcon(((Pagina) event.getObject()).getIcon());
	}

	/**
	 * Permite cambiar el orden de los elementos del menú usando Drag and Drop.
	 */
	public void ordenarMenu() {

		System.out.println(" Controlador Tam pagPadre:" + paginasPadres.size()
				+ " SUPUESTAMENTE despues de ordenar");

		for (int i = 0; i < paginasPadres.size(); i++) {
			System.out.println("NOMBRE -> " + paginasPadres.get(i).getNombre()
					+ " ORDEN -> " + paginasPadres.get(i).getOrden());
			paginasPadres.get(i).setOrden(i);
			srvPagina.actualizar(paginasPadres.get(i));
		}

		actualizarMenusRoles();

		GeneradorMensajes
				.mostrarMensajeInformacion(MensajesInformacion.MENU_ORDEN);
	}

	/* Getters y Setters */

	public List<Pagina> getPaginas() {
		return paginas;
	}

	public Pagina getPaginaSeleccionada() {
		return paginaSeleccionada;
	}

	public void setPaginaSeleccionada(Pagina paginaSeleccionada) {
		this.paginaSeleccionada = paginaSeleccionada;
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

	public ControladorMenu getControladorMenu() {
		return controladorMenu;
	}

	public void setControladorMenu(ControladorMenu controladorMenu) {
		this.controladorMenu = controladorMenu;
	}

	public List<Pagina> getIconos() {
		return iconos;
	}

	public List<Pagina> getPaginasPadres() {
		return paginasPadres;
	}

	public void setPaginasPadres(List<Pagina> paginasPadres) {
		this.paginasPadres = paginasPadres;
	}

	public List<Pagina> getPaginasMenu() {
		return paginasMenu;
	}

	public void setPaginasMenu(List<Pagina> paginasMenu) {
		this.paginasMenu = paginasMenu;
	}

	public PaginasDataManager getPaginasDataManager() {
		return paginasDataManager;
	}

	public void setPaginasDataManager(PaginasDataManager paginasDataManager) {
		this.paginasDataManager = paginasDataManager;
	}

	public String getQuitarPadre() {
		return quitarPadre;
	}

	public void setQuitarPadre(String quitarPadre) {
		this.quitarPadre = quitarPadre;
	}

}
