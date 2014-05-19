package ec.gob.simintel.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the area database table.
 * 
 */
@Entity
public class Area implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer areaid;

	private String descripcion;

	//bi-directional many-to-one association to Area
    @ManyToOne
	@JoinColumn(name="areaipadreid")
	private Area area;

	//bi-directional many-to-one association to Area
	@OneToMany(mappedBy="area")
	private List<Area> areas;

	//bi-directional many-to-one association to Personalarea
	@OneToMany(mappedBy="area")
	private List<Personalarea> personalareas;

    public Area() {
    }

	public Integer getAreaid() {
		return this.areaid;
	}

	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Area getArea() {
		return this.area;
	}

	public void setArea(Area area) {
		this.area = area;
	}
	
	public List<Area> getAreas() {
		return this.areas;
	}

	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}
	
	public List<Personalarea> getPersonalareas() {
		return this.personalareas;
	}

	public void setPersonalareas(List<Personalarea> personalareas) {
		this.personalareas = personalareas;
	}
	
}