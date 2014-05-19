package ec.gob.simintel.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipodocumento database table.
 * 
 */
@Entity
public class Tipodocumento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer tipodocumentoid;

	private String clase;

	private String nombre;

	//bi-directional many-to-one association to Institucion
	@OneToMany(mappedBy="tipodocumento")
	private List<Institucion> institucions;

    public Tipodocumento() {
    }

	public Integer getTipodocumentoid() {
		return this.tipodocumentoid;
	}

	public void setTipodocumentoid(Integer tipodocumentoid) {
		this.tipodocumentoid = tipodocumentoid;
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

	public List<Institucion> getInstitucions() {
		return this.institucions;
	}

	public void setInstitucions(List<Institucion> institucions) {
		this.institucions = institucions;
	}
	
}