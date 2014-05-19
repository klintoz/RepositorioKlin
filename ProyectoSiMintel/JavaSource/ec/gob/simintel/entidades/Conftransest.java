package ec.gob.simintel.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the conftransest database table.
 * 
 */
@Entity
public class Conftransest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer conftransestid;

	private String comentario;

	//bi-directional many-to-one association to Estado
    @ManyToOne
	@JoinColumn(name="estadoinicialid")
	private Estado estado1;

	//bi-directional many-to-one association to Estado
    @ManyToOne
	@JoinColumn(name="estadofinalid")
	private Estado estado2;

    public Conftransest() {
    }

	public Integer getConftransestid() {
		return this.conftransestid;
	}

	public void setConftransestid(Integer conftransestid) {
		this.conftransestid = conftransestid;
	}

	public String getComentario() {
		return this.comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Estado getEstado1() {
		return this.estado1;
	}

	public void setEstado1(Estado estado1) {
		this.estado1 = estado1;
	}
	
	public Estado getEstado2() {
		return this.estado2;
	}

	public void setEstado2(Estado estado2) {
		this.estado2 = estado2;
	}
	
}