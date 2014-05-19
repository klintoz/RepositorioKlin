package ec.gob.simintel.validadores;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Interface para crear la anotacion @Ruc
 * 
 * @author Clearminds
 * 
 */
@Target(value = { ElementType.METHOD, ElementType.FIELD,
		ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RucValidator.class)
public @interface Ruc {

	String message() default "Ruc inválido.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
