package ec.gob.simintel.administracion.servicios;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.gob.simintel.entidades.Pai;
import ec.gob.simintel.entidades.Provincia;
import ec.gob.simintel.servicios.commons.ServicioBase;

@Stateless
public class ServicioProvincia extends ServicioBase<Provincia> {
    
	@PersistenceContext
	EntityManager em;
	public ServicioProvincia() {
		super(Provincia.class, ServicioProvincia.class);
		// TODO Auto-generated constructor stub
	}
	
	public Provincia buscarProvinciaPorNombre (String nombre){
		
		
		try {
			Query q =em.createQuery("SELECT prov FROM  Provincia prov WHERE TRIM(UPPER(prov.nombre))= TRIM(UPPER(:parmNombre))");
			q.setParameter("parmNombre", nombre);
			return(Provincia)q.getSingleResult();
			
		} catch (NoResultException e) {
			return null;
			// TODO: handle exception
		}
	}
	
	public List<Provincia>  buscarProvinciaPorNombres (String nombres){
		Query q = em.createQuery("SELECT prov FROM  Provincia prov WHERE TRIM(UPPER(prov.nombre))= TRIM(UPPER(:parmNombres))");
		q.setParameter("parmNombres","%" + nombres +"%");
		return q.getResultList();
	}
	
	/**
	 * Busca las provincias que le pertenecen a un pais
	 * @param idPais
	 * @return
	 */
	public List<Provincia>  buscarProvinciasPorPais (Integer idPais){
		Query q = em.createQuery("SELECT prov FROM  Provincia prov WHERE prov.pai.paisid = :paramIdpais)");
		q.setParameter("paramIdpais", idPais);
		return q.getResultList();
	}	
	
	

}
