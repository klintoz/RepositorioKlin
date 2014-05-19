package ec.gob.simintel.administracion.servicios;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ec.gob.simintel.entidades.Tiposostenimiento;
import ec.gob.simintel.servicios.commons.ServicioBase;

@Stateless
public class ServicioTipoSostenimiento extends ServicioBase<Tiposostenimiento> {
	@PersistenceContext
	EntityManager em;
	public ServicioTipoSostenimiento () {
		super(Tiposostenimiento.class, ServicioTipoSostenimiento.class);
		// TODO Auto-generated constructor stub
	}

}
