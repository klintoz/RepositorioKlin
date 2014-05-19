package ec.gob.simintel.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the detallebenef database table.
 * 
 */
@Entity
public class Detallebenef implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer detallebenefid;

	private Integer canthombres;

	private Integer cantidadtotal;

	private Integer cantmujeres;

	//bi-directional many-to-one association to Beneficiario
    @ManyToOne
	@JoinColumn(name="beneficiariosid")
	private Beneficiario beneficiario;

	//bi-directional many-to-one association to Tipobeneficiario
    @ManyToOne
	@JoinColumn(name="tipobeneficiarioid")
	private Tipobeneficiario tipobeneficiario;

    public Detallebenef() {
    }

	public Integer getDetallebenefid() {
		return this.detallebenefid;
	}

	public void setDetallebenefid(Integer detallebenefid) {
		this.detallebenefid = detallebenefid;
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

	public Beneficiario getBeneficiario() {
		return this.beneficiario;
	}

	public void setBeneficiario(Beneficiario beneficiario) {
		this.beneficiario = beneficiario;
	}
	
	public Tipobeneficiario getTipobeneficiario() {
		return this.tipobeneficiario;
	}

	public void setTipobeneficiario(Tipobeneficiario tipobeneficiario) {
		this.tipobeneficiario = tipobeneficiario;
	}
	
}