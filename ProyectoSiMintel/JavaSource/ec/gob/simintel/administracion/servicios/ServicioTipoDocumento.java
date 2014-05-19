package ec.gob.simintel.administracion.servicios;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ec.gob.simintel.entidades.Tipodocumento;
import ec.gob.simintel.servicios.commons.ServicioBase;

@Stateless
public class ServicioTipoDocumento extends ServicioBase<Tipodocumento> {

	@PersistenceContext
	EntityManager em;
	public ServicioTipoDocumento() {
		super(Tipodocumento.class, ServicioTipoDocumento.class);
		// TODO Auto-generated constructor stub
	}

}
