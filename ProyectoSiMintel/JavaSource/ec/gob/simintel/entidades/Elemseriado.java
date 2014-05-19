package ec.gob.simintel.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the elemseriado database table.
 * 
 */
@Entity
public class Elemseriado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer elemseriadoid;

	private String garantia;

	private String garantiaaplicada;

    @Temporal( TemporalType.DATE)
	private Date garantiafecha;

	private String serie;

	//bi-directional many-to-one association to Detallemon
	@OneToMany(mappedBy="elemseriado")
	private List<Detallemon> detallemons;

	//bi-directional many-to-one association to Ejecinversion
    @ManyToOne
	@JoinColumn(name="ejecinversionid")
	private Ejecinversion ejecinversion;

	//bi-directional many-to-one association to Elemseriado
    @ManyToOne
	@JoinColumn(name="relacionid")
	private Elemseriado elemseriado;

	//bi-directional many-to-one association to Elemseriado
	@OneToMany(mappedBy="elemseriado")
	private List<Elemseriado> elemseriados;

	//bi-directional many-to-one association to Elemsernovedad
	@OneToMany(mappedBy="elemseriado")
	private List<Elemsernovedad> elemsernovedads;

    public Elemseriado() {
    }

	public Integer getElemseriadoid() {
		return this.elemseriadoid;
	}

	public void setElemseriadoid(Integer elemseriadoid) {
		this.elemseriadoid = elemseriadoid;
	}

	public String getGarantia() {
		return this.garantia;
	}

	public void setGarantia(String garantia) {
		this.garantia = garantia;
	}

	public String getGarantiaaplicada() {
		return this.garantiaaplicada;
	}

	public void setGarantiaaplicada(String garantiaaplicada) {
		this.garantiaaplicada = garantiaaplicada;
	}

	public Date getGarantiafecha() {
		return this.garantiafecha;
	}

	public void setGarantiafecha(Date garantiafecha) {
		this.garantiafecha = garantiafecha;
	}

	public String getSerie() {
		return this.serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public List<Detallemon> getDetallemons() {
		return this.detallemons;
	}

	public void setDetallemons(List<Detallemon> detallemons) {
		this.detallemons = detallemons;
	}
	
	public Ejecinversion getEjecinversion() {
		return this.ejecinversion;
	}

	public void setEjecinversion(Ejecinversion ejecinversion) {
		this.ejecinversion = ejecinversion;
	}
	
	public Elemseriado getElemseriado() {
		return this.elemseriado;
	}

	public void setElemseriado(Elemseriado elemseriado) {
		this.elemseriado = elemseriado;
	}
	
	public List<Elemseriado> getElemseriados() {
		return this.elemseriados;
	}

	public void setElemseriados(List<Elemseriado> elemseriados) {
		this.elemseriados = elemseriados;
	}
	
	public List<Elemsernovedad> getElemsernovedads() {
		return this.elemsernovedads;
	}

	public void setElemsernovedads(List<Elemsernovedad> elemsernovedads) {
		this.elemsernovedads = elemsernovedads;
	}
	
}