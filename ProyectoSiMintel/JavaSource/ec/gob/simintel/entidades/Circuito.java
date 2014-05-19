package ec.gob.simintel.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the circuito database table.
 * 
 */
@Entity
public class Circuito implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer circuitoid;

	private String nombre;

	//bi-directional many-to-one association to Institucion
	@OneToMany(mappedBy="circuito")
	private List<Institucion> institucions;

    public Circuito() {
    }

	public Integer getCircuitoid() {
		return this.circuitoid;
	}

	public void setCircuitoid(Integer circuitoid) {
		this.circuitoid = circuitoid;
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