package ec.gob.simintel.seguridad.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.gob.simintel.entidades.Pagina;
import ec.gob.simintel.entidades.Perfil;
import ec.gob.simintel.servicios.commons.ServicioBase;


/**
 * Maneja la inserción, actualización y eliminación de la entidad Pagina sobre
 * la BDD.
 */
@Stateless
public class ServicioPagina extends ServicioBase<Pagina> {

	/**
	 * Llama al constructor padre.
	 */
	public ServicioPagina() {
		super(Pagina.class, ServicioPagina.class);
	}
	
	//agrege yo
		@PersistenceContext
		EntityManager em;

	/**
	 * Busca todas las páginas.
	 * 
	 * @param perfil
	 *            Perfil con el que se logeo el usuario.
	 * @return Lista de Paginas a las cuales tiene permiso ese perfil.
	 */	
	public List<Pagina> buscarPaginasPorRol(Perfil perfil) {
		Query query = em
				.createQuery("SELECT pr.pagina FROM PaginaRol pr WHERE pr.rol.id = :idRol");
		query.setParameter("idRol", perfil.getRol().getId());
		return query.getResultList();
	}

	/**
	 * Permite buscar una pagina por su nombre, con una ID diferente al
	 * suministrado.
	 * 
	 * @param pagina
	 *            Objeto del tipo <code>Pagina</code> del cual se va a extraer
	 *            el nombre y el Id.
	 * @return <code>true</code> si es que encuentra alguna coincidencia, caso
	 *         contrario <code>false</code>.
	 */
	public boolean existePagNombreId(Pagina pagina) {
		Query query = em
				.createQuery("SELECT p FROM Pagina p WHERE TRIM(UPPER(p.nombre)) = TRIM(UPPER(:nombre)) AND p.id != :id");
		query.setParameter("nombre", pagina.getNombre());
		query.setParameter("id", pagina.getId());
		try {
			query.getSingleResult();
			return true;
		} catch (NoResultException e) {
			LOG.debug("Ya existe otra pagina con ese mismo nombre.");
		} catch (NonUniqueResultException e) {
			LOG.fatal("EXISTEN DOS PAGINAS CON EL MISMO NOMBRE EN BDD.");

		}
		return false;
	}

	/**
	 * Permite buscar una pagina por su URL, con una ID diferente al
	 * suministrado.
	 * 
	 * @param pagina
	 *            Objeto del tipo <code>Pagina</code> del cual se va a extraer
	 *            el URL y el Id.
	 * @return <code>true</code> si es que encuentra alguna coincidencia, caso
	 *         contrario <code>false</code>.
	 */
	public boolean existePagUrlId(Pagina pagina) {
		if (pagina.getUrl().compareTo("#") != 0) {
			Query query = em
					.createQuery("SELECT p FROM Pagina p WHERE TRIM(UPPER(p.url)) = TRIM(UPPER(:url)) AND p.id != :id");
			query.setParameter("url", pagina.getUrl());
			query.setParameter("id", pagina.getId());
			try {
				query.getSingleResult();
				return true;
			} catch (NoResultException e) {
				LOG.debug("NO EXISTEN DUPLICADOS.");
			} catch (NonUniqueResultException e) {
				LOG.fatal("EXISTEN DOS PAGINAS CON LA MISMA URL EN BDD.");
			}
		}
		return false;
	}

	/**
	 * Permite comprobar si existe una página con el <code>nombre</code>
	 * suministrado como parametro.
	 * 
	 * @param nombre
	 *            Nombre el cual va a ser objeto de comprobación.
	 * @return <code>true</code> si es que existe una página con el mismo
	 *         nombre, caso contrario <code>false</code>.
	 */
	public boolean existePagNombre(String nombre) {
		Query query = em
				.createQuery("SELECT p.nombre FROM Pagina p WHERE TRIM(UPPER(p.nombre)) = TRIM(UPPER(:nombre))");
		query.setParameter("nombre", nombre);
		try {
			query.getSingleResult();
			return true;
		} catch (NoResultException e) {
			return false;
		}
	}

	/**
	 * Permite comprobar si existe una página con la <code>URL</code>
	 * suministrada como parametro.
	 * 
	 * @param url
	 *            URL que va a ser objeto de comprobación.
	 * @return <code>true</code> si es que existe una página con el mismo URL,
	 *         caso contrario <code>false</code>.
	 */
	public boolean existePagUrl(String url) {
		if (url.compareTo("#") != 0) {

			Query query = em
					.createQuery("SELECT p.url FROM Pagina p WHERE TRIM(UPPER(p.url)) = TRIM(UPPER(:url))");
			query.setParameter("url", url);
			try {
				query.getSingleResult();
				return true;
			} catch (NoResultException e) {
				return false;
			}
		}
		return false;
	}

	/**
	 * Busca las paginas que son PADRES y a la vez son MENU.
	 * 
	 * @return Lista de paginas con los resultados.
	 */
	public List<Pagina> buscarPaginasPadreMenu() {
		Query query = em
				.createQuery("SELECT p FROM Pagina p WHERE p.pagina IS NULL AND p.esMenu IS TRUE ORDER BY p.orden");
		return query.getResultList();
	}

	/**
	 * Busca las paginas que solo son PADRES.
	 * 
	 * @return Lista de paginas con los resultados.
	 */
	public List<Pagina> buscarPaginasPadre() {
		Query query = em
				.createQuery("SELECT p FROM Pagina p WHERE p.pagina IS NULL ORDER BY p.orden");
		return query.getResultList();
	}

	/**
	 * Busca las paginas que solo son MENU.
	 * 
	 * @return Lista de paginas con los resultados.
	 */
	public List<Pagina> buscarPaginaMenu() {
		Query query = em
				.createQuery("SELECT p FROM Pagina p WHERE p.esMenu IS TRUE");
		return query.getResultList();
	}
}
