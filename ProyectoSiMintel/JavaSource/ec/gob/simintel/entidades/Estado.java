package ec.gob.simintel.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the estado database table.
 * 
 */
@Entity
public class Estado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer estadoid;

	private String clase;

	@Column(name="final")
	private String final_;

	private String nombre;

	//bi-directional many-to-one association to Conftransest
	@OneToMany(mappedBy="estado1")
	private List<Conftransest> conftransests1;

	//bi-directional many-to-one association to Conftransest
	@OneToMany(mappedBy="estado2")
	private List<Conftransest> conftransests2;

	//bi-directional many-to-one association to Convenio
	@OneToMany(mappedBy="estado")
	private List<Convenio> convenios;

	//bi-directional many-to-one association to Instbeneficiada
	@OneToMany(mappedBy="estado")
	private List<Instbeneficiada> instbeneficiadas;

	//bi-directional many-to-one association to Institucion
	@OneToMany(mappedBy="estado")
	private List<Institucion> institucions;

	//bi-directional many-to-one association to Transestconvenio
	@OneToMany(mappedBy="estado")
	private List<Transestconvenio> transestconvenios;

    public Estado() {
    }

	public Integer getEstadoid() {
		return this.estadoid;
	}

	public void setEstadoid(Integer estadoid) {
		this.estadoid = estadoid;
	}

	public String getClase() {
		return this.clase;
	}

	public void setClase(String clase) {
		this.clase = clase;
	}

	public String getFinal_() {
		return this.final_;
	}

	public void setFinal_(String final_) {
		this.final_ = final_;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Conftransest> getConftransests1() {
		return this.conftransests1;
	}

	public void setConftransests1(List<Conftransest> conftransests1) {
		this.conftransests1 = conftransests1;
	}
	
	public List<Conftransest> getConftransests2() {
		return this.conftransests2;
	}

	public void setConftransests2(List<Conftransest> conftransests2) {
		this.conftransests2 = conftransests2;
	}
	
	public List<Convenio> getConvenios() {
		return this.convenios;
	}

	public void setConvenios(List<Convenio> convenios) {
		this.convenios = convenios;
	}
	
	public List<Instbeneficiada> getInstbeneficiadas() {
		return this.instbeneficiadas;
	}

	public void setInstbeneficiadas(List<Instbeneficiada> instbeneficiadas) {
		this.instbeneficiadas = instbeneficiadas;
	}
	
	public List<Institucion> getInstitucions() {
		return this.institucions;
	}

	public void setInstitucions(List<Institucion> institucions) {
		this.institucions = institucions;
	}
	
	public List<Transestconvenio> getTransestconvenios() {
		return this.transestconvenios;
	}

	public void setTransestconvenios(List<Transestconvenio> transestconvenios) {
		this.transestconvenios = transestconvenios;
	}
	
}