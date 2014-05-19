package ec.gob.simintel.commons.datamanager;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ec.gob.simintel.entidades.Pagina;
import ec.gob.simintel.seguridad.servicios.ServicioPagina;


/**
 * Guarda en sesión los datos del Usuario que se logeo.
 * 
 * @author ClearMinds-it
 */
@ManagedBean
@SessionScoped
public class PaginasDataManager {

	// Lista de paginas padres de todos los roles
	private List<Pagina> paginasPadres;
	private List<Pagina> paginasVisibles;
	@EJB
	private ServicioPagina srvPagina;

	/**
	 * Constructor. Inicializa atributos.
	 */
	public PaginasDataManager() {
		paginasVisibles = new ArrayList<Pagina>();
		paginasPadres = new ArrayList<Pagina>();
	}

	/**
	 * Recupera las paginasPadres.
	 */
	@PostConstruct
	private void init() {
		paginasPadres = srvPagina.buscarPaginasPadre();
	}

	public List<Pagina> getPaginasVisibles() {
		return paginasVisibles;
	}

	public void setPaginasVisibles(List<Pagina> paginasVisibles) {
		this.paginasVisibles = paginasVisibles;
	}

	public List<Pagina> getPaginasPadres() {
		return paginasPadres;
	}

	public void setPaginasPadres(List<Pagina> paginasPadres) {
		this.paginasPadres = paginasPadres;
	}
}
