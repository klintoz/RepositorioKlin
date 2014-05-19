package ec.gob.simintel.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the contactos database table.
 * 
 */
@Entity
@Table(name="contactos")
public class Contacto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer contactosid;

	private String activo;

	private String cargo;

	private String comentario;

	private String departamento;

	private String principal;

	//bi-directional many-to-one association to Institucion
    @ManyToOne
	@JoinColumn(name="institucionid")
	private Institucion institucion;

	//bi-directional many-to-one association to Patrocinador
    @ManyToOne
	@JoinColumn(name="patrocinadorid")
	private Patrocinador patrocinador;

	//bi-directional many-to-one association to Proveedor
    @ManyToOne
	@JoinColumn(name="proveedorid")
	private Proveedor proveedor;
    

	//bi-directional many-to-one association to Mediocontacto
	@OneToMany(mappedBy="contacto")
	private List<Mediocontacto> mediocontactos;
	
	//muchos contactos le pertenecen a un DatoContacto 
	@ManyToOne
	@JoinColumn(name="datoscontactoid")
	private Datoscontacto datoscontacto;
	
	@Transient
	private int indice;

    public Contacto() {
    }

	public Integer getContactosid() {
		return this.contactosid;
	}

	public void setContactosid(Integer contactosid) {
		this.contactosid = contactosid;
	}

	public String getActivo() {
		return this.activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	public String getCargo() {
		return this.cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getComentario() {
		return this.comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getDepartamento() {
		return this.departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getPrincipal() {
		return this.principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public Institucion getInstitucion() {
		return this.institucion;
	}

	public void setInstitucion(Institucion institucion) {
		this.institucion = institucion;
	}
	
	public Patrocinador getPatrocinador() {
		return this.patrocinador;
	}

	public void setPatrocinador(Patrocinador patrocinador) {
		this.patrocinador = patrocinador;
	}
	
	public Proveedor getProveedor() {
		return this.proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	
	public List<Mediocontacto> getMediocontactos() {
		return this.mediocontactos;
	}

	public void setMediocontactos(List<Mediocontacto> mediocontactos) {
		this.mediocontactos = mediocontactos;
	}

	public Datoscontacto getDatoscontacto() {
		return datoscontacto;
	}

	public void setDatoscontacto(Datoscontacto datoscontacto) {
		this.datoscontacto = datoscontacto;
	}

	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}
	
}