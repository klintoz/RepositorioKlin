package ec.gob.simintel.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the beneficiarios database table.
 * 
 */
@Entity
@Table(name="beneficiarios")
public class Beneficiario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer beneficiariosid;

	private String actual;

	private Integer canthombres;

	private Integer cantidadtotal;

	private Integer cantmujeres;

	private String referencia;

	//bi-directional many-to-one association to Institucion
    @ManyToOne
	@JoinColumn(name="institucionid")
	private Institucion institucion;

	//bi-directional many-to-one association to Parroquia
    @ManyToOne
	@JoinColumn(name="parroquiaid")
	private Parroquia parroquia;

	//bi-directional many-to-one association to Tipobeneficiario
    @ManyToOne
	@JoinColumn(name="tipobeneficiarioid")
	private Tipobeneficiario tipobeneficiario;

	//bi-directional many-to-one association to Detallebenef
	@OneToMany(mappedBy="beneficiario")
	private List<Detallebenef> detallebenefs;

    public Beneficiario() {
    }

	public Integer getBeneficiariosid() {
		return this.beneficiariosid;
	}

	public void setBeneficiariosid(Integer beneficiariosid) {
		this.beneficiariosid = beneficiariosid;
	}

	public String getActual() {
		return this.actual;
	}

	public void setActual(String actual) {
		this.actual = actual;
	}

	public Integer getCanthombres() {
		return this.canthombres;
	}

	public void setCanthombres(Integer canthombres) {
		this.canthombres = canthombres;
	}

	public Integer getCantidadtotal() {
		return this.cantidadtotal;
	}

	public void setCantidadtotal(Integer cantidadtotal) {
		this.cantidadtotal = cantidadtotal;
	}

	public Integer getCantmujeres() {
		return this.cantmujeres;
	}

	public void setCantmujeres(Integer cantmujeres) {
		this.cantmujeres = cantmujeres;
	}

	public String getReferencia() {
		return this.referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public Institucion getInstitucion() {
		return this.institucion;
	}

	public void setInstitucion(Institucion institucion) {
		this.institucion = institucion;
	}
	
	public Parroquia getParroquia() {
		return this.parroquia;
	}

	public void setParroquia(Parroquia parroquia) {
		this.parroquia = parroquia;
	}
	
	public Tipobeneficiario getTipobeneficiario() {
		return this.tipobeneficiario;
	}

	public void setTipobeneficiario(Tipobeneficiario tipobeneficiario) {
		this.tipobeneficiario = tipobeneficiario;
	}
	
	public List<Detallebenef> getDetallebenefs() {
		return this.detallebenefs;
	}

	public void setDetallebenefs(List<Detallebenef> detallebenefs) {
		this.detallebenefs = detallebenefs;
	}
	
}