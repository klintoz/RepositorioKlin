package ec.gob.simintel.servicios.commons;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import org.jboss.logging.Logger;

public abstract class ServicioBase<T> {
	@PersistenceContext
	EntityManager em;

	protected static Logger LOG;
	private Class<T> tipoEntidad;
	private Class<?> tipoServicio;

	public ServicioBase(Class<T> tipoEntidad, Class<?> tipoServicio) {
		this.tipoEntidad = tipoEntidad;
		this.tipoServicio = tipoServicio;
	}

	public void insertar(T entidad) {
		LOG.info("Insertando Entidad>>" + entidad);
		em.persist(entidad);
		em.flush();
	}

	public void actualizar(T entidad) {
		LOG.info("Actualizando Entidad>>" + entidad);
		em.merge(entidad);
	}

	public void eliminar(T entidad) {
		LOG.info("Eliminando Entidad>>" + entidad);
		em.remove(em.merge(entidad));
	}

	public T buscarPorId(Integer id) {
		LOG.info("Buscando Entidad con id>>" + id);
		return em.find(tipoEntidad, id);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> buscarTodos() {
		CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(tipoEntidad));
		return em.createQuery(cq).getResultList();
	}

	@PostConstruct
	private void setLogger() {
		if (LOG == null) {
			LOG = Logger.getLogger(tipoServicio);
		}
	}

}
