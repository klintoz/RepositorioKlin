package ec.gob.simintel.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the datoscontacto database table.
 * 
 */
@Entity
public class Datoscontacto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer datoscontactoid;

	private String apellido;

	private String identificacion;

	private String nombre;

	private String saludo;

	private Integer tipodocumentoid;

	//bi-directional many-to-one association to Mediocontacto
	@OneToMany(mappedBy="datoscontacto", cascade={CascadeType.PERSIST ,CascadeType.MERGE})
	private List<Mediocontacto> mediocontactos;

	//bi-directional many-to-one association to Monitoreo
	@OneToMany(mappedBy="datoscontacto")
	private List<Monitoreo> monitoreos;

	//un datocontato tiene muchos contactos
	@OneToMany(mappedBy="datoscontacto",cascade={CascadeType.PERSIST ,CascadeType.MERGE})
	private List<Contacto> contactos;
	
    public Datoscontacto() {
    }

	public Integer getDatoscontactoid() {
		return this.datoscontactoid;
	}

	public void setDatoscontactoid(Integer datoscontactoid) {
		this.datoscontactoid = datoscontactoid;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getIdentificacion() {
		return this.identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSaludo() {
		return this.saludo;
	}

	public void setSaludo(String saludo) {
		this.saludo = saludo;
	}

	public Integer getTipodocumentoid() {
		return this.tipodocumentoid;
	}

	public void setTipodocumentoid(Integer tipodocumentoid) {
		this.tipodocumentoid = tipodocumentoid;
	}

	public List<Mediocontacto> getMediocontactos() {
		return this.mediocontactos;
	}

	public void setMediocontactos(List<Mediocontacto> mediocontactos) {
		this.mediocontactos = mediocontactos;
	}
	
	public List<Monitoreo> getMonitoreos() {
		return this.monitoreos;
	}

	public void setMonitoreos(List<Monitoreo> monitoreos) {
		this.monitoreos = monitoreos;
	}

	public List<Contacto> getContactos() {
		return contactos;
	}

	public void setContactos(List<Contacto> contactos) {
		this.contactos = contactos;
	}
	
}