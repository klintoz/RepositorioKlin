package ec.gob.simintel.administracion.servicios;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ec.gob.simintel.entidades.Datoscontacto;
import ec.gob.simintel.entidades.Mediocontacto;
import ec.gob.simintel.servicios.commons.ServicioBase;

@Stateless
public class ServicioDatosContactos  extends ServicioBase<Datoscontacto>{

	
	@PersistenceContext
	EntityManager em;
	public ServicioDatosContactos() {
		super(Datoscontacto.class, ServicioDatosContactos.class);
		// TODO Auto-generated constructor stub
	}
	
	public List<Datoscontacto> buscarDatoContacto( String nombre){
		Query q= em.createQuery("SELECT dcont FROM Datoscontacto dcont  WHERE  TRIM(UPPER(dcont.nombre))LIKE TRIM(UPPER(:paramNombre))");
		q.setParameter("paramNombre","%"+ nombre+"%" );
		return q.getResultList();
		
	}
	
	public Datoscontacto buscarIdentificacion(String identificacion){
		try {
			Query q = em.createQuery("SELECT datcon FROM Datoscontacto datcon WHERE datcon.identificacion = :paramIdentificacion");
			q.setParameter("paramIdentificacion", identificacion);
			return (Datoscontacto)q.getSingleResult();
		} catch (NoResultException e) {
			
			// TODO: handle exception
			return null;
		}	
		
	}
	
	public List<Datoscontacto> buscarIdenticacionContacto(String identificacion){
		Query q = em.createQuery("SELECT datcon FROM Datoscontacto datcon WHERE datcon.identificacion = :paramIdentificacion");
		q.setParameter("paramIdentificacion", identificacion);
		return q.getResultList();
	}
	
	
	@Override
	public void eliminar(Datoscontacto datoscontacto) {		
		Query query=em.createNativeQuery("delete from datoscontacto where datoscontactoid="+datoscontacto.getDatoscontactoid());
		query.executeUpdate();
	}

}


