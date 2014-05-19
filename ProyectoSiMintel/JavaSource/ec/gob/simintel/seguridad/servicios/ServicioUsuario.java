package ec.gob.simintel.seguridad.servicios;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.gob.simintel.entidades.Persona;
import ec.gob.simintel.entidades.Usuario;
import ec.gob.simintel.servicios.commons.ServicioBase;

/**
  * Maneja la inserción, actualización y eliminación de la entidad Usuario sobre
 * la BDD. 
 * @author Gaby
 *
 */
@Stateless
public class ServicioUsuario extends ServicioBase<Usuario> {

	/**
	 * Llama al constructor padre.
	 */
	public ServicioUsuario() {
		super(Usuario.class, ServicioUsuario.class);
	}
	
		@PersistenceContext
		EntityManager em;

	/**
	 * Busca un usuario por <code>username</code> y <code>password</code>.
	 * 
	 * @param usuario
	 * @return <code>usuario</code> completo.
	 */
	public Usuario buscarUsuarioUserPass(Usuario usuario) {
		Query query = em
				.createQuery("SELECT u FROM Usuario u WHERE u.usuario = :usrUsername AND u.contrasenia = :usrClave");
		query.setParameter("usrUsername", usuario.getUsuario());
		query.setParameter("usrClave", usuario.getContrasenia());
		try {
			return (Usuario) query.getSingleResult();
		} catch (NonUniqueResultException e) {
			LOG.error("USUARIOS CON CREDENCIALES IGUALES");
		} catch (NoResultException e) {
			LOG.error("NO EXISTE USUARIO");
		}
		return null;
	}

	/**
	 * Genera un <code>Usuario</code> (username y password) unico por
	 * <code>persona</code>, en caso de repeticion, generara otro, usando "_
	 * <code>numero</code>", el <code>numero</code> sera una variable de
	 * autoincremento, ignora las tildes y las ñ.
	 * 
	 * @param persona
	 *            Objeto con los datos personales.
	 * @return Usuario <code>Usuario</code> con un username y password
	 *         generados.
	 */
	public Usuario generarUsuario(Persona persona) {
		String apellidoAux = persona.getApellido().toLowerCase().trim();
		if (apellidoAux.contains(" ")) {
			// Coge solo el primer apellido en caso de que hayan varios.
			apellidoAux = apellidoAux.substring(0, apellidoAux.indexOf(" "));
		}
		String username = persona.getNombre().substring(0, 1).toLowerCase()
				.concat(apellidoAux);

		Usuario usuario = new Usuario();

		char[] caracteres = username.toCharArray();
		for (int i = 0; i < caracteres.length; i++) {
			// System.out.println("caracter: "+caracteres[i]+" ascii:"+(int)caracteres[i]);
			if (caracteres[i] == 225) {// a con tilde al recuperar coge otro
										// valor y no el original que es 160
				caracteres[i] = 97;
			} else if (caracteres[i] == 233) {// e con tilde 130
				caracteres[i] = 101;
			} else if (caracteres[i] == 237) {// i con tilde 161
				caracteres[i] = 105;
			} else if (caracteres[i] == 243) {// o con tilde 162
				caracteres[i] = 111;
			} else if (caracteres[i] == 250) {// u con tilde 163
				caracteres[i] = 117;
			} else if (caracteres[i] == 241) {// ñ = 241, n = 110
				caracteres[i] = 110;
			}
		}
		username = String.valueOf(caracteres);
		System.out.println("USERNAME CAMBIADO " + username);

		int numeroLogin = 0;
		while (true) {
			Query q = em
					.createQuery("SELECT u FROM Usuario u WHERE u.usuario = :paramUsername");
			q.setParameter("paramUsername", username);
			try {
				q.getSingleResult();
				numeroLogin++;
				if (numeroLogin > 0) {
					String[] auxLogin = username.split("_");
					usuario.setUsuario(auxLogin[0].concat("_") + numeroLogin);
					usuario.setContrasenia(persona.getCedula());
					username = auxLogin[0].concat("_") + numeroLogin;
				}
			} catch (NoResultException e) {
				usuario.setUsuario(username);
				usuario.setContrasenia(persona.getCedula());
				break;
			}
		}
		return usuario;
	}
	
	public Usuario buscarUsuario(String username) throws Exception {
		Query query = em
				.createQuery("SELECT u FROM Usuario u WHERE u.usuario= :paramUsuario");
		query.setParameter("paramUsuario", username);
		try {
			return (Usuario) query.getSingleResult();
		} catch (NoResultException nr) {
			throw new Exception("No existe el usuario");
		} catch (NonUniqueResultException nure) {
			throw new Exception("???");
		}
	}
		
	public boolean existeUsuarioNombre(String username) {
		Query query = em.createQuery("SELECT u FROM Usuario u WHERE UPPER(TRIM(u.usuario)) = UPPER(TRIM(:param1))");
		query.setParameter("param1", username);
		try {
			query.getSingleResult();
			return true;
		} catch (NoResultException ex) {
			LOG.debug("Ya existe un usuario con ese nombre.");
		} catch (NonUniqueResultException ex) {
			LOG.error("Existe mas de un usuario con ese nombre.");
		}
		return false;
	}
	
	public boolean existeUsuarioNombre(Usuario usuario) {
		Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.id != :param1 AND UPPER(TRIM(u.usuario)) = UPPER(TRIM(:param2))");
		query.setParameter("param1", usuario.getId());
		query.setParameter("param2", usuario.getUsuario());
		try {
			query.getSingleResult();
			return true;
		} catch (NoResultException ex) {
			LOG.debug("Ya existe un usuario con ese nombre.");
		} catch (NonUniqueResultException ex) {
			LOG.error("Existe más de un usuario con ese nombre.");
		}
		return false;
	}	

}
