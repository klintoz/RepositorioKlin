package ec.gob.simintel.administracion.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.gob.simintel.entidades.Atributossetatrib;
import ec.gob.simintel.servicios.commons.ServicioBase;

@Stateless
public class ServicioAtributosEquipo extends ServicioBase<Atributossetatrib> {

	@PersistenceContext
	EntityManager em;
	public ServicioAtributosEquipo() {
		super(Atributossetatrib.class, ServicioAtributosEquipo.class);
		// TODO Auto-generated constructor stub
	}
	
	public List<Atributossetatrib> buscarPorTextoDespliegue ( String textoDesp){
		Query q = em.createQuery("SELECT ateq FROM Atributossetatrib  ateq WHERE TRIM(UPPER(ateq.textodespliegue))LIKE TRIM(UPPER(:paramTexto))");
		q.setParameter("paramTexto","%" + textoDesp + "%");
		return q.getResultList();
	}
	
	
			
		
				
		
	
	

}
