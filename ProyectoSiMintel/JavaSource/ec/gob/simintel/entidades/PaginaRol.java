package ec.gob.simintel.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the pagina_rol database table.
 * 
 */
@Entity
@Table(name = "pagina_rol")
public class PaginaRol implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pagrol")
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_creacion")
	private Date fechaCreacion;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_modificacion")
	private Date fechaModificacion;

	@Column(name = "perfil_creacion")
	private Integer perfilCreacion;

	@Column(name = "perfil_modificacion")
	private Integer perfilModificacion;

	// bi-directional many-to-one association to Pagina
	@ManyToOne
	@JoinColumn(name = "id_pagina")
	private Pagina pagina;

	// bi-directional many-to-one association to Rol
	@ManyToOne
	@JoinColumn(name = "id_rol")
	private Rol rol;

	@Override
	public Object clone() {
		PaginaRol paginaRol = null;
		try {
			paginaRol = (PaginaRol) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return paginaRol;
	}

	/* Getters y Setters */
	public PaginaRol() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaModificacion() {
		return this.fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public Integer getPerfilCreacion() {
		return this.perfilCreacion;
	}

	public void setPerfilCreacion(Integer perfilCreacion) {
		this.perfilCreacion = perfilCreacion;
	}

	public Integer getPerfilModificacion() {
		return this.perfilModificacion;
	}

	public void setPerfilModificacion(Integer perfilModificacion) {
		this.perfilModificacion = perfilModificacion;
	}

	public Pagina getPagina() {
		return this.pagina;
	}

	public void setPagina(Pagina pagina) {
		this.pagina = pagina;
	}

	public Rol getRol() {
		return this.rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

}