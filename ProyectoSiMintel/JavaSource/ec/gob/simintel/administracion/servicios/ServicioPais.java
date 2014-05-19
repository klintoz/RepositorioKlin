package ec.gob.simintel.administracion.servicios;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.gob.simintel.entidades.Pai;
import ec.gob.simintel.servicios.commons.ServicioBase;

@Stateless
public class ServicioPais extends ServicioBase<Pai> {
    
	@PersistenceContext
	EntityManager em;
	public ServicioPais() {
		super(Pai.class, ServicioPais.class);
		
	}
	
}
