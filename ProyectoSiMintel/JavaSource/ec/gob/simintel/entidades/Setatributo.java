package ec.gob.simintel.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the setatributos database table.
 * 
 */
@Entity
@Table(name="setatributos")
public class Setatributo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer setatributosid;

	private String descripcion;

	private Integer orden;

	private String seriado;
	
	@Transient
	private  int indice;

	//bi-directional many-to-one association to Atributossetatrib
	@OneToMany(mappedBy="setatributo")
	private List<Atributossetatrib> atributossetatribs;

	//bi-directional many-to-one association to Grupodato
    @ManyToOne
	@JoinColumn(name="grupodatosid")
	private Grupodato grupodato;

    public Setatributo() {
    }

	public Integer getSetatributosid() {
		return this.setatributosid;
	}

	public void setSetatributosid(Integer setatributosid) {
		this.setatributosid = setatributosid;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getOrden() {
		return this.orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	public String getSeriado() {
		return this.seriado;
	}

	public void setSeriado(String seriado) {
		this.seriado = seriado;
	}

	public List<Atributossetatrib> getAtributossetatribs() {
		return this.atributossetatribs;
	}

	public void setAtributossetatribs(List<Atributossetatrib> atributossetatribs) {
		this.atributossetatribs = atributossetatribs;
	}
	
	public Grupodato getGrupodato() {
		return this.grupodato;
	}

	public void setGrupodato(Grupodato grupodato) {
		this.grupodato = grupodato;
	}

	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}
	
}