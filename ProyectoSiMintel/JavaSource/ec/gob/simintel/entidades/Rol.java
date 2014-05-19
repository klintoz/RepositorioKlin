package ec.gob.simintel.entidades;

import java.io.Serializable;
import javax.persistence.*;

//import com.clearminds.tickets.utils.SoloLetras;

import java.util.Date;
import java.util.List;

/**
 * The persistent class for the rol database table.
 * 
 */
@Entity
public class Rol implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_rol")
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_creacion")
	private Date fechaCreacion;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_modificacion")
	private Date fechaModificacion;

	//@SoloLetras(message = "Solo se admiten letras en el campo nombre.")
	private String nombre;

	@Column(name = "perfil_creacion")
	private Integer perfilCreacion;

	@Column(name = "perfil_modificacion")
	private Integer perfilModificacion;

	// bi-directional many-to-one association to PaginaRol
	@OneToMany(mappedBy = "rol", orphanRemoval = true)
	private List<PaginaRol> paginaRols;

	// bi-directional many-to-one association to Perfil
	@OneToMany(mappedBy = "rol", orphanRemoval = true)
	private List<Perfil> perfils;

	// bi-directional many-to-one association to Pagina
	@ManyToOne
	@JoinColumn(name = "id_pagina")
	private Pagina pagina;

	@Transient
	private boolean rolSeleccionado;

	public Rol() {
		this.pagina = new Pagina();
	}

	@Override
	public Object clone() {
		Rol rol = null;
		try {
			rol = (Rol) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return rol;
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

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public List<PaginaRol> getPaginaRols() {
		return this.paginaRols;
	}

	public void setPaginaRols(List<PaginaRol> paginaRols) {
		this.paginaRols = paginaRols;
	}

	public List<Perfil> getPerfils() {
		return this.perfils;
	}

	public void setPerfils(List<Perfil> perfils) {
		this.perfils = perfils;
	}

	public Pagina getPagina() {
		return this.pagina;
	}

	public void setPagina(Pagina pagina) {
		this.pagina = pagina;
	}

	public boolean isRolSeleccionado() {
		return rolSeleccionado;
	}

	public void setRolSeleccionado(boolean rolSeleccionado) {
		this.rolSeleccionado = rolSeleccionado;
	}
}