package ec.gob.simintel.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tiposostenimiento database table.
 * 
 */
@Entity
public class Tiposostenimiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer tiposostenimientoid;

	private String nombre;

	//bi-directional many-to-one association to Institucion
	@OneToMany(mappedBy="tiposostenimiento")
	private List<Institucion> institucions;

	//bi-directional many-to-one association to Tipoinstitucion
    @ManyToOne
	@JoinColumn(name="tipoinstitucionid")
	private Tipoinstitucion tipoinstitucion;

    public Tiposostenimiento() {
    }

	public Integer getTiposostenimientoid() {
		return this.tiposostenimientoid;
	}

	public void setTiposostenimientoid(Integer tiposostenimientoid) {
		this.tiposostenimientoid = tiposostenimientoid;
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
	
	public Tipoinstitucion getTipoinstitucion() {
		return this.tipoinstitucion;
	}

	public void setTipoinstitucion(Tipoinstitucion tipoinstitucion) {
		this.tipoinstitucion = tipoinstitucion;
	}
	
}