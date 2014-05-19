package ec.gob.simintel.entidades;

import java.io.Serializable;
import javax.persistence.*;

import com.sun.org.apache.bcel.internal.generic.CASTORE;

import java.util.List;


/**
 * The persistent class for the plan database table.
 * 
 */
@Entity
public class Plan implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer planid;

	private String descripcion;

	//bi-directional many-to-one association to Programa
	@OneToMany(mappedBy="plan", cascade= {CascadeType.PERSIST ,CascadeType.MERGE})
	private List<Programa> programas;

    public Plan() {
    }

	public Integer getPlanid() {
		return this.planid;
	}

	public void setPlanid(Integer planid) {
		this.planid = planid;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Programa> getProgramas() {
		return this.programas;
	}

	public void setProgramas(List<Programa> programas) {
		this.programas = programas;
	}
	
}