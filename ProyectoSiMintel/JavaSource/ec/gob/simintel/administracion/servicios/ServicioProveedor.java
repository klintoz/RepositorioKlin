package ec.gob.simintel.administracion.servicios;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.gob.simintel.entidades.Proveedor;
import ec.gob.simintel.servicios.commons.ServicioBase;

@Stateless
public class ServicioProveedor extends ServicioBase<Proveedor>{

	@PersistenceContext
	EntityManager em;
	public ServicioProveedor() {
		super(Proveedor.class, ServicioProveedor.class);
		// TODO Auto-generated constructor stub
	}
	

}
