package ec.gob.simintel.seguridad.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;
import ec.gob.simintel.entidades.Persona;
import ec.gob.simintel.servicios.commons.ServicioBase;

@Stateless
public class ServicioPersona extends ServicioBase<Persona>{
	
	public ServicioPersona() {
		super(Persona.class, ServicioPersona.class);
		// TODO Auto-generated constructor stub
	}
}
	
	

