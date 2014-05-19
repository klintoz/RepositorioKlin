package ec.gob.simintel.administracion.servicios;

import javax.ejb.Stateless;

import ec.gob.simintel.entidades.Beneficiario;
import ec.gob.simintel.servicios.commons.ServicioBase;

@Stateless
public class ServicioBeneficiarios  extends ServicioBase<Beneficiario>{

	public ServicioBeneficiarios() {
		super (Beneficiario.class, ServicioBeneficiarios.class);
		// TODO Auto-generated constructor stub
	}

}
