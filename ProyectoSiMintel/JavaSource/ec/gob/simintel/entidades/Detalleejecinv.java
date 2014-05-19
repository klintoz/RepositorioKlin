package ec.gob.simintel.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the detalleejecinv database table.
 * 
 */
@Entity
public class Detalleejecinv implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer detalleejecinvid;

    @Temporal( TemporalType.DATE)
	private Date fechafin;

    @Temporal( TemporalType.DATE)
	private Date fechainicio;

	private String valoratrib;

	//bi-directional many-to-one association to Atributossetatrib
    @ManyToOne
	@JoinColumn(name="atributossetatribid")
	private Atributossetatrib atributossetatrib;

	//bi-directional many-to-one association to Detalleejecinv
    @ManyToOne
	@JoinColumn(name="relacionid")
	private Detalleejecinv detalleejecinv;

	//bi-directional many-to-one association to Detalleejecinv
	@OneToMany(mappedBy="detalleejecinv")
	private List<Detalleejecinv> detalleejecinvs;

	//bi-directional many-to-one association to Ejecinversion
    @ManyToOne
	@JoinColumn(name="ejecinversionid")
	private Ejecinversion ejecinversion;

    public Detalleejecinv() {
    }

	public Integer getDetalleejecinvid() {
		return this.detalleejecinvid;
	}

	public void setDetalleejecinvid(Integer detalleejecinvid) {
		this.detalleejecinvid = detalleejecinvid;
	}

	public Date getFechafin() {
		return this.fechafin;
	}

	public void setFechafin(Date fechafin) {
		this.fechafin = fechafin;
	}

	public Date getFechainicio() {
		return this.fechainicio;
	}

	public void setFechainicio(Date fechainicio) {
		this.fechainicio = fechainicio;
	}

	public String getValoratrib() {
		return this.valoratrib;
	}

	public void setValoratrib(String valoratrib) {
		this.valoratrib = valoratrib;
	}

	public Atributossetatrib getAtributossetatrib() {
		return this.atributossetatrib;
	}

	public void setAtributossetatrib(Atributossetatrib atributossetatrib) {
		this.atributossetatrib = atributossetatrib;
	}
	
	public Detalleejecinv getDetalleejecinv() {
		return this.detalleejecinv;
	}

	public void setDetalleejecinv(Detalleejecinv detalleejecinv) {
		this.detalleejecinv = detalleejecinv;
	}
	
	public List<Detalleejecinv> getDetalleejecinvs() {
		return this.detalleejecinvs;
	}

	public void setDetalleejecinvs(List<Detalleejecinv> detalleejecinvs) {
		this.detalleejecinvs = detalleejecinvs;
	}
	
	public Ejecinversion getEjecinversion() {
		return this.ejecinversion;
	}

	public void setEjecinversion(Ejecinversion ejecinversion) {
		this.ejecinversion = ejecinversion;
	}
	
}