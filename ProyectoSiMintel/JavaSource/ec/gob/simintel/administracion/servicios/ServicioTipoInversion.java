package ec.gob.simintel.administracion.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.gob.simintel.entidades.Tipoinversion;
import ec.gob.simintel.servicios.commons.ServicioBase;

@Stateless
public class ServicioTipoInversion extends ServicioBase<Tipoinversion> {

	public ServicioTipoInversion() {
		super(Tipoinversion.class, ServicioTipoInversion.class);
		// TODO Auto-generated constructor stub
	}
	
	@PersistenceContext
	EntityManager em;
	public List<Tipoinversion> buscarTipoInversionesPorGrupoInversion(Integer idGrupoDatoInversion){
		Query q = em.createQuery("SELECT tinv FROM Tipoinversion tinv WHERE tinv.grupodato.grupodatosid = :paramIdGInversion");
		q.setParameter("paramIdGInversion", idGrupoDatoInversion);
		return q.getResultList();
	}

}
