package ec.gob.simintel.administracion.servicios;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ec.gob.simintel.entidades.Plan;
import ec.gob.simintel.servicios.commons.ServicioBase;

@Stateless
public class ServicioPlan  extends ServicioBase<Plan>{

	@PersistenceContext
	EntityManager em;
	public ServicioPlan() {
		super(Plan.class, ServicioPlan.class);
		
		// TODO Auto-generated constructor stub
	}
	
	public List<Plan>  buscarPorNombres (String nombres){
		Query q = em.createQuery("SELECT pla FROM  Plan pla WHERE TRIM(UPPER(pla.descripcion))LIKE TRIM(UPPER(:paramNombres))");
		q.setParameter("paramNombres","%" + nombres +"%");
		return q.getResultList();
	}

}
