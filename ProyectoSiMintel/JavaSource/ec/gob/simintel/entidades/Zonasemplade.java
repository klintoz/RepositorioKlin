package ec.gob.simintel.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the zonasemplades database table.
 * 
 */
@Entity
@Table(name="zonasemplades")
public class Zonasemplade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer zonasempladesid;

	private String descripcion;

	//bi-directional many-to-one association to Canton
	@OneToMany(mappedBy="zonasemplade")
	private List<Canton> cantons;

	//bi-directional many-to-one association to Provincia
	@OneToMany(mappedBy="zonasemplade")
	private List<Provincia> provincias;

    public Zonasemplade() {
    }

	public Integer getZonasempladesid() {
		return this.zonasempladesid;
	}

	public void setZonasempladesid(Integer zonasempladesid) {
		this.zonasempladesid = zonasempladesid;
	}

	

	public List<Canton> getCantons() {
		return this.cantons;
	}

	public void setCantons(List<Canton> cantons) {
		this.cantons = cantons;
	}
	
	public List<Provincia> getProvincias() {
		return this.provincias;
	}

	public void setProvincias(List<Provincia> provincias) {
		this.provincias = provincias;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}