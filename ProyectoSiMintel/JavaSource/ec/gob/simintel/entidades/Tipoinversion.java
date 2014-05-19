package ec.gob.simintel.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipoinversion database table.
 * 
 */
@Entity
public class Tipoinversion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer tipoinversionid;

	private String descripcion;

	//bi-directional many-to-one association to Inversion
	@OneToMany(mappedBy="tipoinversion")
	private List<Inversion> inversions;

	//bi-directional many-to-one association to Grupodato
    @ManyToOne
	@JoinColumn(name="grupodatosid")
	private Grupodato grupodato;

    public Tipoinversion() {
    }

	public Integer getTipoinversionid() {
		return this.tipoinversionid;
	}

	public void setTipoinversionid(Integer tipoinversionid) {
		this.tipoinversionid = tipoinversionid;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Inversion> getInversions() {
		return this.inversions;
	}

	public void setInversions(List<Inversion> inversions) {
		this.inversions = inversions;
	}
	
	public Grupodato getGrupodato() {
		return this.grupodato;
	}

	public void setGrupodato(Grupodato grupodato) {
		this.grupodato = grupodato;
	}
	
}