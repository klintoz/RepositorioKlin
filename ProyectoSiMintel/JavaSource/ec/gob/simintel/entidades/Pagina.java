package ec.gob.simintel.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

//import com.clearminds.tickets.utils.SoloLetras;

/**
 * The persistent class for the pagina database table.
 * 
 * @author  klintozcano
 * 
 */
@Entity
public class Pagina implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pagina")
	private Integer id;

	@Column(name = "es_menu")
	private Boolean esMenu;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_creacion")
	private Date fechaCreacion;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_modificacion")
	private Date fechaModificacion;

	private String icon;
	//@SoloLetras(message = "Solo se admite letras en el campo nombre.")
	private String nombre;

	private Integer orden;

	@Column(name = "perfil_creacion")
	private Integer perfilCreacion;

	@Column(name = "perfil_modificacion")
	private Integer perfilModificacion;

	private String url;

	// bi-directional many-to-one association to Pagina
	@ManyToOne
	@JoinColumn(name = "pag_id_pagina")
	private Pagina pagina;

	// Remueve en cascada
	// bi-directional many-to-one association to Pagina
	@OneToMany(mappedBy = "pagina", orphanRemoval = true)
	private List<Pagina> paginas;

	// Remueve en cascada
	// bi-directional many-to-one association to PaginaRol
	@OneToMany(mappedBy = "pagina", orphanRemoval = true)
	private List<PaginaRol> paginaRols;

	// bi-directional many-to-one association to Rol
	@OneToMany(mappedBy = "pagina")
	private List<Rol> rols;

	@Transient
	private boolean seleccionadoRol;

	public Pagina() {
	}

	/***
	 * Constructor con parámetros.
	 * 
	 * @param esMenu
	 *            <code>true</code> si la página es menú, caso contrario
	 *            <code>false</code>.
	 * @param icon
	 *            Path del icono de la página/menú.
	 * @param nombre
	 *            Nombre de la página.
	 * @param orden
	 *            Orden dentro del menú
	 * @param url
	 *            URL a la cual apunta.
	 * @param pagina
	 *            Objeto del tipo <code>Pagina</code> que representa un Padre.
	 */
	public Pagina(Integer id, Boolean esMenu, String icon, String nombre,
			Integer orden, String url, Pagina pagina) {
		super();
		this.id = id;
		this.esMenu = esMenu;
		this.icon = icon;
		this.nombre = nombre;
		this.orden = orden;
		this.url = url;
		this.pagina = pagina;
	}

	@Override
	public Object clone() {
		Pagina pagina = null;
		try {
			pagina = (Pagina) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return pagina;
	}

	/* Getters y Setters */
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getEsMenu() {
		return this.esMenu;
	}

	public void setEsMenu(Boolean esMenu) {
		this.esMenu = esMenu;
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

	public String getIcon() {
		if (icon != null)
			return this.icon;
		else
			return "imagenes/icons/no-icon.png";
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getOrden() {
		if (orden != null)
			return this.orden;
		else
			return 0;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
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

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Pagina getPagina() {
		return this.pagina;
	}

	public void setPagina(Pagina pagina) {
		this.pagina = pagina;
	}

	public List<Pagina> getPaginas() {
		return this.paginas;
	}

	public void setPaginas(List<Pagina> paginas) {
		this.paginas = paginas;
	}

	public List<PaginaRol> getPaginaRols() {
		return this.paginaRols;
	}

	public void setPaginaRols(List<PaginaRol> paginaRols) {
		this.paginaRols = paginaRols;
	}

	public List<Rol> getRols() {
		return this.rols;
	}

	public void setRols(List<Rol> rols) {
		this.rols = rols;
	}

	public boolean isSeleccionadoRol() {
		return seleccionadoRol;
	}

	public void setSeleccionadoRol(boolean seleccionadoRol) {
		this.seleccionadoRol = seleccionadoRol;
	}

}