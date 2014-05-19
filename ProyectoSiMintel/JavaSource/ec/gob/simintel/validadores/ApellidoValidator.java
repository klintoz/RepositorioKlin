package ec.gob.simintel.validadores;

import java.util.ArrayList;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Implementa los metodos necesarios para validar el apellido.
 * 
 * @author Clearminds
 * 
 */
public class ApellidoValidator implements ConstraintValidator<Apellido, String> {

	@Override
	public void initialize(Apellido arg0) {
	}

	@Override
	public boolean isValid(String apellido, ConstraintValidatorContext arg1) {
		String expresion = "[ ]*[A-ZÁÉÍÓÚ]{1}[a-záéíóú]+[ ]*(([A-ZÁÉÍÓÚ]{1}[a-záéíóú]+)*[ ]*)*";

		ArrayList<String> controles = new ArrayList<String>();
		controles.add("de ");
		controles.add("las ");
		controles.add("la ");
		controles.add("los ");

		StringBuffer sb = new StringBuffer(apellido);

		for (String aux : controles) {
			if (apellido.contains(aux)) {
				sb.delete(sb.indexOf(aux), sb.indexOf(aux) + aux.length());
			}
		}

		if (sb.toString().matches(expresion)) {
			return true;
		}
		return false;
	}
}
