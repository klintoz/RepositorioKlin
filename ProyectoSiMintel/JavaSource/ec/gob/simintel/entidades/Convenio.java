package ec.gob.simintel.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the convenio database table.
 * 
 */
@Entity
public class Convenio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer convenioid;

	private String comentario;

	private String descripcion;

	private String docrespaldo;

    @Temporal( TemporalType.DATE)
	private Date fechasuscripcion;

	private String objetivo;

	private double presupuestcalculado;

	private double presupuestoejecutado;

	private double presupuestoestablecido;

	private String tipo;

	private Integer tipodocumentoid;
	
	@Transient
	private int indice;

	//bi-directional many-to-one association to Estado
    @ManyToOne
	@JoinColumn(name="estadoid")
	private Estado estado;

	//bi-directional many-to-one association to Persona
    @ManyToOne
	@JoinColumn(name="id_persona")
	private Persona persona;

	//bi-directional many-to-one association to Proveedor
    @ManyToOne
	@JoinColumn(name="proveedorid")
	private Proveedor proveedor;

	//bi-directional many-to-one association to Proyecto
    @ManyToOne
	@JoinColumn(name="proyectoid")
	private Proyecto proyecto;

	//bi-directional many-to-one association to Inversion
	@OneToMany(mappedBy="convenio", cascade={CascadeType.PERSIST ,CascadeType.MERGE})
	private List<Inversion> inversions;

	//bi-directional many-to-one association to Monitoreo
	@OneToMany(mappedBy="convenio")
	private List<Monitoreo> monitoreos;

	//bi-directional many-to-one association to Transestconvenio
	@OneToMany(mappedBy="convenio")
	private List<Transestconvenio> transestconvenios;

    public Convenio() {
    }

	public Integer getConvenioid() {
		return this.convenioid;
	}

	public void setConvenioid(Integer convenioid) {
		this.convenioid = convenioid;
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

	public Date getFechasuscripcion() {
		return this.fechasuscripcion;
	}

	public void setFechasuscripcion(Date fechasuscripcion) {
		this.fechasuscripcion = fechasuscripcion;
	}

	public String getObjetivo() {
		return this.objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public double getPresupuestcalculado() {
		return this.presupuestcalculado;
	}

	public void setPresupuestcalculado(double presupuestcalculado) {
		this.presupuestcalculado = presupuestcalculado;
	}

	public double getPresupuestoejecutado() {
		return this.presupuestoejecutado;
	}

	public void setPresupuestoejecutado(double presupuestoejecutado) {
		this.presupuestoejecutado = presupuestoejecutado;
	}

	public double getPresupuestoestablecido() {
		return this.presupuestoestablecido;
	}

	public void setPresupuestoestablecido(double presupuestoestablecido) {
		this.presupuestoestablecido = presupuestoestablecido;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getTipodocumentoid() {
		return this.tipodocumentoid;
	}

	public void setTipodocumentoid(Integer tipodocumentoid) {
		this.tipodocumentoid = tipodocumentoid;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	public Proveedor getProveedor() {
		return this.proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	
	public Proyecto getProyecto() {
		return this.proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}
	
	public List<Inversion> getInversions() {
		return this.inversions;
	}

	public void setInversions(List<Inversion> inversions) {
		this.inversions = inversions;
	}
	
	public List<Monitoreo> getMonitoreos() {
		return this.monitoreos;
	}

	public void setMonitoreos(List<Monitoreo> monitoreos) {
		this.monitoreos = monitoreos;
	}
	
	public List<Transestconvenio> getTransestconvenios() {
		return this.transestconvenios;
	}

	public void setTransestconvenios(List<Transestconvenio> transestconvenios) {
		this.transestconvenios = transestconvenios;
	}

	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}
	
}