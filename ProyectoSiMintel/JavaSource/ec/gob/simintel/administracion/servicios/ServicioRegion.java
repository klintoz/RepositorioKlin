package ec.gob.simintel.administracion.servicios;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ec.gob.simintel.entidades.Region;
import ec.gob.simintel.servicios.commons.ServicioBase;

@Stateless
public class ServicioRegion extends ServicioBase<Region>{

	@PersistenceContext
	EntityManager em;
	public ServicioRegion() {
		super(Region.class, ServicioRegion.class);
		// TODO Auto-generated constructor stub
	}

}
