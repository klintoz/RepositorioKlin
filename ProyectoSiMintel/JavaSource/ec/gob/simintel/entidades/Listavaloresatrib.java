package ec.gob.simintel.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the listavaloresatrib database table.
 * 
 */
@Entity
public class Listavaloresatrib implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer listavaloresatrib;

	private String item;

	//bi-directional many-to-one association to Atributo
    @ManyToOne
	@JoinColumn(name="atributoid")
	private Atributo atributo;

    public Listavaloresatrib() {
    }

	public Integer getListavaloresatrib() {
		return this.listavaloresatrib;
	}

	public void setListavaloresatrib(Integer listavaloresatrib) {
		this.listavaloresatrib = listavaloresatrib;
	}

	public String getItem() {
		return this.item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public Atributo getAtributo() {
		return this.atributo;
	}

	public void setAtributo(Atributo atributo) {
		this.atributo = atributo;
	}
	
}