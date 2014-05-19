package ec.gob.simintel.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the canton database table.
 * 
 */
@Entity
public class Canton implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer cantonid;

	private String dpa;

    @Temporal( TemporalType.DATE)
	private Date fechafin;

    @Temporal( TemporalType.DATE)
	private Date fechainicio;

	private String migracionextid;

	private String nombre;

	//bi-directional many-to-one association to Canton
    @ManyToOne
	@JoinColumn(name="relacionid")
	private Canton canton;

	//bi-directional many-to-one association to Canton
	@OneToMany(mappedBy="canton")
	private List<Canton> cantons;

	//bi-directional many-to-one association to Provincia
    @ManyToOne
	@JoinColumn(name="provinciaid")
	private Provincia provincia;

	//bi-directional many-to-one association to Region
    @ManyToOne
	@JoinColumn(name="regionid")
	private Region region;

	//bi-directional many-to-one association to Zonasemplade
    @ManyToOne
	@JoinColumn(name="zonasempladesid")
	private Zonasemplade zonasemplade;

	//bi-directional many-to-one association to Parroquia
	@OneToMany(mappedBy="canton")
	private List<Parroquia> parroquias;

    public Canton() {
    }

	public Integer getCantonid() {
		return this.cantonid;
	}

	public void setCantonid(Integer cantonid) {
		this.cantonid = cantonid;
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

	public String getMigracionextid() {
		return this.migracionextid;
	}

	public void setMigracionextid(String migracionextid) {
		this.migracionextid = migracionextid;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Canton getCanton() {
		return this.canton;
	}

	public void setCanton(Canton canton) {
		this.canton = canton;
	}
	
	public List<Canton> getCantons() {
		return this.cantons;
	}

	public void setCantons(List<Canton> cantons) {
		this.cantons = cantons;
	}
	
	public Provincia getProvincia() {
		return this.provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	
	public Region getRegion() {
		return this.region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}
	
	public Zonasemplade getZonasemplade() {
		return this.zonasemplade;
	}

	public void setZonasemplade(Zonasemplade zonasemplade) {
		this.zonasemplade = zonasemplade;
	}
	
	public List<Parroquia> getParroquias() {
		return this.parroquias;
	}

	public void setParroquias(List<Parroquia> parroquias) {
		this.parroquias = parroquias;
	}
	
}