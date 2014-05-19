package ec.gob.simintel.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the proyecto database table.
 * 
 */
@Entity
public class Proyecto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer proyectoid;

	private Integer ano;

	private String comentario;

	private String descripcion;

	private String docrespaldo;

	private double presupuestocal;

	private double presupuestoejec;

	private double presupuestoest;

	private Integer tipodocumentoid;

	//bi-directional many-to-one association to Convenio
	@OneToMany(mappedBy="proyecto",cascade ={CascadeType.PERSIST ,CascadeType.MERGE})
	private List<Convenio> convenios;

	//bi-directional many-to-one association to Patrocinio
	@OneToMany(mappedBy="proyecto")
	private List<Patrocinio> patrocinios;

	//bi-directional many-to-one association to Programa
    @ManyToOne
	@JoinColumn(name="programaid")
	private Programa programa;

	//bi-directional many-to-one association to Relacionproyecto
	@OneToMany(mappedBy="proyecto1")
	private List<Relacionproyecto> relacionproyectos1;

	//bi-directional many-to-one association to Relacionproyecto
	@OneToMany(mappedBy="proyecto2")
	private List<Relacionproyecto> relacionproyectos2;

    public Proyecto() {
    }

	public Integer getProyectoid() {
		return this.proyectoid;
	}

	public void setProyectoid(Integer proyectoid) {
		this.proyectoid = proyectoid;
	}

	public Integer getAno() {
		return this.ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public String getComentario() {
		return this.comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDocrespaldo() {
		return this.docrespaldo;
	}

	public void setDocrespaldo(String docrespaldo) {
		this.docrespaldo = docrespaldo;
	}

	public double getPresupuestocal() {
		return this.presupuestocal;
	}

	public void setPresupuestocal(double presupuestocal) {
		this.presupuestocal = presupuestocal;
	}

	public double getPresupuestoejec() {
		return this.presupuestoejec;
	}

	public void setPresupuestoejec(double presupuestoejec) {
		this.presupuestoejec = presupuestoejec;
	}

	public double getPresupuestoest() {
		return this.presupuestoest;
	}

	public void setPresupuestoest(double presupuestoest) {
		this.presupuestoest = presupuestoest;
	}

	public Integer getTipodocumentoid() {
		return this.tipodocumentoid;
	}

	public void setTipodocumentoid(Integer tipodocumentoid) {
		this.tipodocumentoid = tipodocumentoid;
	}

	public List<Convenio> getConvenios() {
		return this.convenios;
	}

	public void setConvenios(List<Convenio> convenios) {
		this.convenios = convenios;
	}
	
	public List<Patrocinio> getPatrocinios() {
		return this.patrocinios;
	}

	public void setPatrocinios(List<Patrocinio> patrocinios) {
		this.patrocinios = patrocinios;
	}
	
	public Programa getPrograma() {
		return this.programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}
	
	public List<Relacionproyecto> getRelacionproyectos1() {
		return this.relacionproyectos1;
	}

	public void setRelacionproyectos1(List<Relacionproyecto> relacionproyectos1) {
		this.relacionproyectos1 = relacionproyectos1;
	}
	
	public List<Relacionproyecto> getRelacionproyectos2() {
		return this.relacionproyectos2;
	}

	public void setRelacionproyectos2(List<Relacionproyecto> relacionproyectos2) {
		this.relacionproyectos2 = relacionproyectos2;
	}
	
}