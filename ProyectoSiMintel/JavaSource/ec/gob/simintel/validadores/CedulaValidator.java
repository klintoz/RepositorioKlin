package ec.gob.simintel.validadores;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Implementa los metodos necesarios para validar la cedula.
 * 
 * @author Clearminds
 * 
 */
public class CedulaValidator implements ConstraintValidator<Cedula, String> {

	/**
	 * Numero de provincias en el país.
	 */
	public static final int NUMERO_DE_PROVINCIAS = 24;// 22;

	@Override
	public void initialize(Cedula arg0) {
	}

	@Override
	public boolean isValid(String cedula, ConstraintValidatorContext arg1) {
		// verifica que tenga 10 dígitos y que contenga solo valores numéricos
		if (!((cedula.length() == 10) && cedula.matches("^[0-9]{10}$"))) {
			return false;
		}

		// verifica que los dos primeros dígitos correspondan a un valor entre 1
		// y NUMERO_DE_PROVINCIAS
		int prov = Integer.parseInt(cedula.substring(0, 2));

		if (!((prov > 0) && (prov <= NUMERO_DE_PROVINCIAS))) {
			return false;
		}

		// verifica que el último dígito de la cédula sea válido
		int[] d = new int[10];

		// Asignamos el string a un array
		for (int i = 0; i < d.length; i++) {
			d[i] = Integer.parseInt(cedula.charAt(i) + "");
		}

		int imp = 0;
		int par = 0;

		// sumamos los duplos de posición impar
		for (int i = 0; i < d.length; i += 2) {
			d[i] = ((d[i] * 2) > 9) ? ((d[i] * 2) - 9) : (d[i] * 2);
			imp += d[i];
		}

		// sumamos los digitos de posición par
		for (int i = 1; i < (d.length - 1); i += 2) {
			par += d[i];
		}

		// Sumamos los dos resultados
		int suma = imp + par;

		// Restamos de la decena superior
		int d10 = Integer.parseInt(String.valueOf(suma + 10).substring(0, 1)
				+ "0")
				- suma;

		// Si es diez el décimo dígito es cero
		d10 = (d10 == 10) ? 0 : d10;

		// si el décimo dígito calculado es igual al digitado la cédula es
		// correcta
		return d10 == d[9];
	}
}
