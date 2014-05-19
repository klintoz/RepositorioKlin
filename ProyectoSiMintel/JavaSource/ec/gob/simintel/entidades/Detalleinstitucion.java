package ec.gob.simintel.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the detalleinstitucion database table.
 * 
 */
@Entity
public class Detalleinstitucion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer detalleinstitucionid;

	private String valoratrib;

	//bi-directional many-to-one association to Atributossetatrib
    @ManyToOne
	@JoinColumn(name="atributossetatribid")
	private Atributossetatrib atributossetatrib;

	//bi-directional many-to-one association to Institucion
    @ManyToOne
	@JoinColumn(name="institucionid")
	private Institucion institucion;

    public Detalleinstitucion() {
    }

	public Integer getDetalleinstitucionid() {
		return this.detalleinstitucionid;
	}

	public void setDetalleinstitucionid(Integer detalleinstitucionid) {
		this.detalleinstitucionid = detalleinstitucionid;
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
	
	public Institucion getInstitucion() {
		return this.institucion;
	}

	public void setInstitucion(Institucion institucion) {
		this.institucion = institucion;
	}
	
}