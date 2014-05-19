package ec.gob.simintel.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipomedio database table.
 * 
 */
@Entity
public class Tipomedio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer tipomedioid;

	private String nombre;

	//bi-directional many-to-one association to Mediocontacto
	@OneToMany(mappedBy="tipomedio")
	private List<Mediocontacto> mediocontactos;

    public Tipomedio() {
    }

	public Integer getTipomedioid() {
		return this.tipomedioid;
	}

	public void setTipomedioid(Integer tipomedioid) {
		this.tipomedioid = tipomedioid;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Mediocontacto> getMediocontactos() {
		return this.mediocontactos;
	}

	public void setMediocontactos(List<Mediocontacto> mediocontactos) {
		this.mediocontactos = mediocontactos;
	}
	
}