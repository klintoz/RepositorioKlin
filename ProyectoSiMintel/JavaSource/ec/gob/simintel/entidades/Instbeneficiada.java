package ec.gob.simintel.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the instbeneficiada database table.
 * 
 */
@Entity
public class Instbeneficiada implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer instbeneficiadaid;

	private String comentario;

    @Temporal( TemporalType.DATE)
	private Date fechaatencion;

	//bi-directional many-to-one association to Estado
    @ManyToOne
	@JoinColumn(name="estadoid")
	private Estado estado;

	//bi-directional many-to-one association to Institucion
    @ManyToOne
	@JoinColumn(name="institucionid")
	private Institucion institucion;

	//bi-directional many-to-one association to Inversion
    @ManyToOne
	@JoinColumn(name="inversionid")
	private Inversion inversion;

    public Instbeneficiada() {
    }

	public Integer getInstbeneficiadaid() {
		return this.instbeneficiadaid;
	}

	public void setInstbeneficiadaid(Integer instbeneficiadaid) {
		this.instbeneficiadaid = instbeneficiadaid;
	}

	public String getComentario() {
		return this.comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Date getFechaatencion() {
		return this.fechaatencion;
	}

	public void setFechaatencion(Date fechaatencion) {
		this.fechaatencion = fechaatencion;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public Institucion getInstitucion() {
		return this.institucion;
	}

	public void setInstitucion(Institucion institucion) {
		this.institucion = institucion;
	}
	
	public Inversion getInversion() {
		return this.inversion;
	}

	public void setInversion(Inversion inversion) {
		this.inversion = inversion;
	}
	
}