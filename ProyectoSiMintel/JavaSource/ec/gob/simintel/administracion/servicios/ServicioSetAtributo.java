package ec.gob.simintel.administracion.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.gob.simintel.entidades.Mediocontacto;
import ec.gob.simintel.entidades.Setatributo;
import ec.gob.simintel.servicios.commons.ServicioBase;

@Stateless
public class ServicioSetAtributo extends ServicioBase<Setatributo>{
	
	@PersistenceContext
	EntityManager em;
	public ServicioSetAtributo() {
		super(Setatributo.class, ServicioSetAtributo.class);
		// TODO Auto-generated constructor stub
	}
	
	
	 /**
	 * Busca los Equipos  que le pertenecen a un Grupo de Datos
	 * @param idGrupoDtos
	 * @return
	 */
	public List<Setatributo>  buscarEquipoPorGrupodeDatos (Integer idGrupoDtos){
		Query q = em.createQuery("SELECT sta FROM  Setatributo sta WHERE sta.grupodato.grupodatosid = :paramIdGrupoDtos)");
		q.setParameter("paramIdGrupoDtos", idGrupoDtos);
		return q.getResultList();
	}

	@Override
	public void eliminar(Setatributo equipo) {		
		Query query=em.createNativeQuery("delete from setatributos where setatributosid="+equipo.getSetatributosid());
		query.executeUpdate();
	}
}
