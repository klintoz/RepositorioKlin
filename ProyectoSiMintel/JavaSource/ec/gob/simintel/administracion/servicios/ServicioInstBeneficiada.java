package ec.gob.simintel.administracion.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.gob.simintel.entidades.Instbeneficiada;
import ec.gob.simintel.servicios.commons.ServicioBase;

@Stateless
public class ServicioInstBeneficiada extends ServicioBase<Instbeneficiada>{
	
	@PersistenceContext
	EntityManager em;
	public ServicioInstBeneficiada() {
		super(Instbeneficiada.class, ServicioInstBeneficiada.class );
		// TODO Auto-generated constructor stub
	}


	
	/**
	 * Permite buscar en cuantas veces la institucion se encuentra beneficiada en los diferentes tipos de inversiones 
	   ,ya sea equipamiento o Conectividad
	 * @param idInstitucion
	 * @return Una lista de Inversiones en las cuales la institucion esta beneficiada
	 */
	public List<Instbeneficiada> buscarInstBeneficios (Integer idInstitucion){
		Query q = em.createQuery("SELECT ib FROM  Instbeneficiada ib WHERE ib.institucion.institucionid = :paramIdInstitucion");
		q.setParameter("paramIdInstitucion", idInstitucion);
		return q.getResultList();
		
	}
	
	

}
