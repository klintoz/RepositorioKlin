package ec.gob.simintel.administracion.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.gob.simintel.entidades.Canton;
import ec.gob.simintel.entidades.Convenio;
import ec.gob.simintel.servicios.commons.ServicioBase;
@Stateless
public class ServicioConvenio extends ServicioBase<Convenio>{

	
	
	@PersistenceContext
	EntityManager em;
	public ServicioConvenio() {
		super(Convenio.class, ServicioConvenio.class);
		// TODO Auto-generated constructor stub
	}
	
	public List<Convenio> buscarTipoIdConvenio(int convenioId){
		Query q=em.createQuery("SELEC conv FROM Convenio  conv  WHERE con.convenioid = :paramConve");
		q.setParameter("paramConve", convenioId);
		return q.getResultList();
		
	}
	
	public Convenio buscarConvenioPorNombre (String nombre){
		try {
			Query q =em.createQuery("SELECT conv FROM Convenio conv WHERE TRIM(UPPER(conv.descripcion)) = TRIM(UPPER(:paramNombre))");
			q.setParameter("paramNombre", nombre);
			return(Convenio)q.getSingleResult();
			
		} catch (NoResultException e) {
			return null;
			// TODO: handle exception
		}
	}
	
	public List<Convenio> buscarConvenioPorNombres (String nombre){
		
		Query q =em.createQuery("SELECT conv FROM Convenio conv WHERE UPPER(TRIM(conv.nombre)) LIKE UPPER(TRIM(:paramNombre))");
		q.setParameter("paramNombre", "%" + nombre+ "%");
		return q.getResultList();
		
	
	}
	/**
	 * Busca los convenio que le pertenecen a una proyecto
	 * @param idProyecto
	 * @return
	 */
	public List<Convenio>  buscarConvenioPorProyecto (Integer idProyecto){
		Query q = em.createQuery("SELECT conv FROM  Convenio conv WHERE conv.proyecto.proyectoid = :paramIdProyecto)");
		q.setParameter("paramIdProyecto", idProyecto);
		return q.getResultList();
	}	

}
