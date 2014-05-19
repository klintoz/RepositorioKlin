package ec.gob.simintel.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the distrito database table.
 * 
 */
@Entity
public class Distrito implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer distritoid;

	private String nombre;

	//bi-directional many-to-one association to Institucion
	@OneToMany(mappedBy="distrito")
	private List<Institucion> institucions;

    public Distrito() {
    }

	public Integer getDistritoid() {
		return this.distritoid;
	}

	public void setDistritoid(Integer distritoid) {
		this.distritoid = distritoid;
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
	
}