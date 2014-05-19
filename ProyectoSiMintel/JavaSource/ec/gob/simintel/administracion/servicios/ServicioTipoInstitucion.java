package ec.gob.simintel.administracion.servicios;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.gob.simintel.entidades.Tipoinstitucion;
import ec.gob.simintel.servicios.commons.ServicioBase;

@Stateless
public class ServicioTipoInstitucion extends ServicioBase<Tipoinstitucion> {
	
	@PersistenceContext
	EntityManager em;
	public ServicioTipoInstitucion (){
		super(Tipoinstitucion.class, ServicioTipoInstitucion.class);
		// TODO Auto-generated constructor stub
	}

}
