package ec.gob.simintel.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the patrocinador database table.
 * 
 */
@Entity
public class Patrocinador implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer patrocinadorid;

	private String activo;

	private String comentario;

	private String direccion;

	private String email;

	private String fax;

	private String identificacion;

	private String nombre;

	private String telefono;

	private Integer tipodocumentoid;

	private String tipoempresa;

	private String url;

	//bi-directional many-to-one association to Contacto
	@OneToMany(mappedBy="patrocinador")
	private List<Contacto> contactos;

	//bi-directional many-to-one association to Patrocinio
	@OneToMany(mappedBy="patrocinador")
	private List<Patrocinio> patrocinios;

    public Patrocinador() {
    }

	public Integer getPatrocinadorid() {
		return this.patrocinadorid;
	}

	public void setPatrocinadorid(Integer patrocinadorid) {
		this.patrocinadorid = patrocinadorid;
	}

	public String getActivo() {
		return this.activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	public String getComentario() {
		return this.comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
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

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Integer getTipodocumentoid() {
		return this.tipodocumentoid;
	}

	public void setTipodocumentoid(Integer tipodocumentoid) {
		this.tipodocumentoid = tipodocumentoid;
	}

	public String getTipoempresa() {
		return this.tipoempresa;
	}

	public void setTipoempresa(String tipoempresa) {
		this.tipoempresa = tipoempresa;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Contacto> getContactos() {
		return this.contactos;
	}

	public void setContactos(List<Contacto> contactos) {
		this.contactos = contactos;
	}
	
	public List<Patrocinio> getPatrocinios() {
		return this.patrocinios;
	}

	public void setPatrocinios(List<Patrocinio> patrocinios) {
		this.patrocinios = patrocinios;
	}
	
}