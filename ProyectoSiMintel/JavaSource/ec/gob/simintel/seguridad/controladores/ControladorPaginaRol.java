package ec.gob.simintel.seguridad.controladores;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.DefaultRequestContext;

import ec.gob.simintel.commons.datamanager.MenuDataManager;
import ec.gob.simintel.commons.datamanager.UsuarioDataManager;
import ec.gob.simintel.controladores.commons.Constantes;
import ec.gob.simintel.controladores.commons.MensajesInformacion;
import ec.gob.simintel.controladores.commons.GeneradorMensajes;
import ec.gob.simintel.entidades.Pagina;
import ec.gob.simintel.entidades.PaginaRol;
import ec.gob.simintel.entidades.Rol;
import ec.gob.simintel.seguridad.servicios.ServicioMenu;
import ec.gob.simintel.seguridad.servicios.ServicioPagina;
import ec.gob.simintel.seguridad.servicios.ServicioPaginaRol;
import ec.gob.simintel.seguridad.servicios.ServicioRol;
import ec.gob.simintel.validadores.UtilRedireccion;




/**
 * Controlador para la PaginaRol. 
 */
@ManagedBean
@ViewScoped
public class ControladorPaginaRol {

	private PaginaRol paginaRol;
	private List<PaginaRol> paginasRol;
	private List<Pagina> paginas;
	private List<PaginaRol> paginasAux;
	private List<Rol> roles;
	private Rol rol;
	private List<Pagina> paginasSeleccionadas;
	@EJB
	private ServicioRol srvRol;
	@EJB
	private ServicioPaginaRol srvPagRol;
	@EJB
	private ServicioPagina srvPagina;
	@ManagedProperty("#{usuarioDataManager}")
	private UsuarioDataManager usuarioDataManager;
	@EJB
	private ServicioMenu srvMenu;
	@ManagedProperty("#{menuDataManager}")
	private MenuDataManager menuDataManager;
	@ManagedProperty("#{controladorMenu}")
	private ControladorMenu controladorMenu;

	/**
	 * Inicializa los atributos.
	 */
	public ControladorPaginaRol() {
		paginaRol = new PaginaRol();
		paginasRol = new ArrayList<PaginaRol>();
		paginasAux = new ArrayList<PaginaRol>();
		paginasSeleccionadas = new ArrayList<Pagina>();
		rol = new Rol();
	}

	/**
	 * Llena la lista de roles y de páginas.
	 */
	@PostConstruct
	private void init() {
		roles = srvRol.buscarTodos();
		paginas = srvPagina.buscarTodos();
	}

	/**
	 * Llena la tabla que va a ser iterada en la web, usando los servicios
	 * correspondientes.
	 * 
	 * @param evt
	 *            Evento Ajax.
	 */
	public void llenarTabla(AjaxBehaviorEvent evt) {
		if ((rol.getId() != -1)) {
			// Llena todos lo datos completos del rol seleccionado
			rol = srvRol.buscarPorId(rol.getId());
			// Llena las paginas a las cuales el rol seleccionado tiene permiso
			paginasRol = srvPagRol.buscarPagRol(rol.getId());
			if (paginasRol != null) {
				System.out.println("-----> ---> Tamanio PagRol:+ "
						+ paginasRol.size());
				paginasAux = srvPagRol.llenarTabla(paginasRol, paginas);
			} else {
				System.out.println("-----> ---> Tamanio PagRol SALIO NULL");
				paginasAux = new ArrayList<PaginaRol>();
				Date date = new Date();

				for (Pagina auxPag : paginas) {
					paginaRol.setFechaCreacion(date);
					paginaRol.setRol(rol);
					paginaRol.setPerfilCreacion(usuarioDataManager.getPerfil()
							.getId());
					paginaRol.setPagina(auxPag);
					System.out
							.println("Deberia ingresar a la lista para pintar: "
									+ paginaRol.getPagina().getNombre());
					paginasAux.add(paginaRol);
					paginaRol = new PaginaRol();
				}
			}
		} else {
			paginasAux = new ArrayList<PaginaRol>();
		}
		DefaultRequestContext.getCurrentInstance().update(
				"frmPagRol:dttPaginasRol");
	}

	/**
	 * Permite realizar la redirección a la página solicitada.
	 */
	public void gestionPaginas() {
		UtilRedireccion.redireccionar(Constantes.URL_PAGINA);
	}

	public void gestionRol() {
		UtilRedireccion.redireccionar(Constantes.URL_ROL);
	}

	/**
	 * Inserta o elimina datos usando los servicios correspondientes.
	 */
	public void aceptarCambios() {
		for (PaginaRol pag : paginasAux) {
			System.out.println(" Nombre Pag ---> : "
					+ pag.getPagina().getNombre() + " BOOLEAN : "
					+ pag.getPagina().isSeleccionadoRol());
		}
		srvPagRol.insertarDatos(paginasAux, paginasRol, rol, usuarioDataManager.getPerfil().getId());
		// Vuelve a obtener datos actualizados para llenar las Listas para
		// evitar duplicados
		llenarTabla(null);
		// Actualiza los menus.
		actualizarMenusRoles();
		UtilRedireccion.redireccionar(Constantes.URL_PAGINA_ROL);
		GeneradorMensajes.mostrarMensajeInformacion(MensajesInformacion.ACEPTAR_CAMBIOS);
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
		// Cambia el estado rendered del componente
//		UIComponent.getCurrentComponent(FacesContext.getCurrentInstance())
//				.findComponent(" :stkBot").setRendered(false);
		// Actualiza todo lo que se encuentra dentro del form "frmStk", es
		// decir el submenu.
		DefaultRequestContext.getCurrentInstance().update("frmStk");
		// Actualiza el Menu.
		DefaultRequestContext.getCurrentInstance().update("frmDock");

	}

	/* Getter y Setters */
	public PaginaRol getPaginaRol() {
		return paginaRol;
	}

	public void setPaginaRol(PaginaRol paginaRol) {
		this.paginaRol = paginaRol;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

	public List<Pagina> getPaginasSeleccionadas() {
		return paginasSeleccionadas;
	}

	public void setPaginasSeleccionadas(List<Pagina> paginasSeleccionadas) {
		this.paginasSeleccionadas = paginasSeleccionadas;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public UsuarioDataManager getUsuarioDataManager() {
		return usuarioDataManager;
	}

	public void setUsuarioDataManager(UsuarioDataManager usuarioDataManager) {
		this.usuarioDataManager = usuarioDataManager;
	}

	public List<PaginaRol> getPaginasAux() {
		return paginasAux;
	}

	public void setPaginasAux(List<PaginaRol> paginasAux) {
		this.paginasAux = paginasAux;
	}

	public MenuDataManager getMenuDataManager() {
		return menuDataManager;
	}

	public void setMenuDataManager(MenuDataManager menuDataManager) {
		this.menuDataManager = menuDataManager;
	}

	public ControladorMenu getControladorMenu() {
		return controladorMenu;
	}

	public void setControladorMenu(ControladorMenu controladorMenu) {
		this.controladorMenu = controladorMenu;
	}

}
