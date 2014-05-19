package ec.gob.simintel.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the pais database table.
 * 
 */
@Entity
@Table(name="pais")
public class Pai implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer paisid;

	private Integer codigopais;

	private String nombre;

	//bi-directional many-to-one association to Provincia
	@OneToMany(mappedBy="pai")
	private List<Provincia> provincias;

    public Pai() {
    }

	public Integer getPaisid() {
		return this.paisid;
	}

	public void setPaisid(Integer paisid) {
		this.paisid = paisid;
	}

	public Integer getCodigopais() {
		return this.codigopais;
	}

	public void setCodigopais(Integer codigopais) {
		this.codigopais = codigopais;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Provincia> getProvincias() {
		return this.provincias;
	}

	public void setProvincias(List<Provincia> provincias) {
		this.provincias = provincias;
	}
	
}