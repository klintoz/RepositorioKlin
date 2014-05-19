package ec.gob.simintel.seguridad.servicios;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.gob.simintel.entidades.Perfil;
import ec.gob.simintel.entidades.Rol;
import ec.gob.simintel.entidades.Usuario;
import ec.gob.simintel.servicios.commons.ServicioBase;

/**
 * Maneja la inserción, actualización y eliminación de la entidad Perfil sobre
 * la BDD.
 */
@Stateless
public class ServicioPerfil extends ServicioBase<Perfil> {

	/**
	 * Llama al constructor padre.
	 */
	public ServicioPerfil() {
		super(Perfil.class, ServicioPerfil.class);
	}
	
	//agrege yo
	@PersistenceContext
	EntityManager em;

	/**
	 * Busca los perfiles del usuario.
	 * 
	 * @param usuario
	 *            Usuario del cual se va a buscar los perfiles.
	 * @return Lista del tipo <code>Perfil</code> con todos los perfiles que un
	 *         usuario tiene.
	 */
	public List<Perfil> buscarPerfPorUsuario(Usuario usuario) {
		Query query = em.createQuery("SELECT p FROM Perfil p WHERE p.usuario.id = :idUsuario");
		query.setParameter("idUsuario", usuario.getId());
		return query.getResultList();
	}

	/**
	 * Busca los perfiles por el usuario y el rol.
	 * 
	 * @param usuario
	 *            Usuario del cual se va a buscar el perfil.
	 * @param rol
	 *            Rol del cual se va a buscar el perfil
	 * @return Lista del tipo <code>Perfil</code> con todos los perfiles que
	 *         tiene un usario y un rol determinado.
	 */
	public Perfil buscarPerfPorUsuarioRol(Usuario usuario, Rol rol) {
		Query query = em
				.createQuery("SELECT p FROM Perfil p WHERE p.usuario.id = :idUsuario AND p.rol.id= :paramIdRol");
		query.setParameter("idUsuario", usuario.getId());
		query.setParameter("paramIdRol", rol.getId());
		return (Perfil) query.getSingleResult();
	}

	public void modificarPerfil(List<Rol> rolNuevo, List<Rol> rolAntiguo,
			int idPerfil, Usuario usuario) throws Exception {
		if (!rolNuevo.isEmpty() || !rolAntiguo.isEmpty()) {
			for (Rol rAntiguo : rolAntiguo) {
				for (Rol rNuevo : rolNuevo) {
					if (rAntiguo.getId() == rNuevo.getId()) {
						System.out.println("ENTRO AL PRIMER IF.......");
						if (rAntiguo.isRolSeleccionado() != rNuevo
								.isRolSeleccionado()) {
							System.out.println("ENTRO AL SEGUNDO IF.......");
							if (rAntiguo.isRolSeleccionado()) {
								em.remove(buscarPerfPorUsuarioRol(usuario,
										rAntiguo));
								System.out
										.println("ENTRO AL  IF DE REMOVER.......");
							}
							if (!rAntiguo.isRolSeleccionado()) {
								System.out
										.println("ENTRO AL  IF DE INSERTAR.......");
								Perfil perfil = new Perfil();
								perfil.setPerfilCreacion(idPerfil);
								perfil.setRol(rAntiguo);
								perfil.setUsuario(usuario);
								perfil.setFechaCreacion(new Date());
								em.persist(perfil);
							}
						}
					}
				}
			}
		} else {
			throw new Exception("No ha seleccionado un usuario");
		}

	}
	
	public List<Perfil> buscarPerfilesPorRol(Integer idRol){
		Query query = em.createQuery("SELECT p FROM Perfil p WHERE p.rol.id = :param");
		query.setParameter("param", idRol);
		return query.getResultList();
	}
}
