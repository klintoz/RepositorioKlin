package ec.gob.simintel.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the mediocontacto database table.
 * 
 */
@Entity
public class Mediocontacto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer mediocontactoid;

	private String datocontacto;

	//bi-directional many-to-one association to Contacto
    @ManyToOne
	@JoinColumn(name="contactosid")
	private Contacto contacto;

	//bi-directional many-to-one association to Datoscontacto
    @ManyToOne
	@JoinColumn(name="datoscontactoid")
	private Datoscontacto datoscontacto;

	//bi-directional many-to-one association to Tipomedio
    @ManyToOne
	@JoinColumn(name="tipomedioid")
	private Tipomedio tipomedio;
    
    @Transient
    private int indice; 
    
    public Mediocontacto() {
    }

	public Integer getMediocontactoid() {
		return this.mediocontactoid;
	}

	public void setMediocontactoid(Integer mediocontactoid) {
		this.mediocontactoid = mediocontactoid;
	}

	public String getDatocontacto() {
		return this.datocontacto;
	}

	public void setDatocontacto(String datocontacto) {
		this.datocontacto = datocontacto;
	}

	public Contacto getContacto() {
		return this.contacto;
	}

	public void setContacto(Contacto contacto) {
		this.contacto = contacto;
	}
	
	public Datoscontacto getDatoscontacto() {
		return this.datoscontacto;
	}

	public void setDatoscontacto(Datoscontacto datoscontacto) {
		this.datoscontacto = datoscontacto;
	}
	
	public Tipomedio getTipomedio() {
		return this.tipomedio;
	}

	public void setTipomedio(Tipomedio tipomedio) {
		this.tipomedio = tipomedio;
	}

	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}


}