package ec.gob.simintel.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the transestconvenio database table.
 * 
 */
@Entity
public class Transestconvenio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer transestconvenioid;

	private String comentario;

	private String docrespaldo;

    @Temporal( TemporalType.DATE)
	private Date fechafin;

    @Temporal( TemporalType.DATE)
	private Date fechainicio;

	private Integer tipodocumentoid;

	//bi-directional many-to-one association to Convenio
    @ManyToOne
	@JoinColumn(name="convenioid")
	private Convenio convenio;

	//bi-directional many-to-one association to Estado
    @ManyToOne
	@JoinColumn(name="estadoid")
	private Estado estado;

    public Transestconvenio() {
    }

	public Integer getTransestconvenioid() {
		return this.transestconvenioid;
	}

	public void setTransestconvenioid(Integer transestconvenioid) {
		this.transestconvenioid = transestconvenioid;
	}

	public String getComentario() {
		return this.comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getDocrespaldo() {
		return this.docrespaldo;
	}

	public void setDocrespaldo(String docrespaldo) {
		this.docrespaldo = docrespaldo;
	}

	public Date getFechafin() {
		return this.fechafin;
	}

	public void setFechafin(Date fechafin) {
		this.fechafin = fechafin;
	}

	public Date getFechainicio() {
		return this.fechainicio;
	}

	public void setFechainicio(Date fechainicio) {
		this.fechainicio = fechainicio;
	}

	public Integer getTipodocumentoid() {
		return this.tipodocumentoid;
	}

	public void setTipodocumentoid(Integer tipodocumentoid) {
		this.tipodocumentoid = tipodocumentoid;
	}

	public Convenio getConvenio() {
		return this.convenio;
	}

	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
	}
	
	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
}