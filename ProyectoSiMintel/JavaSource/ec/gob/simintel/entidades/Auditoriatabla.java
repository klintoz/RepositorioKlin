package ec.gob.simintel.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the auditoriatablas database table.
 * 
 */
@Entity
@Table(name="auditoriatablas")
public class Auditoriatabla implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer auditoriaid;

	private Integer pkid;

	private String tabla;

	//bi-directional many-to-one association to Detalleauditoria
	@OneToMany(mappedBy="auditoriatabla")
	private List<Detalleauditoria> detalleauditorias;

    public Auditoriatabla() {
    }

	public Integer getAuditoriaid() {
		return this.auditoriaid;
	}

	public void setAuditoriaid(Integer auditoriaid) {
		this.auditoriaid = auditoriaid;
	}

	public Integer getPkid() {
		return this.pkid;
	}

	public void setPkid(Integer pkid) {
		this.pkid = pkid;
	}

	public String getTabla() {
		return this.tabla;
	}

	public void setTabla(String tabla) {
		this.tabla = tabla;
	}

	public List<Detalleauditoria> getDetalleauditorias() {
		return this.detalleauditorias;
	}

	public void setDetalleauditorias(List<Detalleauditoria> detalleauditorias) {
		this.detalleauditorias = detalleauditorias;
	}
	
}