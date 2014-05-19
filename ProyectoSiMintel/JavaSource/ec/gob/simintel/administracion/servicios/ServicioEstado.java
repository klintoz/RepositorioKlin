package ec.gob.simintel.administracion.servicios;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ec.gob.simintel.entidades.Estado;
import ec.gob.simintel.servicios.commons.ServicioBase;

@Stateless					
public class ServicioEstado extends ServicioBase<Estado>{
	
	@PersistenceContext
	EntityManager em;
	public ServicioEstado() {
		super(Estado.class, ServicioEstado.class);
		// TODO Auto-generated constructor stub
	}

}
