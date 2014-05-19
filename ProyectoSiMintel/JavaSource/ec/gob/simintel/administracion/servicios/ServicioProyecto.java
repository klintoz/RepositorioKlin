package ec.gob.simintel.administracion.servicios;



import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.gob.simintel.entidades.Provincia;
import ec.gob.simintel.entidades.Proyecto;
import ec.gob.simintel.servicios.commons.ServicioBase;


@Stateless

public class ServicioProyecto extends ServicioBase<Proyecto>{

	@PersistenceContext
	EntityManager em;
	public ServicioProyecto() {
		super(Proyecto.class, ServicioProyecto.class);
		// TODO Auto-generated constructor stub
	}
	
	public List<Proyecto> buscarTipoIdProyecto (int proyectoId){
		Query q=em.createQuery("SELEC proy FROM Proyecto  proy  WHERE proy.proyectoid= :paramProye");
		q.setParameter("paramProye", proyectoId);
		return q.getResultList();
	}
	
	public Proyecto buscarProyectoPorNombre (String nombre){
		try {
			Query q =em.createQuery("SELECT proy FROM Proyecto proy WHERE TRIM(UPPER(proy.descripcion)) = TRIM(UPPER(:paramNombre))");
			q.setParameter("paramNombre", nombre);
			return(Proyecto)q.getSingleResult();
			
		} catch (NoResultException e) {
			return null;
			// TODO: handle exception
		}
	}
	
	public List<Proyecto> buscarProyectosPorNombres (String nombre){
		
		Query q =em.createQuery("SELECT proyes FROM Proyecto proyes WHERE UPPER(TRIM(proyes.descripcion)) LIKE UPPER(TRIM(:paramNombre))");
		q.setParameter("paramNombre", "%" + nombre+ "%");
		return q.getResultList();
	}
	
	/**
	 * Busca las programa que le pertenecen a un proyecto
	 * @param idPrograma
	 * @return
	 */
	public List<Proyecto>  buscarProyectoPorPrograma (Integer idPrograma){
		Query q = em.createQuery("SELECT proy FROM  Proyecto proy WHERE proy.programa.programaid = :paramIdprograma)");
		q.setParameter("paramIdprograma", idPrograma);
		return q.getResultList();
	}
}
