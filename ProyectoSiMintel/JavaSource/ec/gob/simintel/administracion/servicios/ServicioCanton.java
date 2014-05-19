package ec.gob.simintel.administracion.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ec.gob.simintel.entidades.Canton;
import ec.gob.simintel.entidades.Parroquia;
import ec.gob.simintel.entidades.Provincia;
import ec.gob.simintel.servicios.commons.ServicioBase;

@Stateless
public class ServicioCanton extends ServicioBase<Canton>{

	@PersistenceContext
	EntityManager em;
	
	public ServicioCanton() {
		super(Canton.class, ServicioCanton.class);
		// TODO Auto-generated constructor stub
	}
	
	public List<Canton> buscarTipoIdCanton(int cantonId){
		Query q=em.createQuery("SELEC cant FROM Canton  cant  WHERE cant.cantonid = :paramCant");
		q.setParameter("paramCant", cantonId);
		return q.getResultList();
		
	}
	
	public Canton buscarCantonPorNombre (String nombre){
		try {
			Query q =em.createQuery("SELECT cant FROM Canton cant WHERE TRIM(UPPER(cant.nombre)) = TRIM(UPPER(:paramNombre))");
			q.setParameter("paramNombre", nombre);
			return(Canton)q.getSingleResult();
			
		} catch (NoResultException e) {
			return null;
			// TODO: handle exception
		}
	}
	
	public List<Canton> buscarCantonPorNombres (String nombre){
		Query q =em.createQuery("SELECT cant FROM Canton cant WHERE UPPER(TRIM(cant.nombre)) LIKE UPPER(TRIM(:paramNombre))");
		q.setParameter("paramNombre", "%" + nombre+ "%");
		return q.getResultList();
	}

	/**
	 * Busca los cantones que le pertenecen a una provincia
	 * @param idProvincia
	 * @return
	 */
	public List<Canton>  buscarCantonesPorProvincia (Integer idProvincia){
		Query q = em.createQuery("SELECT cant FROM  Canton cant WHERE cant.provincia.provinciaid = :paramIdProvincia)");
		q.setParameter("paramIdProvincia", idProvincia);
		return q.getResultList();
	}	

}
