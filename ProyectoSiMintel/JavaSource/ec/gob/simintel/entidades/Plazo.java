package ec.gob.simintel.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the plazo database table.
 * 
 */
@Entity
public class Plazo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer plazoid;

	private String activo;

	private String comentario;

	private String docrespaldo;

	private Integer duracion;

    @Temporal( TemporalType.DATE)
	private Date fechainstalacionplan;

    @Temporal( TemporalType.DATE)
	private Date fechainstalacionreal;

	private String tipo;

	private Integer tipodocumentoid;

	//bi-directional many-to-one association to Inversion
    @ManyToOne
	@JoinColumn(name="inversionid")
	private Inversion inversion;

	//bi-directional many-to-one association to Plazo
    @ManyToOne
	@JoinColumn(name="relacionid")
	private Plazo plazo;

	//bi-directional many-to-one association to Plazo
	@OneToMany(mappedBy="plazo")
	private List<Plazo> plazos;

    public Plazo() {
    }

	public Integer getPlazoid() {
		return this.plazoid;
	}

	public void setPlazoid(Integer plazoid) {
		this.plazoid = plazoid;
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

	public String getDocrespaldo() {
		return this.docrespaldo;
	}

	public void setDocrespaldo(String docrespaldo) {
		this.docrespaldo = docrespaldo;
	}

	public Integer getDuracion() {
		return this.duracion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

	public Date getFechainstalacionplan() {
		return this.fechainstalacionplan;
	}

	public void setFechainstalacionplan(Date fechainstalacionplan) {
		this.fechainstalacionplan = fechainstalacionplan;
	}

	public Date getFechainstalacionreal() {
		return this.fechainstalacionreal;
	}

	public void setFechainstalacionreal(Date fechainstalacionreal) {
		this.fechainstalacionreal = fechainstalacionreal;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getTipodocumentoid() {
		return this.tipodocumentoid;
	}

	public void setTipodocumentoid(Integer tipodocumentoid) {
		this.tipodocumentoid = tipodocumentoid;
	}

	public Inversion getInversion() {
		return this.inversion;
	}

	public void setInversion(Inversion inversion) {
		this.inversion = inversion;
	}
	
	public Plazo getPlazo() {
		return this.plazo;
	}

	public void setPlazo(Plazo plazo) {
		this.plazo = plazo;
	}
	
	public List<Plazo> getPlazos() {
		return this.plazos;
	}

	public void setPlazos(List<Plazo> plazos) {
		this.plazos = plazos;
	}
	
}