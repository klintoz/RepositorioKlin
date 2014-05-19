package ec.gob.simintel.administracion.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.gob.simintel.entidades.Planinversion;
import ec.gob.simintel.servicios.commons.ServicioBase;

@Stateless
public class ServicioPlanInversion extends ServicioBase<Planinversion> {
	
	@PersistenceContext
	EntityManager em;
	public ServicioPlanInversion() {
		super(Planinversion.class, ServicioPlanInversion.class);
		// TODO Auto-generated constructor stub
	}
	
	
	 /**
	 * Busca los Planes de Inversion  que le pertenecen a una de Inversion
	 * @param idTipoInversion
	 * @return
	 */
	public List<Planinversion>  buscarPlanInverPorInversion (Integer idInversion){
		Query q = em.createQuery("SELECT pliv FROM  Planinversion pliv WHERE pliv.inversion.inversionid = :paramIdInversion)");
		q.setParameter("paramIdInversion", idInversion);
		return q.getResultList();
	}

}
