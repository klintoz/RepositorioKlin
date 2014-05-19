package ec.gob.simintel.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the grupodatos database table.
 * 
 */
@Entity
@Table(name="grupodatos")
public class Grupodato implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer grupodatosid;

	private String descripcion;

	//bi-directional many-to-one association to Setatributo
	@OneToMany(mappedBy="grupodato", cascade= {CascadeType.PERSIST ,CascadeType.MERGE})
	private List<Setatributo> setatributos;

	//bi-directional many-to-one association to Tipoinstitucion
	@OneToMany(mappedBy="grupodato")
	private List<Tipoinstitucion> tipoinstitucions;

	//bi-directional many-to-one association to Tipoinversion
	@OneToMany(mappedBy="grupodato")
	private List<Tipoinversion> tipoinversions;

    public Grupodato() {
    }

	public Integer getGrupodatosid() {
		return this.grupodatosid;
	}

	public void setGrupodatosid(Integer grupodatosid) {
		this.grupodatosid = grupodatosid;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Setatributo> getSetatributos() {
		return this.setatributos;
	}

	public void setSetatributos(List<Setatributo> setatributos) {
		this.setatributos = setatributos;
	}
	
	public List<Tipoinstitucion> getTipoinstitucions() {
		return this.tipoinstitucions;
	}

	public void setTipoinstitucions(List<Tipoinstitucion> tipoinstitucions) {
		this.tipoinstitucions = tipoinstitucions;
	}
	
	public List<Tipoinversion> getTipoinversions() {
		return this.tipoinversions;
	}

	public void setTipoinversions(List<Tipoinversion> tipoinversions) {
		this.tipoinversions = tipoinversions;
	}
	
}