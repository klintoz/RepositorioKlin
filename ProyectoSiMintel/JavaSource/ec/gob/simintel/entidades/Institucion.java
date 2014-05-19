package ec.gob.simintel.entidades;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.List;


/**
 * The persistent class for the institucion database table.
 * 
 */
@Entity
public class Institucion implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer institucionid;

	private String comentario;

	private String comunidad;

	private String direccion;

	@NotNull(message="IDENTIFICACION es un campo obligatorio")
	private String identificacion;

	private String latitud;

	private String latitudhex;

	private String longitud;

	private String longitudhex;

	private String meridiano;

	private String migracionextid;

	@NotNull(message="NOMBRE es un campo obligatorio")
	private String nombre;

	private String zona;
	
	@Transient
	private String provincia;
	
	@Transient
	private String canton;

	//bi-directional many-to-one association to Beneficiario
	@OneToMany(mappedBy="institucion")
	private List<Beneficiario> beneficiarios;

	//bi-directional many-to-one association to Contacto
	@OneToMany(mappedBy="institucion", cascade={CascadeType.PERSIST ,CascadeType.MERGE})
	private List<Contacto> contactos;

	//bi-directional many-to-one association to Detalleinstitucion
	@OneToMany(mappedBy="institucion")
	private List<Detalleinstitucion> detalleinstitucions;

	//bi-directional many-to-one association to Instbeneficiada
	@OneToMany(mappedBy="institucion")
	private List<Instbeneficiada> instbeneficiadas;

	//bi-directional many-to-one association to Circuito
    @ManyToOne
	@JoinColumn(name="circuitoid")
	private Circuito circuito;

	//bi-directional many-to-one association to Distrito
    @ManyToOne
	@JoinColumn(name="distritoid")
	private Distrito distrito;

	//bi-directional many-to-one association to Estado
    @ManyToOne
	@JoinColumn(name="estadoid")
	private Estado estado;

	//bi-directional many-to-one association to Parroquia
    @ManyToOne
	@JoinColumn(name="parroquiaid")
	private Parroquia parroquia;

	//bi-directional many-to-one association to Tipodocumento
    @ManyToOne
	@JoinColumn(name="tipodocumentoid")
	private Tipodocumento tipodocumento;

	//bi-directional many-to-one association to Tipoinstitucion
    @ManyToOne
	@JoinColumn(name="tipoinstitucionid")
	private Tipoinstitucion tipoinstitucion;

	//bi-directional many-to-one association to Tiposostenimiento
    @ManyToOne
	@JoinColumn(name="tiposostenimientoid")
	private Tiposostenimiento tiposostenimiento;

	//bi-directional many-to-one association to Monitoreo
	@OneToMany(mappedBy="institucion")
	private List<Monitoreo> monitoreos;

    public Institucion() {
    }

	public Integer getInstitucionid() {
		return this.institucionid;
	}

	public void setInstitucionid(Integer institucionid) {
		this.institucionid = institucionid;
	}

	public String getComentario() {
		return this.comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getComunidad() {
		return this.comunidad;
	}

	public void setComunidad(String comunidad) {
		this.comunidad = comunidad;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getIdentificacion() {
		return this.identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getLatitud() {
		return this.latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getLatitudhex() {
		return this.latitudhex;
	}

	public void setLatitudhex(String latitudhex) {
		this.latitudhex = latitudhex;
	}

	public String getLongitud() {
		return this.longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public String getLongitudhex() {
		return this.longitudhex;
	}

	public void setLongitudhex(String longitudhex) {
		this.longitudhex = longitudhex;
	}

	public String getMeridiano() {
		return this.meridiano;
	}

	public void setMeridiano(String meridiano) {
		this.meridiano = meridiano;
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
	
	public List<Contacto> getContactos() {
		return this.contactos;
	}

	public void setContactos(List<Contacto> contactos) {
		this.contactos = contactos;
	}
	
	public List<Detalleinstitucion> getDetalleinstitucions() {
		return this.detalleinstitucions;
	}

	public void setDetalleinstitucions(List<Detalleinstitucion> detalleinstitucions) {
		this.detalleinstitucions = detalleinstitucions;
	}
	
	public List<Instbeneficiada> getInstbeneficiadas() {
		return this.instbeneficiadas;
	}

	public void setInstbeneficiadas(List<Instbeneficiada> instbeneficiadas) {
		this.instbeneficiadas = instbeneficiadas;
	}
	
	public Circuito getCircuito() {
		return this.circuito;
	}

	public void setCircuito(Circuito circuito) {
		this.circuito = circuito;
	}
	
	public Distrito getDistrito() {
		return this.distrito;
	}

	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
	}
	
	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public Parroquia getParroquia() {
		return this.parroquia;
	}

	public void setParroquia(Parroquia parroquia) {
		this.parroquia = parroquia;
	}
	
	public Tipodocumento getTipodocumento() {
		return this.tipodocumento;
	}

	public void setTipodocumento(Tipodocumento tipodocumento) {
		this.tipodocumento = tipodocumento;
	}
	
	public Tipoinstitucion getTipoinstitucion() {
		return this.tipoinstitucion;
	}

	public void setTipoinstitucion(Tipoinstitucion tipoinstitucion) {
		this.tipoinstitucion = tipoinstitucion;
	}
	
	public Tiposostenimiento getTiposostenimiento() {
		return this.tiposostenimiento;
	}

	public void setTiposostenimiento(Tiposostenimiento tiposostenimiento) {
		this.tiposostenimiento = tiposostenimiento;
	}
	
	public List<Monitoreo> getMonitoreos() {
		return this.monitoreos;
	}

	public void setMonitoreos(List<Monitoreo> monitoreos) {
		this.monitoreos = monitoreos;
	}
	
	/**Compara si dos Instituciones son iguales 
	 * true = Si las identificaciones son las mismos
	 * false = Si las identificaciones son diferentes
	 */
	@Override
	 public boolean equals(Object obj) {
	  if (obj == null)
	   return false;
	  if (obj == this)
	   return true;
	  if (!(obj instanceof Institucion))
	   return false;
	  Institucion p = (Institucion) obj;
	  if ((identificacion == null) ? (p.identificacion != null) : !identificacion.equals(p.identificacion))			  
		  return false;//Entra al if si son diferentes 
	  return true;  
	 }
	
	@Override
	public int hashCode() {
		return 0;
		
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getCanton() {
		return canton;
	}

	public void setCanton(String canton) {
		this.canton = canton;
	}
}