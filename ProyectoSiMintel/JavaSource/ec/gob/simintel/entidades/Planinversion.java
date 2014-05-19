package ec.gob.simintel.entidades;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the planinversion database table.
 * 
 */
@Entity
public class Planinversion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer planinversionid;

	private Integer cantidadperiodos;

	private Integer componenteinvid;

	private String descriptivo;

	private String detallecantidad;

	private String periocidadpago;

	private double precio;
	
	@Transient
	private  int indice;
	
	private int setatributosid;
	
	//Creo para poder obtener el objeto equipo completo y poder pintarlo en la tabla
	@Transient
	private Setatributo Setatributo;

	//bi-directional many-to-one association to Detalleplaninv
	@OneToMany(mappedBy="planinversion")
	private List<Detalleplaninv> detalleplaninvs;

	//bi-directional many-to-one association to Ejecinversion
	@OneToMany(mappedBy="planinversion")
	private List<Ejecinversion> ejecinversions;

	//bi-directional many-to-one association to Inversion
    @ManyToOne
	@JoinColumn(name="inversionid")
	private Inversion inversion;

    public Planinversion() {
    }

	public Integer getPlaninversionid() {
		return this.planinversionid;
	}

	public void setPlaninversionid(Integer planinversionid) {
		this.planinversionid = planinversionid;
	}

	public Integer getCantidadperiodos() {
		return this.cantidadperiodos;
	}

	public void setCantidadperiodos(Integer cantidadperiodos) {
		this.cantidadperiodos = cantidadperiodos;
	}

	public Integer getComponenteinvid() {
		return this.componenteinvid;
	}

	public void setComponenteinvid(Integer componenteinvid) {
		this.componenteinvid = componenteinvid;
	}

	public String getDescriptivo() {
		return this.descriptivo;
	}

	public void setDescriptivo(String descriptivo) {
		this.descriptivo = descriptivo;
	}

	public String getDetallecantidad() {
		return this.detallecantidad;
	}

	public void setDetallecantidad(String detallecantidad) {
		this.detallecantidad = detallecantidad;
	}

	public String getPeriocidadpago() {
		return this.periocidadpago;
	}

	public void setPeriocidadpago(String periocidadpago) {
		this.periocidadpago = periocidadpago;
	}

	public double getPrecio() {
		return this.precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public List<Detalleplaninv> getDetalleplaninvs() {
		return this.detalleplaninvs;
	}

	public void setDetalleplaninvs(List<Detalleplaninv> detalleplaninvs) {
		this.detalleplaninvs = detalleplaninvs;
	}
	
	public List<Ejecinversion> getEjecinversions() {
		return this.ejecinversions;
	}

	public void setEjecinversions(List<Ejecinversion> ejecinversions) {
		this.ejecinversions = ejecinversions;
	}
	
	public Inversion getInversion() {
		return this.inversion;
	}

	public void setInversion(Inversion inversion) {
		this.inversion = inversion;
	}

	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}

	public int getSetatributosid() {
		return setatributosid;
	}

	public void setSetatributosid(int setatributosid) {
		this.setatributosid = setatributosid;
	}

	public Setatributo getSetatributo() {
		return Setatributo;
	}

	public void setSetatributo(Setatributo setatributo) {
		Setatributo = setatributo;
	}
	
}