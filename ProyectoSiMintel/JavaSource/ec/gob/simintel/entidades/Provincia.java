package ec.gob.simintel.entidades;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the provincia database table.
 * 
 */
@Entity
public class Provincia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer provinciaid;

	private String dpa;

    @Temporal( TemporalType.DATE)
	private Date fechafin;

    @Temporal( TemporalType.DATE)
	private Date fechainicio;

    @NotNull(message="El nombre es un campo obigatorio")
	private String nombre;

	//bi-directional many-to-one association to Canton
	@OneToMany(mappedBy="provincia")
	private List<Canton> cantons;

	//bi-directional many-to-one association to Pai
    @ManyToOne
	@JoinColumn(name="paisid")
	private Pai pai;

	//bi-directional many-to-one association to Provincia
    @ManyToOne
	@JoinColumn(name="relacionid")
	private Provincia provincia;

	//bi-directional many-to-one association to Provincia
	@OneToMany(mappedBy="provincia")
	private List<Provincia> provincias;

	//bi-directional many-to-one association to Zonasemplade
    @ManyToOne
	@JoinColumn(name="zonasempladesid")
	private Zonasemplade zonasemplade;

    public Provincia() {
    }

	public Integer getProvinciaid() {
		return this.provinciaid;
	}

	public void setProvinciaid(Integer provinciaid) {
		this.provinciaid = provinciaid;
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

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Canton> getCantons() {
		return this.cantons;
	}

	public void setCantons(List<Canton> cantons) {
		this.cantons = cantons;
	}
	
	public Pai getPai() {
		return this.pai;
	}

	public void setPai(Pai pai) {
		this.pai = pai;
	}
	
	public Provincia getProvincia() {
		return this.provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	
	public List<Provincia> getProvincias() {
		return this.provincias;
	}

	public void setProvincias(List<Provincia> provincias) {
		this.provincias = provincias;
	}
	
	public Zonasemplade getZonasemplade() {
		return this.zonasemplade;
	}

	public void setZonasemplade(Zonasemplade zonasemplade) {
		this.zonasemplade = zonasemplade;
	}
	
}