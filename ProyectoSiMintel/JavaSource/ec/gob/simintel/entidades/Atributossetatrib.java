package ec.gob.simintel.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the atributossetatrib database table.
 * 
 */
@Entity
public class Atributossetatrib implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer atributossetatribid;

	private String obligatorio;

	private Integer orden;

	private String textodespliegue;

	//bi-directional many-to-one association to Atributo
    @ManyToOne
	@JoinColumn(name="atributoid")
	private Atributo atributo;

	//bi-directional many-to-one association to Setatributo
    @ManyToOne
	@JoinColumn(name="setatributosid")
	private Setatributo setatributo;

	//bi-directional many-to-one association to Detalleejecinv
	@OneToMany(mappedBy="atributossetatrib")
	private List<Detalleejecinv> detalleejecinvs;

	//bi-directional many-to-one association to Detalleinstitucion
	@OneToMany(mappedBy="atributossetatrib")
	private List<Detalleinstitucion> detalleinstitucions;

	//bi-directional many-to-one association to Detalleplaninv
	@OneToMany(mappedBy="atributossetatrib")
	private List<Detalleplaninv> detalleplaninvs;

    public Atributossetatrib() {
    }

	public Integer getAtributossetatribid() {
		return this.atributossetatribid;
	}

	public void setAtributossetatribid(Integer atributossetatribid) {
		this.atributossetatribid = atributossetatribid;
	}

	public String getObligatorio() {
		return this.obligatorio;
	}

	public void setObligatorio(String obligatorio) {
		this.obligatorio = obligatorio;
	}

	public Integer getOrden() {
		return this.orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	public String getTextodespliegue() {
		return this.textodespliegue;
	}

	public void setTextodespliegue(String textodespliegue) {
		this.textodespliegue = textodespliegue;
	}

	public Atributo getAtributo() {
		return this.atributo;
	}

	public void setAtributo(Atributo atributo) {
		this.atributo = atributo;
	}
	
	public Setatributo getSetatributo() {
		return this.setatributo;
	}

	public void setSetatributo(Setatributo setatributo) {
		this.setatributo = setatributo;
	}
	
	public List<Detalleejecinv> getDetalleejecinvs() {
		return this.detalleejecinvs;
	}

	public void setDetalleejecinvs(List<Detalleejecinv> detalleejecinvs) {
		this.detalleejecinvs = detalleejecinvs;
	}
	
	public List<Detalleinstitucion> getDetalleinstitucions() {
		return this.detalleinstitucions;
	}

	public void setDetalleinstitucions(List<Detalleinstitucion> detalleinstitucions) {
		this.detalleinstitucions = detalleinstitucions;
	}
	
	public List<Detalleplaninv> getDetalleplaninvs() {
		return this.detalleplaninvs;
	}

	public void setDetalleplaninvs(List<Detalleplaninv> detalleplaninvs) {
		this.detalleplaninvs = detalleplaninvs;
	}
	
}