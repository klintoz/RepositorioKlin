package ec.gob.simintel.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the detallemon database table.
 * 
 */
@Entity
public class Detallemon implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer detallemonid;

    @Temporal( TemporalType.DATE)
	private Date fechanovedad;

    @Temporal( TemporalType.DATE)
	private Date fechasolucion;

	private String novedades;

	private String solucion;

	//bi-directional many-to-one association to Elemseriado
    @ManyToOne
	@JoinColumn(name="elemseriadoid")
	private Elemseriado elemseriado;

	//bi-directional many-to-one association to Monitoreo
    @ManyToOne
	@JoinColumn(name="monitoreoid")
	private Monitoreo monitoreo;

    public Detallemon() {
    }

	public Integer getDetallemonid() {
		return this.detallemonid;
	}

	public void setDetallemonid(Integer detallemonid) {
		this.detallemonid = detallemonid;
	}

	public Date getFechanovedad() {
		return this.fechanovedad;
	}

	public void setFechanovedad(Date fechanovedad) {
		this.fechanovedad = fechanovedad;
	}

	public Date getFechasolucion() {
		return this.fechasolucion;
	}

	public void setFechasolucion(Date fechasolucion) {
		this.fechasolucion = fechasolucion;
	}

	public String getNovedades() {
		return this.novedades;
	}

	public void setNovedades(String novedades) {
		this.novedades = novedades;
	}

	public String getSolucion() {
		return this.solucion;
	}

	public void setSolucion(String solucion) {
		this.solucion = solucion;
	}

	public Elemseriado getElemseriado() {
		return this.elemseriado;
	}

	public void setElemseriado(Elemseriado elemseriado) {
		this.elemseriado = elemseriado;
	}
	
	public Monitoreo getMonitoreo() {
		return this.monitoreo;
	}

	public void setMonitoreo(Monitoreo monitoreo) {
		this.monitoreo = monitoreo;
	}
	
}