package ec.gob.simintel.administracion.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ec.gob.simintel.entidades.Contacto;
import ec.gob.simintel.servicios.commons.ServicioBase;

@Stateless
public class ServicioContactos extends ServicioBase<Contacto>{
	@PersistenceContext
	EntityManager em;
	public ServicioContactos() {
		super(Contacto.class, ServicioContactos.class);
		// TODO Auto-generated constructor stub
	}
	
	
	
	/**
	 * Busca el DatoContacto que cargara en la lista de  un Contacto para utilizarlo en la Institución
	 * @param idContacto
	 * @return
	 */
	public List<Contacto> buscarPorDatoContacto(Integer idDatoContacto){
		Query q= em.createQuery("SELECT cont FROM Contacto cont WHERE cont.datoscontacto.datoscontactoid =:paramIddatoContacto");
		q.setParameter("paramIddatoContacto",idDatoContacto);
		return q.getResultList();
		
		
	}
	
	public List<Contacto> buscarPorInstitucion(Integer idInstitucion){
		Query q= em.createQuery("SELECT cont FROM Contacto cont WHERE cont.institucion.institucionid = :param");
		q.setParameter("param",idInstitucion);
		return q.getResultList();		
		
	}
	
	@Override
	public void eliminar(Contacto contacto) {		
		Query query=em.createNativeQuery("delete from contactos where contactosid="+ contacto.getContactosid());
		query.executeUpdate();
	}

}

