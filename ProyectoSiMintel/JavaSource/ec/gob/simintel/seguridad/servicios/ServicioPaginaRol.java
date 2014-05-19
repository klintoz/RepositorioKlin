package ec.gob.simintel.seguridad.servicios;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.gob.simintel.entidades.Pagina;
import ec.gob.simintel.entidades.PaginaRol;
import ec.gob.simintel.entidades.Perfil;
import ec.gob.simintel.entidades.Rol;
import ec.gob.simintel.servicios.commons.ServicioBase;


/**
 * Maneja la inserción, actualización y eliminación de la entidad PaginaRol
 * sobre la BDD.
 */
@Stateless
public class ServicioPaginaRol extends ServicioBase<PaginaRol> {

	/**
	 * Llama al constructor padre.
	 */
	public ServicioPaginaRol() {
		super(PaginaRol.class, ServicioPaginaRol.class);
	}
	
		@PersistenceContext
		EntityManager em;

	/**
	 * Busca las página asociadas al rol.
	 * 
	 * @param perfil
	 *            Perfil con el que se logeo el usuario.
	 * @return Lista de Páginas para poder acceder.
	 */
	public List<Pagina> buscarPagRol(Perfil perfil) {
		Query query = em
				.createQuery("SELECT pr.pagina FROM PaginaRol pr WHERE pr.rol.id = :idRol");
		query.setParameter("idRol", perfil.getRol().getId());
		return query.getResultList();
	}

	/**
	 * Obtiene las PaginaRol disponibles sin importar el rol.
	 * 
	 * @return Lista del tipo <code>PaginaRol</code>
	 */
	public List<PaginaRol> buscarPagRol() {
		Query query = em
				.createQuery("SELECT p FROM PaginaRol p ORDER BY p.rol.id");
		return (query.getResultList().size() != 0 ? query.getResultList()
				: null);
	}

	/**
	 * Obtiene las PaginaRol disponibles de acuerdo al ID del Rol.
	 * 
	 * @param idRol
	 *            Id del cual obtendremos la información.
	 * 
	 * @return Lista del tipo <code>PaginaRol</code>
	 */
	public List<PaginaRol> buscarPagRol(int idRol) {
		Query query = em
				.createQuery("SELECT p FROM PaginaRol p WHERE p.rol.id = :idRol");
		query.setParameter("idRol", idRol);
		return (query.getResultList().size() != 0 ? query.getResultList()
				: null);
	}

	/**
	 * Compara las 2 listas que recibe y las mezcla en una sola.
	 * 
	 * @param paginasRol
	 *            Lista con las páginas disponibles para un rol.
	 * @param paginas
	 *            Lista con todas las páginas existentes.
	 * @return
	 */
	public List<PaginaRol> llenarTabla(List<PaginaRol> paginasRol,
			List<Pagina> paginas) {
		List<Pagina> todasPag = new CopyOnWriteArrayList<Pagina>();
		List<PaginaRol> pagRol = new CopyOnWriteArrayList<PaginaRol>();
		PaginaRol paginaRol = new PaginaRol();

		// Insertando datos clonados de una Lista a otra
		for (Pagina auxPag : paginas) {
			todasPag.add((Pagina) auxPag.clone());
		}

		// Insertando datos clonados de una Lista a otra
		for (PaginaRol auxPagRol : paginasRol) {
			auxPagRol.setPagina((Pagina) auxPagRol.getPagina().clone());
			pagRol.add((PaginaRol) auxPagRol.clone());
		}

		// Cambiando el valor del Booleano en la nueva lista
		for (PaginaRol auxPagRol : pagRol) {
			auxPagRol.getPagina().setSeleccionadoRol(true);
		}

		try {
			// Eliminando Duplicados
			forExterno: for (PaginaRol auxPagRol : pagRol) {
				for (Pagina auxPag : todasPag) {
					if (auxPag.getNombre().compareTo(
							auxPagRol.getPagina().getNombre()) == 0) {
						todasPag.remove(auxPag);
						continue forExterno;
					}
				}
			}

			System.out.println("Tamaño Lista TodasPag: " + todasPag.size());

			// Agregando elementos restantes de pagina a las pagina Rol
			if (todasPag.size() > 0) {
				System.out
						.println("Entro a la parte de la mezcla cuando es >0");
				for (Pagina auxPag : todasPag) {
					paginaRol = new PaginaRol();
					paginaRol.setPagina(auxPag);
					pagRol.add(paginaRol);
				}
			}
		} catch (NullPointerException e) {
			LOG.debug("Lista PagRol VACIA");
			pagRol = null;
		}
		return pagRol;
	}

	/**
	 * Comprueba los cambios entre los datos existentes en la BDD con los
	 * cambiados en la lista, de acuerdo a los cambios, inserta o elimina
	 * registros.
	 * 
	 * @param paginasAux
	 *            Lista con los datos cambiados.
	 * @param paginasRol
	 *            Lista con los datos originales de la BDD.
	 * @param rol
	 *            Rol al cual se le van a realizar los cambios
	 * @param idPerfil
	 *            Id del perfil que esta modificando los datos.
	 */
	public void insertarDatos(List<PaginaRol> paginasAux,
			List<PaginaRol> paginasRol, Rol rol, int idPerfil) {

		List<PaginaRol> pagsSeleccion = new CopyOnWriteArrayList<PaginaRol>();
		List<PaginaRol> pagsRol = new CopyOnWriteArrayList<PaginaRol>();

		// Insertando datos clonados de una Lista a otra
		for (PaginaRol pagSelec : paginasAux) {
			pagsSeleccion.add((PaginaRol) pagSelec.clone());
		}

		try {

			// Insertando datos clonados de una Lista a otra
			for (PaginaRol auxPagRol : paginasRol) {
				auxPagRol.setPagina((Pagina) auxPagRol.getPagina().clone());
				pagsRol.add((PaginaRol) auxPagRol.clone());
			}

			// Cambiando el valor del Booleano en la nueva lista
			for (PaginaRol auxPagRol : pagsRol) {
				auxPagRol.getPagina().setSeleccionadoRol(true);
			}

			forExterno: for (PaginaRol pagSelecAux : pagsSeleccion) {
				for (PaginaRol pagRolAux : pagsRol) {
					if (pagRolAux.getPagina().getNombre()
							.compareTo(pagSelecAux.getPagina().getNombre()) == 0) {
						// Si tanto en la lista de la web, como en la lista de
						// la BDD no se cambiaron los booleanos, no hace nada
						if (pagRolAux.getPagina().isSeleccionadoRol() == pagSelecAux
								.getPagina().isSeleccionadoRol()) {
							LOG.debug("Encontro Coincidencia de nombre y de CHECKS "
									+ pagRolAux.getPagina().getNombre()
									+ " "
									+ pagRolAux.getPagina().isSeleccionadoRol()
									+ " Sleccion: "
									+ pagSelecAux.getPagina().getNombre()
									+ " "
									+ pagSelecAux.getPagina()
											.isSeleccionadoRol());
							continue forExterno;
							// Si antes estaba con true y ahora con false,
							// entonces se elimina el registro de BDD.
						} else if ((!pagSelecAux.getPagina()
								.isSeleccionadoRol())
								&& (pagRolAux.getPagina().isSeleccionadoRol())) {
							pagSelecAux.setRol(rol);
							LOG.debug("Intento Eliminar: "
									+ pagSelecAux.getPagina().getNombre());
							eliminar(pagSelecAux);
							continue forExterno;
						}
					}
				}

				// Inserta en la BDD debido a que es un registro nuevo.
				if (pagSelecAux.getPagina().isSeleccionadoRol()) {
					pagSelecAux.setRol(rol);
					pagSelecAux.setFechaCreacion(new Date());
					pagSelecAux.setPerfilCreacion(idPerfil);
					LOG.debug("Intento insertar: "
							+ pagSelecAux.getPagina().getNombre());
					insertar(pagSelecAux);
				}

			}
			// Significa que La lista de PaginaRol esta vacia.
		} catch (NullPointerException e) {
			for (PaginaRol pagAux : pagsSeleccion) {
				if (pagAux.getPagina().isSeleccionadoRol())
					insertar(pagAux);
			}
		}
	}
}
