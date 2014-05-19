package ec.gob.simintel.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the personalarea database table.
 * 
 */
@Entity
public class Personalarea implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer personalareaid;

	private String activo;

    @Temporal( TemporalType.DATE)
	private Date fechafin;

    @Temporal( TemporalType.DATE)
	private Date fechainicio;

	//bi-directional many-to-one association to Area
    @ManyToOne
	@JoinColumn(name="areaid")
	private Area area;

	//bi-directional many-to-one association to Persona
    @ManyToOne
	@JoinColumn(name="id_persona")
	private Persona persona;

    public Personalarea() {
    }

	public Integer getPersonalareaid() {
		return this.personalareaid;
	}

	public void setPersonalareaid(Integer personalareaid) {
		this.personalareaid = personalareaid;
	}

	public String getActivo() {
		return this.activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
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

	public Area getArea() {
		return this.area;
	}

	public void setArea(Area area) {
		this.area = area;
	}
	
	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
}