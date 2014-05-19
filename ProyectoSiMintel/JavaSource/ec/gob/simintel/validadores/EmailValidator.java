package ec.gob.simintel.validadores;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Implementa los metodos necesarios para validar el email.
 * 
 * @author Clearminds
 * 
 */
public class EmailValidator implements ConstraintValidator<Email, String> {

	@Override
	public void initialize(Email arg0) {
	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext arg1) {
		String expresion = "[a-zA-Z0-9_.-]*[@]{1}[a-zA-Z0-9_-]*([.]{1}[a-zA-Z]{2,3}){1,2}";
		return email.matches(expresion);
	}
}
