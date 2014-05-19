package ec.gob.simintel.seguridad.controladores;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import ec.gob.simintel.commons.datamanager.PaginasDataManager;
import ec.gob.simintel.entidades.Pagina;



/**
 * Convertidor Utiliza un String y lo transforma a un Objeto Pagina, y viceversa
 * 
 */

@FacesConverter("convertidorPagina")
public class ConvertidorPagina implements Converter {

	

	/***
	 * Utiliza el String orden para ordenar la lista de menus.
	 */
	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String orden) {
		System.out.println("CONVERTER DE STRING A OBJECT");
		
		PaginasDataManager paginaDataManager=(PaginasDataManager)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("insumoDataManager");
		System.out.println("IDM: "+paginaDataManager);
		List<Pagina> lstPaginas = paginaDataManager.getPaginasVisibles(); 
		
		System.out.println(" TAMAÑO LISTA ----> " + lstPaginas.size());
		if (orden != null && orden.trim().equals("")) {
			return null;
		} else {
			try {
				int id = Integer.parseInt(orden);
				for (Pagina i : lstPaginas) {
					if (i.getId() == id) {
						return i;
					}
				}
			} catch (Exception ex) {
				throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
						"Error al realizar al conversión."));
			}
		}
		return null;
	}

	/***
	 * Utiliza el objeto pagina, y retorna el orden del objeto.
	 */
	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object pagina) {
		if (pagina == null || pagina.equals("")) {
			return "";
		} else {
			System.out.println(" ------> "+ String.valueOf(((Pagina) pagina).getId()));
			return String.valueOf(((Pagina) pagina).getId());
		}
	}
}
