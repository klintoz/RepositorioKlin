package ec.gob.simintel.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the detalleauditoria database table.
 * 
 */
@Entity
public class Detalleauditoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer detalleauditoriaid;

	private String accion;

	private String data;

    @Temporal( TemporalType.DATE)
	private Date fecha;

	//bi-directional many-to-one association to Auditoriatabla
    @ManyToOne
	@JoinColumn(name="auditoriaid")
	private Auditoriatabla auditoriatabla;

	//bi-directional many-to-one association to Persona
    @ManyToOne
	@JoinColumn(name="id_persona")
	private Persona persona;

    public Detalleauditoria() {
    }

	public Integer getDetalleauditoriaid() {
		return this.detalleauditoriaid;
	}

	public void setDetalleauditoriaid(Integer detalleauditoriaid) {
		this.detalleauditoriaid = detalleauditoriaid;
	}

	public String getAccion() {
		return this.accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public String getData() {
		return this.data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Auditoriatabla getAuditoriatabla() {
		return this.auditoriatabla;
	}

	public void setAuditoriatabla(Auditoriatabla auditoriatabla) {
		this.auditoriatabla = auditoriatabla;
	}
	
	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
}