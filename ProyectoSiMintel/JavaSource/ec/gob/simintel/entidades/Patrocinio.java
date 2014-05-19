package ec.gob.simintel.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the patrocinio database table.
 * 
 */
@Entity
public class Patrocinio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer patrocinioid;

	private String comentario;

	private String docrespaldo;

	private Integer porcentaje;

	private Integer tipodocumentoid;

	//bi-directional many-to-one association to Patrocinador
    @ManyToOne
	@JoinColumn(name="patrocinadorid")
	private Patrocinador patrocinador;

	//bi-directional many-to-one association to Proyecto
    @ManyToOne
	@JoinColumn(name="proyectoid")
	private Proyecto proyecto;

    public Patrocinio() {
    }

	public Integer getPatrocinioid() {
		return this.patrocinioid;
	}

	public void setPatrocinioid(Integer patrocinioid) {
		this.patrocinioid = patrocinioid;
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

	public Integer getPorcentaje() {
		return this.porcentaje;
	}

	public void setPorcentaje(Integer porcentaje) {
		this.porcentaje = porcentaje;
	}

	public Integer getTipodocumentoid() {
		return this.tipodocumentoid;
	}

	public void setTipodocumentoid(Integer tipodocumentoid) {
		this.tipodocumentoid = tipodocumentoid;
	}

	public Patrocinador getPatrocinador() {
		return this.patrocinador;
	}

	public void setPatrocinador(Patrocinador patrocinador) {
		this.patrocinador = patrocinador;
	}
	
	public Proyecto getProyecto() {
		return this.proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}
	
}