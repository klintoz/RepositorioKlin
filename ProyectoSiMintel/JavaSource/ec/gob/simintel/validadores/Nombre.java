package ec.gob.simintel.validadores;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Interface para crear la anotacion @Nombre
 * 
 * @author Clearminds
 * 
 */
@Target(value = { ElementType.METHOD, ElementType.FIELD,
		ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NombreValidator.class)
public @interface Nombre {

	String message() default "Tipo de Vehículo. Ejemplo: Automóvil";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
