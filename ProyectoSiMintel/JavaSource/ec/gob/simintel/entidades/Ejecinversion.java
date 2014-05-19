package ec.gob.simintel.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ejecinversion database table.
 * 
 */
@Entity
public class Ejecinversion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer ejecinversionid;

	private Integer cantidad;

	private String comentario;

	private String docrespaldo;

	private double precio;

	private Integer tipodocumentoid;

	//bi-directional many-to-one association to Detalleejecinv
	@OneToMany(mappedBy="ejecinversion")
	private List<Detalleejecinv> detalleejecinvs;

	//bi-directional many-to-one association to Planinversion
    @ManyToOne
	@JoinColumn(name="planinversionid")
	private Planinversion planinversion;

	//bi-directional many-to-one association to Elemseriado
	@OneToMany(mappedBy="ejecinversion")
	private List<Elemseriado> elemseriados;

    public Ejecinversion() {
    }

	public Integer getEjecinversionid() {
		return this.ejecinversionid;
	}

	public void setEjecinversionid(Integer ejecinversionid) {
		this.ejecinversionid = ejecinversionid;
	}

	public Integer getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
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

	public double getPrecio() {
		return this.precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Integer getTipodocumentoid() {
		return this.tipodocumentoid;
	}

	public void setTipodocumentoid(Integer tipodocumentoid) {
		this.tipodocumentoid = tipodocumentoid;
	}

	public List<Detalleejecinv> getDetalleejecinvs() {
		return this.detalleejecinvs;
	}

	public void setDetalleejecinvs(List<Detalleejecinv> detalleejecinvs) {
		this.detalleejecinvs = detalleejecinvs;
	}
	
	public Planinversion getPlaninversion() {
		return this.planinversion;
	}

	public void setPlaninversion(Planinversion planinversion) {
		this.planinversion = planinversion;
	}
	
	public List<Elemseriado> getElemseriados() {
		return this.elemseriados;
	}

	public void setElemseriados(List<Elemseriado> elemseriados) {
		this.elemseriados = elemseriados;
	}
	
}