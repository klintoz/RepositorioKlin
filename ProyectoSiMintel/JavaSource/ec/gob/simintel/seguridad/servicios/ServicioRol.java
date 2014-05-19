package ec.gob.simintel.seguridad.servicios;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.gob.simintel.entidades.Perfil;
import ec.gob.simintel.entidades.Rol;
import ec.gob.simintel.entidades.Usuario;
import ec.gob.simintel.servicios.commons.ServicioBase;


/**
 * Maneja la inserción, actualización y eliminación de la entidad Rol sobre la
 * BDD.
 */
@Stateless
public class ServicioRol extends ServicioBase<Rol> {

	@EJB
	private ServicioPerfil servicioPerfil;

	/**
	 * Llama al constructor padre.
	 */
	public ServicioRol() {
		super(Rol.class, ServicioRol.class);
	}
	
	//agrege yo
	@PersistenceContext
	EntityManager em;

	/**
	 * Comprueba la existencia de un Rol por un nombre.
	 * 
	 * @param nombreRol
	 *            Nombre del rol del cual se desea comprobar la existencia.
	 * @return<code>true</code> en caso de que exista un rol con el mismo nombre
	 *                          pero diferente ID, caso contrario
	 *                          <code>false</code>.
	 */
	public boolean existeRolNombre(String nombreRol) {
		Query query = em
				.createQuery("SELECT r FROM Rol r WHERE UPPER(TRIM(r.nombre)) = UPPER(TRIM(:nombreRol))");
		query.setParameter("nombreRol", nombreRol);
		try {
			query.getSingleResult();
			return true;
		} catch (NoResultException ex) {
			LOG.debug("Ya existe un rol con ese nombre.");
		} catch (NonUniqueResultException ex) {
			LOG.error("Existe mas de un rol con ese nombre.");
		}
		return false;
	}

	/**
	 * Comprueba la existencia de un Rol por un nombre igual pero diferente ID.
	 * 
	 * @param rol
	 *            Rol del cual se quiere comprobar algún duplicado.
	 * @return <code>true</code> en caso de que exista un rol con el mismo
	 *         nombre pero diferente ID, caso contrario <code>false</code>.
	 */
	public boolean existeRolNombre(Rol rol) {
		Query query = em
				.createQuery("SELECT r FROM Rol r WHERE r.id != :idRol AND UPPER(TRIM(r.nombre)) = UPPER(TRIM(:nombreRol))");
		query.setParameter("idRol", rol.getId());
		query.setParameter("nombreRol", rol.getNombre());
		try {
			query.getSingleResult();
			return true;
		} catch (NoResultException ex) {
			LOG.debug("Ya existe un rol con ese nombre.");
		} catch (NonUniqueResultException ex) {
			LOG.error("Existe más de un rol con ese nombre.");
		}
		return false;
	}

	public List<Rol> buscarRolUsuario(Usuario usuario) {
		List<Perfil> perfilesUsuario = servicioPerfil
				.buscarPerfPorUsuario(usuario);
		List<Rol> roles = buscarTodos();

		for (Perfil p : perfilesUsuario) {
			for (Rol r : roles) {
				if (p.getRol().getId() == r.getId()) {
					r.setRolSeleccionado(true);
				}
			}
		}
		return roles;
	}
}
