package ec.gob.simintel.administracion.controladores;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;

import ec.gob.simintel.administracion.servicios.ServicioSetAtributo;
import ec.gob.simintel.entidades.Setatributo;


@ManagedBean
@Stateless
public class ControladorSetAtributo {
	
	@EJB
	private ServicioSetAtributo srvSetAtributo;
	private Setatributo setatributo;
	private  Integer idsetAtributo ; 

}
