package ec.gob.simintel.administracion.servicios;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ec.gob.simintel.entidades.Programa;
import ec.gob.simintel.servicios.commons.ServicioBase;

@Stateless
public class ServicioPrograma extends  ServicioBase<Programa>{
	
	
	@PersistenceContext
	EntityManager em;
	public ServicioPrograma() {
		super(Programa.class, ServicioPrograma.class);
		// TODO Auto-generated constructor stub
	}
	
	public List<Programa> buscarTipoIdPrograma(int pragramaId){
		Query q=em.createQuery("SELECT prog FROM Prprograma prog WHERE prog.programaid = :paramProgra");
		q.setParameter("paramProgra", pragramaId);
		return q.getResultList();
		
	}
	 public  Programa buscarProgramaPorNombre (String nombre){
		 try {
		 Query q=em.createQuery("SELECT prog FROM  Prprograma prog WHERE  TRIM(UPPER(prog.descripcion)) = TRIM(UPPER(:paramProgramas))");
		 q.setParameter("paramProgramas", nombre);
		 return (Programa)q.getSingleResult();
		 
		 	} catch (NoResultException e) {
		 		return null;
			// TODO: handle exception
		}
	 }
		 
	 
	 
	 /**
		 * Busca las Programas que le pertenecen a un plan
		 * @param idPrograma
		 * @return
		 */
		public List<Programa>  buscarProgramaPorPlan (Integer idPlan){
			Query q = em.createQuery("SELECT prog FROM  Programa prog WHERE prog.plan.planid = :paramIdplan)");
			q.setParameter("paramIdplan", idPlan);
			return q.getResultList();
		}
		
	
	 	

}
