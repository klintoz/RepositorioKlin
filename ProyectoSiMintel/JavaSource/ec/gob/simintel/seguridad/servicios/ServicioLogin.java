package ec.gob.simintel.seguridad.servicios;

import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.gob.simintel.entidades.Usuario;
import ec.gob.simintel.servicios.commons.ServicioBase;


/**
 * Maneja la autentificación de los Usuarios.
 */
@Stateless
public class ServicioLogin extends ServicioBase<Usuario> {

	/**
	 * Llama al constructor del padre.
	 */
	public ServicioLogin() {
		super(Usuario.class, ServicioLogin.class);
	}
	
	//agrege yo
		@PersistenceContext
		EntityManager em;

	/**
	 * Realiza las consultas a la base para comprobar si existe el usuario con
	 * esa clave y si esta dentro del rango de fechas.
	 * 
	 * @param usuario
	 * 
	 * @return <code>true</code> Si se encontro al usuario, caso contrario
	 *         <code>false</code>.
	 */	
	public boolean autenticar(Usuario usuario) {
		Query query = em
				.createQuery("SELECT u FROM Usuario u WHERE u.usuario = :usrUsername AND u.contrasenia = :usrClave AND (u.fechaCaducidadContrasenia >= :usrFecha OR u.fechaCaducidadContrasenia IS NULL)");
		query.setParameter("usrUsername", usuario.getUsuario());
		query.setParameter("usrClave", usuario.getContrasenia());
		query.setParameter("usrFecha", new Date());
		try {
			query.getSingleResult();
			LOG.debug("Usuario autentificado sin problemas.");
		} catch (NoResultException e) {
			LOG.debug(e.getMessage());
			return false;
		} catch (NonUniqueResultException e) {
			LOG.fatal("Existen 2 usuarios con las mismas credenciales. Username: "
					+ usuario.getUsuario());
			LOG.debug(e.getMessage());
			return false;
		}
		return true;
	}
}
