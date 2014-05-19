package ec.gob.simintel.controladores.commons;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class GeneradorMensajes {

	public static void mostrarMensajeError(String mensajeError) {
		FacesContext.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								mensajeError, ""));
	}

	public static void mostrarMensajeInformacion(String mensajeInfo) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, mensajeInfo, ""));
	}

}
