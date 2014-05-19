package ec.gob.simintel.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the elemsernovedad database table.
 * 
 */
@Entity
public class Elemsernovedad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer elemsernovedadid;

    @Temporal( TemporalType.DATE)
	private Date fechanovedad;

    @Temporal( TemporalType.DATE)
	private Date fechasolucion;

	private String novedad;

	private String solucion;

	//bi-directional many-to-one association to Elemseriado
    @ManyToOne
	@JoinColumn(name="elemseriadoid")
	private Elemseriado elemseriado;

    public Elemsernovedad() {
    }

	public Integer getElemsernovedadid() {
		return this.elemsernovedadid;
	}

	public void setElemsernovedadid(Integer elemsernovedadid) {
		this.elemsernovedadid = elemsernovedadid;
	}

	public Date getFechanovedad() {
		return this.fechanovedad;
	}

	public void setFechanovedad(Date fechanovedad) {
		this.fechanovedad = fechanovedad;
	}

	public Date getFechasolucion() {
		return this.fechasolucion;
	}

	public void setFechasolucion(Date fechasolucion) {
		this.fechasolucion = fechasolucion;
	}

	public String getNovedad() {
		return this.novedad;
	}

	public void setNovedad(String novedad) {
		this.novedad = novedad;
	}

	public String getSolucion() {
		return this.solucion;
	}

	public void setSolucion(String solucion) {
		this.solucion = solucion;
	}

	public Elemseriado getElemseriado() {
		return this.elemseriado;
	}

	public void setElemseriado(Elemseriado elemseriado) {
		this.elemseriado = elemseriado;
	}
	
}