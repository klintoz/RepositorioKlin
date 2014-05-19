package ec.gob.simintel.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the atributo database table.
 * 
 */
@Entity
public class Atributo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer atributoid;

	private String nombre;

	private String tipodato;

	//bi-directional many-to-one association to Atributossetatrib
	@OneToMany(mappedBy="atributo")
	private List<Atributossetatrib> atributossetatribs;

	//bi-directional many-to-one association to Listavaloresatrib
	@OneToMany(mappedBy="atributo")
	private List<Listavaloresatrib> listavaloresatribs;

    public Atributo() {
    }

	public Integer getAtributoid() {
		return this.atributoid;
	}

	public void setAtributoid(Integer atributoid) {
		this.atributoid = atributoid;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipodato() {
		return this.tipodato;
	}

	public void setTipodato(String tipodato) {
		this.tipodato = tipodato;
	}

	public List<Atributossetatrib> getAtributossetatribs() {
		return this.atributossetatribs;
	}

	public void setAtributossetatribs(List<Atributossetatrib> atributossetatribs) {
		this.atributossetatribs = atributossetatribs;
	}
	
	public List<Listavaloresatrib> getListavaloresatribs() {
		return this.listavaloresatribs;
	}

	public void setListavaloresatribs(List<Listavaloresatrib> listavaloresatribs) {
		this.listavaloresatribs = listavaloresatribs;
	}
	
}