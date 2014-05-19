package ec.gob.simintel.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the proveedor database table.
 * 
 */
@Entity
public class Proveedor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer proveedorid;

	private String activo;

	private String comentario;

	private String direccion;

	private String identificacion;

	private String nombre;

	private Integer tipodocumentoid;

	private String tipoempresa;

	//bi-directional many-to-one association to Contacto
	@OneToMany(mappedBy="proveedor")
	private List<Contacto> contactos;

	//bi-directional many-to-one association to Convenio
	@OneToMany(mappedBy="proveedor")
	private List<Convenio> convenios;

    public Proveedor() {
    }

	public Integer getProveedorid() {
		return this.proveedorid;
	}

	public void setProveedorid(Integer proveedorid) {
		this.proveedorid = proveedorid;
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

	public List<Contacto> getContactos() {
		return this.contactos;
	}

	public void setContactos(List<Contacto> contactos) {
		this.contactos = contactos;
	}
	
	public List<Convenio> getConvenios() {
		return this.convenios;
	}

	public void setConvenios(List<Convenio> convenios) {
		this.convenios = convenios;
	}
	
}