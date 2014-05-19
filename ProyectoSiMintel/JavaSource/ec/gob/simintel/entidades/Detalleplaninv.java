package ec.gob.simintel.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the detalleplaninv database table.
 * 
 */
@Entity
public class Detalleplaninv implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer detalleplaninvid;

	private String valoratrib;

	//bi-directional many-to-one association to Atributossetatrib
    @ManyToOne
	@JoinColumn(name="atributossetatribid")
	private Atributossetatrib atributossetatrib;

	//bi-directional many-to-one association to Planinversion
    @ManyToOne
	@JoinColumn(name="planinversionid")
	private Planinversion planinversion;

    public Detalleplaninv() {
    }

	public Integer getDetalleplaninvid() {
		return this.detalleplaninvid;
	}

	public void setDetalleplaninvid(Integer detalleplaninvid) {
		this.detalleplaninvid = detalleplaninvid;
	}

	public String getValoratrib() {
		return this.valoratrib;
	}

	public void setValoratrib(String valoratrib) {
		this.valoratrib = valoratrib;
	}

	public Atributossetatrib getAtributossetatrib() {
		return this.atributossetatrib;
	}

	public void setAtributossetatrib(Atributossetatrib atributossetatrib) {
		this.atributossetatrib = atributossetatrib;
	}
	
	public Planinversion getPlaninversion() {
		return this.planinversion;
	}

	public void setPlaninversion(Planinversion planinversion) {
		this.planinversion = planinversion;
	}
	
}