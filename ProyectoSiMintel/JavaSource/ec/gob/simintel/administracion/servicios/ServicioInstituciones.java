package ec.gob.simintel.administracion.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.gob.simintel.entidades.Instbeneficiada;
import ec.gob.simintel.entidades.Institucion;
import ec.gob.simintel.servicios.commons.ServicioBase;
@Stateless
public class ServicioInstituciones extends ServicioBase<Institucion> {

	
	@PersistenceContext
	EntityManager em;
	public ServicioInstituciones() {
		super(Institucion.class , ServicioInstituciones.class);
	}
     
	public Institucion buscarPorNombre (String nombre){
		try {
			Query q =em.createQuery("SELECT inst FROM  Institucion inst WHERE TRIM(UPPER(inst.nombre))= TRIM(UPPER(:paramNombre))");
			q.setParameter("paramNombre", nombre);
			return(Institucion)q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Institucion>  buscarPorNombres (String nombres){
		Query q = em.createQuery("SELECT inst FROM  Institucion inst WHERE TRIM(UPPER(inst.nombre))LIKE TRIM(UPPER(:paramNombres))");
		q.setParameter("paramNombres","%" + nombres +"%");
		return q.getResultList();
	}
	
	/**
	 * Busca una institucion por atributo identificación 
	 * @param identificacion
	 * @return
	 * null: si no encuentra ninguna institucion con dicha identificación 
	 * institucion: la institución que encontro con dicha identificación
	 */
	public Institucion buscarPorIdentificacion (String identificacion){
		try {
			Query q =em.createQuery("SELECT inst FROM  Institucion inst WHERE TRIM(UPPER(inst.identificacion))= TRIM(UPPER(:paramId))");
			q.setParameter("paramId", identificacion);
			return(Institucion)q.getSingleResult();
			
		} catch (NoResultException e) {
			//NoResultException es una excepcion que se lanza en ejecucion si no hay resultado en la consulta
			e.printStackTrace();
			return null;
		} catch (Exception e) { 
			//Exception es padre de todas las excepciones me va a permitir capturar otras miestras el proyecto este en produccion 
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 *  Despliegue  del  Detalle de la Institución 
	 *  
	 */
	
	public Institucion buscarDetalleInstitucion (Integer idInstitucion){
		Query q = em.createQuery("SELECT dit FROM  Institucion dit WHERE dit.institucion.institucionid = :paramIdInstitucion");
		q.setParameter("paramIdInstitucion", idInstitucion);
		return (Institucion) q.getResultList();
		
	}
	
	
}
