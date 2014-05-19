package ec.gob.simintel.administracion.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.gob.simintel.entidades.Canton;
import ec.gob.simintel.entidades.Convenio;
import ec.gob.simintel.entidades.Parroquia;
import ec.gob.simintel.servicios.commons.ServicioBase;
@Stateless
public class ServicioParroquia extends ServicioBase<Parroquia>{

	@PersistenceContext
	EntityManager em;
	public ServicioParroquia() {
		super(Parroquia.class, ServicioParroquia.class);
		// TODO Auto-generated constructor stub
	}
	
	public List<Parroquia> buscarTipoIdParroquia(int parroquiaId){
		Query q=em.createQuery("SELEC parr FROM Parroquia  parr  WHERE parr.parroquiaid = :paramParroq");
		q.setParameter("paramParroq", parroquiaId);
		return q.getResultList();
		
	}
	
	public Parroquia buscarParroquiaPorNombre (String nombre){
		try {
			Query q =em.createQuery("SELECT parr FROM Parroquia parr WHERE TRIM(UPPER(parr.nombre)) = TRIM(UPPER(:paramNombre))");
			q.setParameter("paramNombre", nombre);
			return(Parroquia)q.getSingleResult();
			
		} catch (NoResultException e) {
			return null;
			// TODO: handle exception
		}
	}
	
	public List<Parroquia> buscarParroquiaPorNombres (String nombre){
		
		Query q =em.createQuery("SELECT parr FROM Parroquia parr WHERE UPPER(TRIM(parr.nombre)) LIKE UPPER(TRIM(:paramNombre))");
		q.setParameter("paramNombre", "%" + nombre+ "%");
		return q.getResultList();
	}
	
	/**
	 * Busca las parroquias que le pertenecen a un cantón
	 * @param idCanton
	 * @return
	 */
	public List<Parroquia>  buscarParroquiasPorCanton (Integer idCanton){
		Query q = em.createQuery("SELECT parr FROM  Parroquia parr WHERE parr.canton.cantonid = :paramIdCanton)");
		q.setParameter("paramIdCanton", idCanton);
		return q.getResultList();
	}	
	

}
