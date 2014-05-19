package ec.gob.simintel.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipobeneficiario database table.
 * 
 */
@Entity
public class Tipobeneficiario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer tipobeneficiarioid;

	private String clase;

	private String nombre;

	//bi-directional many-to-one association to Beneficiario
	@OneToMany(mappedBy="tipobeneficiario")
	private List<Beneficiario> beneficiarios;

	//bi-directional many-to-one association to Detallebenef
	@OneToMany(mappedBy="tipobeneficiario")
	private List<Detallebenef> detallebenefs;

	//bi-directional many-to-one association to Tipobeneficiario
    @ManyToOne
	@JoinColumn(name="tipobenefpadreid")
	private Tipobeneficiario tipobeneficiario;

	//bi-directional many-to-one association to Tipobeneficiario
	@OneToMany(mappedBy="tipobeneficiario")
	private List<Tipobeneficiario> tipobeneficiarios;

    public Tipobeneficiario() {
    }

	public Integer getTipobeneficiarioid() {
		return this.tipobeneficiarioid;
	}

	public void setTipobeneficiarioid(Integer tipobeneficiarioid) {
		this.tipobeneficiarioid = tipobeneficiarioid;
	}

	public String getClase() {
		return this.clase;
	}

	public void setClase(String clase) {
		this.clase = clase;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Beneficiario> getBeneficiarios() {
		return this.beneficiarios;
	}

	public void setBeneficiarios(List<Beneficiario> beneficiarios) {
		this.beneficiarios = beneficiarios;
	}
	
	public List<Detallebenef> getDetallebenefs() {
		return this.detallebenefs;
	}

	public void setDetallebenefs(List<Detallebenef> detallebenefs) {
		this.detallebenefs = detallebenefs;
	}
	
	public Tipobeneficiario getTipobeneficiario() {
		return this.tipobeneficiario;
	}

	public void setTipobeneficiario(Tipobeneficiario tipobeneficiario) {
		this.tipobeneficiario = tipobeneficiario;
	}
	
	public List<Tipobeneficiario> getTipobeneficiarios() {
		return this.tipobeneficiarios;
	}

	public void setTipobeneficiarios(List<Tipobeneficiario> tipobeneficiarios) {
		this.tipobeneficiarios = tipobeneficiarios;
	}
	
}