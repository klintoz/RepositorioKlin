package ec.gob.simintel.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the parroquia database table.
 * 
 */
@Entity
public class Parroquia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer parroquiaid;

	private String dpa;

    @Temporal( TemporalType.DATE)
	private Date fechafin;

    @Temporal( TemporalType.DATE)
	private Date fechainicio;

	private String migracionext;

	private String nombre;

	private String zona;

	//bi-directional many-to-one association to Beneficiario
	@OneToMany(mappedBy="parroquia")
	private List<Beneficiario> beneficiarios;

	//bi-directional many-to-one association to Institucion
	@OneToMany(mappedBy="parroquia")
	private List<Institucion> institucions;

	//bi-directional many-to-one association to Canton
    @ManyToOne
	@JoinColumn(name="cantonid")
	private Canton canton;

	//bi-directional many-to-one association to Parroquia
    @ManyToOne
	@JoinColumn(name="relacionid")
	private Parroquia parroquia;

	//bi-directional many-to-one association to Parroquia
	@OneToMany(mappedBy="parroquia")
	private List<Parroquia> parroquias;

    public Parroquia() {
    }

	public Integer getParroquiaid() {
		return this.parroquiaid;
	}

	public void setParroquiaid(Integer parroquiaid) {
		this.parroquiaid = parroquiaid;
	}

	public String getDpa() {
		return this.dpa;
	}

	public void setDpa(String dpa) {
		this.dpa = dpa;
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

	public String getMigracionext() {
		return this.migracionext;
	}

	public void setMigracionext(String migracionext) {
		this.migracionext = migracionext;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getZona() {
		return this.zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public List<Beneficiario> getBeneficiarios() {
		return this.beneficiarios;
	}

	public void setBeneficiarios(List<Beneficiario> beneficiarios) {
		this.beneficiarios = beneficiarios;
	}
	
	public List<Institucion> getInstitucions() {
		return this.institucions;
	}

	public void setInstitucions(List<Institucion> institucions) {
		this.institucions = institucions;
	}
	
	public Canton getCanton() {
		return this.canton;
	}

	public void setCanton(Canton canton) {
		this.canton = canton;
	}
	
	public Parroquia getParroquia() {
		return this.parroquia;
	}

	public void setParroquia(Parroquia parroquia) {
		this.parroquia = parroquia;
	}
	
	public List<Parroquia> getParroquias() {
		return this.parroquias;
	}

	public void setParroquias(List<Parroquia> parroquias) {
		this.parroquias = parroquias;
	}
	
}