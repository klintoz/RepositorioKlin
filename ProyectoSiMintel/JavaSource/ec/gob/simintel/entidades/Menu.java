package ec.gob.simintel.entidades;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Klintozcano
 * 
 */
public class Menu implements Comparable<Menu> {
	private String nombre;
	private String icon;
	private String url;
	private int orden;
	private int id;
	private List<Menu> subMenus;

	/**
	 * Constructor que inicializa la lista de subMenus.
	 */
	public Menu() {
		this.subMenus = new ArrayList<Menu>();
	}

	/**
	 * Se usa para realizar la ordenacion de los menus usando el metodo .sort de
	 * <code>Collections</code>.
	 * 
	 * @param m1
	 *            Representa el objeto en este caso del tipo<code>Menu</code> a
	 *            ser comparado.
	 * @return Un entero negativo, cero o un entero positivo si el objeto
	 *         <code>this</code> es menor, igual o mayor que es objeto
	 *         especificado en el parámetro.
	 */
	@Override
	public int compareTo(Menu m1) {
		if (((Integer) this.orden).compareTo(m1.orden) == 0)
			return 0;
		else if (((Integer) this.orden).compareTo(m1.orden) < 1)
			return -1;
		else
			return 1;

		// No lo usamos por que el compareTo devuelve a veces valores diferentes
		// de
		// -1 y 1
		// return ((Integer) this.orden).compareTo(m1.orden);
	}

	/**
	 * Se usa en el contains y en el indexOf que es usado en el servicio Menu
	 * para verificar la existencia de elementos duplicados.
	 * 
	 * @param m1
	 *            Representa el objeto en este caso del tipo <code>Menu</code> a
	 *            ser comparado.
	 * @return <code>true<code> Si los nombres y URL de los elementos comparados son iguales, caso contrario <code>false</code>
	 */
	@Override
	public boolean equals(Object m1) {
		// Solo comparamos el nombre, debido que aunque el URL tiene que ser
		// diferente por cada página, eso ya esta controlado en la inserción,
		// ademas pueden haber redirecciones hacia # repetidas en los padres.
		if (this.getNombre().trim().compareTo(((Menu) m1).getNombre().trim()) == 0) {
			// if ((this.getNombre().equals(((Menu) m1).getNombre()))
			// || (this.getUrl().equals(((Menu) m1).getUrl()))) {

			return true;
		}
		return false;
	}

	public Menu(String nombre, String icon, String url) {
		super();
		this.nombre = nombre;
		this.icon = icon;
		this.url = url;
	}

	public Menu(String nombre, String icon, String url, int orden) {
		super();
		this.nombre = nombre;
		this.icon = icon;
		this.url = url;
		this.orden = orden;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getOrden() {
		return orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	public List<Menu> getSubMenus() {
		return subMenus;
	}

	public void setSubMenus(List<Menu> subMenus) {
		this.subMenus = subMenus;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
