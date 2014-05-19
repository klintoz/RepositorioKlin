package ec.gob.simintel.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the monitoreo database table.
 * 
 */
@Entity
public class Monitoreo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer monitoreoid;

	private String comentario;

    @Temporal( TemporalType.DATE)
	private Date fecha;

	private String tipo;

	//bi-directional many-to-one association to Detallemon
	@OneToMany(mappedBy="monitoreo")
	private List<Detallemon> detallemons;

	//bi-directional many-to-one association to Convenio
    @ManyToOne
	@JoinColumn(name="convenioid")
	private Convenio convenio;

	//bi-directional many-to-one association to Datoscontacto
    @ManyToOne
	@JoinColumn(name="datoscontactoid")
	private Datoscontacto datoscontacto;

	//bi-directional many-to-one association to Institucion
    @ManyToOne
	@JoinColumn(name="institucionid")
	private Institucion institucion;

    public Monitoreo() {
    }

	public Integer getMonitoreoid() {
		return this.monitoreoid;
	}

	public void setMonitoreoid(Integer monitoreoid) {
		this.monitoreoid = monitoreoid;
	}

	public String getComentario() {
		return this.comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Detallemon> getDetallemons() {
		return this.detallemons;
	}

	public void setDetallemons(List<Detallemon> detallemons) {
		this.detallemons = detallemons;
	}
	
	public Convenio getConvenio() {
		return this.convenio;
	}

	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
	}
	
	public Datoscontacto getDatoscontacto() {
		return this.datoscontacto;
	}

	public void setDatoscontacto(Datoscontacto datoscontacto) {
		this.datoscontacto = datoscontacto;
	}
	
	public Institucion getInstitucion() {
		return this.institucion;
	}

	public void setInstitucion(Institucion institucion) {
		this.institucion = institucion;
	}
	
}