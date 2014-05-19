package ec.gob.simintel.administracion.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ec.gob.simintel.entidades.Inversion;
import ec.gob.simintel.servicios.commons.ServicioBase;
@Stateless
public class ServicioGestionInversion extends ServicioBase<Inversion>{

	public ServicioGestionInversion() {
		super(Inversion.class, ServicioGestionInversion.class);
		// TODO Auto-generated constructor stub
	}
	
	@PersistenceContext
	EntityManager em;
	public List<Inversion> buscarInversionesPorConvenio(Integer idConvenino){
		Query q = em.createQuery("SELECT inv FROM Inversion inv WHERE inv.convenio.convenioid = :paramIdConvenio");
		q.setParameter("paramIdConvenio", idConvenino);
		return q.getResultList();
	}
	
	public List<Inversion> buscarInversionPorDescripcion (String descripcion){
		
		Query q =em.createQuery("SELECT inv FROM Inversion inv WHERE UPPER(TRIM(inv.descripcion)) LIKE UPPER(TRIM(:paramDescripcion))");
		q.setParameter("paramDescripcion", "%" + descripcion+ "%");
		return q.getResultList();
		
	
	}

}
