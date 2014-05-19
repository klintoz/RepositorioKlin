package ec.gob.simintel.administracion.servicios;

import javax.ejb.Stateless;

import ec.gob.simintel.entidades.Tipomedio;
import ec.gob.simintel.servicios.commons.ServicioBase;

@Stateless
public class ServicioTipoMedio extends ServicioBase<Tipomedio>{

	public ServicioTipoMedio() {
		super(Tipomedio.class, ServicioTipoMedio.class);
		// TODO Auto-generated constructor stub
	}
}
