package ec.gob.simintel.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
public class Usuario implements Serializable, Cloneable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_usuario")
	private Integer id;

	private String contrasenia;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_caducidad_contrasenia")
	private Date fechaCaducidadContrasenia;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_modificacion")
	private Date fechaModificacion;

	@Column(name="perfil_creacion")
	private Integer perfilCreacion;

	@Column(name="perfil_modificacion")
	private Integer perfilModificacion;
	@Column(name="id_parqueadero")
	private Integer idParqueadero;

	private String usuario;

	//bi-directional many-to-one association to Perfil
	@OneToMany(mappedBy="usuario")
	private List<Perfil> perfils;

	//bi-directional many-to-one association to Persona
	@ManyToOne
	@JoinColumn(name="id_persona")
	private Persona persona;

	public Usuario() {
	}
	
	@Override
	public Object clone() {
		Usuario usuario = null;
		try {
			usuario = (Usuario) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return usuario;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContrasenia() {
		return this.contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public Date getFechaCaducidadContrasenia() {
		return this.fechaCaducidadContrasenia;
	}

	public void setFechaCaducidadContrasenia(Date fechaCaducidadContrasenia) {
		this.fechaCaducidadContrasenia = fechaCaducidadContrasenia;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaModificacion() {
		return this.fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public Integer getPerfilCreacion() {
		return this.perfilCreacion;
	}

	public void setPerfilCreacion(Integer perfilCreacion) {
		this.perfilCreacion = perfilCreacion;
	}

	public Integer getPerfilModificacion() {
		return this.perfilModificacion;
	}

	public void setPerfilModificacion(Integer perfilModificacion) {
		this.perfilModificacion = perfilModificacion;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public List<Perfil> getPerfils() {
		return this.perfils;
	}

	public void setPerfils(List<Perfil> perfils) {
		this.perfils = perfils;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Integer getIdParqueadero() {
		return idParqueadero;
	}

	public void setIdParqueadero(Integer idParqueadero) {
		this.idParqueadero = idParqueadero;
	}

	

}