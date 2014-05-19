package ec.gob.simintel.commons.datamanager;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ec.gob.simintel.administracion.servicios.ServicioConvenio;
import ec.gob.simintel.administracion.servicios.ServicioGestionInversion;
import ec.gob.simintel.administracion.servicios.ServicioPlanInversion;
import ec.gob.simintel.administracion.servicios.ServicioProyecto;
import ec.gob.simintel.entidades.Convenio;
import ec.gob.simintel.entidades.Inversion;
import ec.gob.simintel.entidades.Planinversion;
import ec.gob.simintel.entidades.Programa;
import ec.gob.simintel.entidades.Proyecto;

@ManagedBean
@SessionScoped
public class GeneralDataManager {
	
	private List<Programa> lstProgramas;
	private Integer idPrograma;
	
	
	@EJB
	private ServicioProyecto srvProyecto;
	private List<Proyecto> lstProyectos;
	private Proyecto proyecto;
	private Integer idProyecto;
	
	@EJB
	private ServicioConvenio srvConvenio;
	private List<Convenio> lstConvenios; //lista de la tabla de la pagina Convenios
	private Convenio convenio; //Objeto que vas a usar en la pagina Convenios
	private Integer idConvenio; //Es el que va atado al combo de la pagina GestionInversion
	
	@EJB
	private ServicioGestionInversion srvInversion;
	private List<Inversion> lstInversiones;//lista de la tabla de la pagina GestionInversion
	private Inversion inversion;
	private Integer idInversion;
	
	@EJB
	private ServicioPlanInversion srvPlaInversion;
	private List<Planinversion> lstPlanInversion;
	private Planinversion planinversion;
	private Integer idPlanInversion;
	
	public GeneralDataManager() {
		// TODO Auto-generated constructor stub
		lstProgramas = new ArrayList<Programa>();
		lstProyectos = new ArrayList<Proyecto>();
		lstConvenios = new ArrayList<Convenio>();
		lstInversiones= new ArrayList<Inversion>();
		lstPlanInversion = new ArrayList<Planinversion>();
		
	}

	public void cargarListaProyectos(){
	    lstProyectos= srvProyecto.buscarProyectoPorPrograma(idPrograma);
	       
	}
	
	public void cargarListaConvenios(){
		lstConvenios=srvConvenio.buscarConvenioPorProyecto(idProyecto);
	}
	
	public void cargarListaInversion(){
		lstInversiones = srvInversion.buscarInversionesPorConvenio(idConvenio); 
		
	}
	
	public void cargarListaPlanesInversion(){
		lstPlanInversion = srvPlaInversion.buscarPlanInverPorInversion(idInversion);
	}
	
	public List<Programa> getLstProgramas() {
		return lstProgramas;
	}

	public void setLstProgramas(List<Programa> lstProgramas) {
		this.lstProgramas = lstProgramas;
	}


	public Integer getIdPrograma() {
		return idPrograma;
	}


	public void setIdPrograma(Integer idPrograma) {
		this.idPrograma = idPrograma;
	}


	public List<Proyecto> getLstProyectos() {
		return lstProyectos;
	}

	public void setLstProyectos(List<Proyecto> lstProyectos) {
		this.lstProyectos = lstProyectos;
	}

	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	public List<Convenio> getLstConvenios() {
		return lstConvenios;
	}

	public void setLstConvenios(List<Convenio> lstConvenios) {
		this.lstConvenios = lstConvenios;
	}

	public Convenio getConvenio() {
		return convenio;
	}

	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
	}

	public Integer getIdProyecto() {
		return idProyecto;
	}

	public void setIdProyecto(Integer idProyecto) {
		this.idProyecto = idProyecto;
	}

	public List<Inversion> getLstInversiones() {
		return lstInversiones;
	}

	public void setLstInversiones(List<Inversion> lstInversiones) {
		this.lstInversiones = lstInversiones;
	}

	public Inversion getInversion() {
		return inversion;
	}

	public void setInversion(Inversion inversion) {
		this.inversion = inversion;
	}

	public Integer getIdInversion() {
		return idInversion;
	}

	public void setIdInversion(Integer idInversion) {
		this.idInversion = idInversion;
	}

	public Integer getIdConvenio() {
		return idConvenio;
	}

	public void setIdConvenio(Integer idConvenio) {
		this.idConvenio = idConvenio;
	}

	public List<Planinversion> getLstPlanInversion() {
		return lstPlanInversion;
	}

	public void setLstPlanInversion(List<Planinversion> lstPlanInversion) {
		this.lstPlanInversion = lstPlanInversion;
	}

	public Planinversion getPlaninversion() {
		return planinversion;
	}

	public void setPlaninversion(Planinversion planinversion) {
		this.planinversion = planinversion;
	}

	public Integer getIdPlanInversion() {
		return idPlanInversion;
	}

	public void setIdPlanInversion(Integer idPlanInversion) {
		this.idPlanInversion = idPlanInversion;
	}

}
