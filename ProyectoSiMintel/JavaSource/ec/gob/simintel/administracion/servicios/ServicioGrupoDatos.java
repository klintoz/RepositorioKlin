package ec.gob.simintel.administracion.servicios;

import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ec.gob.simintel.entidades.Grupodato;
import ec.gob.simintel.entidades.Mediocontacto;
import ec.gob.simintel.servicios.commons.ServicioBase;

@Stateless
public class ServicioGrupoDatos extends ServicioBase<Grupodato>{
	
	@PersistenceContext
	EntityManager em;
	public ServicioGrupoDatos() {
		super(Grupodato.class, ServicioGrupoDatos.class);
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * Busca las Grupos de Datos por su Descripcion
	 * @param descripcion
	 * @return
	 */
	
	public List<Grupodato> buscarPorDescripcion (String descripcion){
		Query q = em.createQuery("SELECT gd FROM  Grupodato gd WHERE TRIM(UPPER(gd.descripcion))LIKE TRIM(UPPER(:paramNombres))");
		q.setParameter("paramNombres","%" + descripcion +"%");
		return q.getResultList();
	}
	

}
