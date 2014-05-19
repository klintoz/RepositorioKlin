package ec.gob.simintel.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the relacionproyectos database table.
 * 
 */
@Entity
@Table(name="relacionproyectos")
public class Relacionproyecto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer relacionid;

	//bi-directional many-to-one association to Proyecto
    @ManyToOne
	@JoinColumn(name="pryrelacionadoid")
	private Proyecto proyecto1;

	//bi-directional many-to-one association to Proyecto
    @ManyToOne
	@JoinColumn(name="proyectoid")
	private Proyecto proyecto2;

    public Relacionproyecto() {
    }

	public Integer getRelacionid() {
		return this.relacionid;
	}

	public void setRelacionid(Integer relacionid) {
		this.relacionid = relacionid;
	}

	public Proyecto getProyecto1() {
		return this.proyecto1;
	}

	public void setProyecto1(Proyecto proyecto1) {
		this.proyecto1 = proyecto1;
	}
	
	public Proyecto getProyecto2() {
		return this.proyecto2;
	}

	public void setProyecto2(Proyecto proyecto2) {
		this.proyecto2 = proyecto2;
	}
	
}