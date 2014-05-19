package ec.gob.simintel.validadores;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

public class UtilRedireccion {

	public static void redireccionar(String pagina) {
		try {
			if ((pagina.compareTo("#") == 0) || (pagina.compareTo("null") == 0)) {
				Object request = FacesContext.getCurrentInstance()
						.getExternalContext().getRequest();
				if (request instanceof HttpServletRequest) {
					FacesContext
							.getCurrentInstance()
							.getExternalContext()
							.redirect(
									((HttpServletRequest) request)
											.getRequestURL().toString() + "#");
					System.out.println("Redireccionara con el # hacia: "
							+ ((HttpServletRequest) request).getRequestURL()
									.toString() + "#");
				}
			} else {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect(getContextRoot() + "/" + pagina);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getContextRoot() {
		String contextPath = null;
		Object request = FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		if (request instanceof HttpServletRequest) {
			contextPath = ((HttpServletRequest) request).getContextPath()
					.toString();
		}
		return contextPath;
	}

}
