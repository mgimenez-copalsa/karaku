package py.una.pol.karaku.security;

/**
 * Interfaz que define un mecanismo de manipulación automática de ciertos
 * permisos.
 *
 * <p>
 * Permite que los mismos puedan ser omitidos en las implementaciones y se
 * utilice solamente el permiso por defecto.
 * </p>
 *
 * @author Arturo Volpe
 * @since 1.0
 * @version 1.0 Oct 3, 2013
 *
 */
public interface HasDefaultPermissions {

	/**
	 * Retorna una cadena que representa el defecto, este permiso es utilizado
	 * cuando ningún otro permiso es proveído, y es el permiso necesario para
	 * poder Ver un elemento.
	 *
	 * @see #isView()
	 * @return cadena que representa el permiso
	 */
	String getDefaultPermission();

	/**
	 * Retorna la cadena que representa el nombre del permiso necesario para
	 * poder borrar registros en este controlador.<br />
	 * Si no se quiere que se pueda borrar, se puede poner una cadena aleatoria
	 * o retornar <code>""</code>
	 *
	 * @see #isDelete()
	 * @return cadena que representa el permiso
	 */
	String getDeletePermission();

	/**
	 * Retorna la cadena que representa el nombre del permiso necesario para
	 * poder crear nuevos registros en este controlador.<br />
	 *
	 * @see #isCreate()
	 * @return cadena que representa el permiso
	 */
	String getCreatePermission();

	/**
	 * Retorna la cadena que representa el nombre del permiso necesario para
	 * poder editar registros en este controlador.<br />
	 *
	 * @see #isEdit()
	 * @return cadena que representa el permiso
	 */
	String getEditPermission();

	/**
	 * Cuando sea un {@link HasDefaultPermissions} el componente que se este
	 * auditando, y se encuentre esta clave, no se la utilizara como permiso,
	 * sino se invocara al método
	 * {@link HasDefaultPermissions#getDefaultPermission()}
	 */
	String DEFAULT = "DEFAULT";

	/**
	 * Cuando sea un {@link HasDefaultPermissions} el componente que se este
	 * auditando, y se encuentre esta clave, no se la utilizara como permiso,
	 * sino se invocara al método
	 * {@link HasDefaultPermissions#getCreatePermission()}
	 */
	String DEFAULT_CREATE = "DEFAULT_CREATE";

	/**
	 * Cuando sea un {@link HasDefaultPermissions} el componente que se este
	 * auditando, y se encuentre esta clave, no se la utilizara como permiso,
	 * sino se invocara al método
	 * {@link HasDefaultPermissions#getEditPermission()}
	 */
	String DEFAULT_EDIT = "DEFAULT_EDIT";

	/**
	 * Cuando sea un {@link HasDefaultPermissions} el componente que se este
	 * auditando, y se encuentre esta clave, no se la utilizara como permiso,
	 * sino se invocara al método
	 * {@link HasDefaultPermissions#getDeletePermission()}
	 */
	String DEFAULT_DELETE = "DEFAULT_DELETE";
}
