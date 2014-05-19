package ec.gob.simintel.entidades;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the inversion database table.
 * 
 */
@Entity
public class Inversion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer inversionid;

	private String descripcion;

	private double presupuestocalculado;

	//bi-directional many-to-one association to Instbeneficiada
	@OneToMany(mappedBy="inversion")
	private List<Instbeneficiada> instbeneficiadas;

	//bi-directional many-to-one association to Convenio
    @ManyToOne
	@JoinColumn(name="convenioid")
	private Convenio convenio;

	//bi-directional many-to-one association to Tipoinversion
    @ManyToOne
	@JoinColumn(name="tipoinversionid")
	private Tipoinversion tipoinversion;

	//bi-directional many-to-one association to Planinversion
	@OneToMany(mappedBy="inversion", cascade= {CascadeType.PERSIST ,CascadeType.MERGE})
	private List<Planinversion> planinversions;

	//bi-directional many-to-one association to Plazo
	@OneToMany(mappedBy="inversion")
	private List<Plazo> plazos;

    public Inversion() {
    }

	public Integer getInversionid() {
		return this.inversionid;
	}

	public void setInversionid(Integer inversionid) {
		this.inversionid = inversionid;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPresupuestocalculado() {
		return this.presupuestocalculado;
	}

	public void setPresupuestocalculado(double presupuestocalculado) {
		this.presupuestocalculado = presupuestocalculado;
	}

	public List<Instbeneficiada> getInstbeneficiadas() {
		return this.instbeneficiadas;
	}

	public void setInstbeneficiadas(List<Instbeneficiada> instbeneficiadas) {
		this.instbeneficiadas = instbeneficiadas;
	}
	
	public Convenio getConvenio() {
		return this.convenio;
	}

	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
	}
	
	public Tipoinversion getTipoinversion() {
		return this.tipoinversion;
	}

	public void setTipoinversion(Tipoinversion tipoinversion) {
		this.tipoinversion = tipoinversion;
	}
	
	public List<Planinversion> getPlaninversions() {
		return this.planinversions;
	}

	public void setPlaninversions(List<Planinversion> planinversions) {
		this.planinversions = planinversions;
	}
	
	public List<Plazo> getPlazos() {
		return this.plazos;
	}

	public void setPlazos(List<Plazo> plazos) {
		this.plazos = plazos;
	}
	
}