package ec.gob.simintel.administracion.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.gob.simintel.entidades.Mediocontacto;
import ec.gob.simintel.servicios.commons.ServicioBase;

@Stateless
public class ServicioMedioContacto extends ServicioBase<Mediocontacto>{

	
	@PersistenceContext
	EntityManager em;
	public ServicioMedioContacto() {
		super(Mediocontacto.class, ServicioMedioContacto.class);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Busca las MediosContacto que le pertenecen a un DatoContacto
	 * @param idMedioContacto
	 * @return
	 */
	
	public List<Mediocontacto> buscarPorDatosContacto(Integer idDatosContacto){
		Query q= em.createQuery("SELECT mcont FROM Mediocontacto mcont WHERE mcont.datoscontacto.datoscontactoid =:paramIddatoCont ");
		q.setParameter("paramIddatoCont",idDatosContacto);
		return q.getResultList();
		
		
	}
	
	@Override
	public void eliminar(Mediocontacto medioContacto) {		
		Query query=em.createNativeQuery("delete from mediocontacto where mediocontactoid="+medioContacto.getMediocontactoid());
		query.executeUpdate();
	}

}
