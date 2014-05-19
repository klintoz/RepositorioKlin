package ec.gob.simintel.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipoinstitucion database table.
 * 
 */
@Entity
public class Tipoinstitucion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer tipoinstitucionid;

	private String descripcion;

	private String nombre;

	//bi-directional many-to-one association to Institucion
	@OneToMany(mappedBy="tipoinstitucion")
	private List<Institucion> institucions;

	//bi-directional many-to-one association to Grupodato
    @ManyToOne
	@JoinColumn(name="grupodatosid")
	private Grupodato grupodato;

	//bi-directional many-to-one association to Tipoinstitucion
    @ManyToOne
	@JoinColumn(name="tipoinspadreid")
	private Tipoinstitucion tipoinstitucion;

	//bi-directional many-to-one association to Tipoinstitucion
	@OneToMany(mappedBy="tipoinstitucion")
	private List<Tipoinstitucion> tipoinstitucions;

	//bi-directional many-to-one association to Tiposostenimiento
	@OneToMany(mappedBy="tipoinstitucion")
	private List<Tiposostenimiento> tiposostenimientos;

    public Tipoinstitucion() {
    }

	public Integer getTipoinstitucionid() {
		return this.tipoinstitucionid;
	}

	public void setTipoinstitucionid(Integer tipoinstitucionid) {
		this.tipoinstitucionid = tipoinstitucionid;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Institucion> getInstitucions() {
		return this.institucions;
	}

	public void setInstitucions(List<Institucion> institucions) {
		this.institucions = institucions;
	}
	
	public Grupodato getGrupodato() {
		return this.grupodato;
	}

	public void setGrupodato(Grupodato grupodato) {
		this.grupodato = grupodato;
	}
	
	public Tipoinstitucion getTipoinstitucion() {
		return this.tipoinstitucion;
	}

	public void setTipoinstitucion(Tipoinstitucion tipoinstitucion) {
		this.tipoinstitucion = tipoinstitucion;
	}
	
	public List<Tipoinstitucion> getTipoinstitucions() {
		return this.tipoinstitucions;
	}

	public void setTipoinstitucions(List<Tipoinstitucion> tipoinstitucions) {
		this.tipoinstitucions = tipoinstitucions;
	}
	
	public List<Tiposostenimiento> getTiposostenimientos() {
		return this.tiposostenimientos;
	}

	public void setTiposostenimientos(List<Tiposostenimiento> tiposostenimientos) {
		this.tiposostenimientos = tiposostenimientos;
	}
	
}