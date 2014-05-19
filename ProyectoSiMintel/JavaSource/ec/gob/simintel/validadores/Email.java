package ec.gob.simintel.validadores;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Interface para crear la anotacion @Email
 * 
 * @author Clearminds
 * 
 */
@Target(value = { ElementType.METHOD, ElementType.FIELD,
		ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
public @interface Email {

	String message() default "Email inválido";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
