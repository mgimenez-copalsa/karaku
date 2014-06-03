/*
 * @Operation.java 1.0 Nov 5, 2013 Sistema Integral de Gestion Hospitalaria
 */
package py.una.pol.karaku.dao.entity;

import javax.annotation.Nonnull;

/**
 * 
 * @author Arturo Volpe
 * @since 2.2.8
 * @version 1.0 Nov 5, 2013
 * 
 */
public enum Operation {

	/**
	 * Define una operación de persistencia.
	 * 
	 * @see py.una.pol.karaku.dao.BaseDAO#add(Object)
	 */
	@Nonnull
	CREATE,
	/**
	 * Define una operación de actualización.
	 * 
	 * @see py.una.pol.karaku.dao.BaseDAO#update(Object)
	 */
	@Nonnull
	UPDATE,
	/**
	 * Define una operación de eliminación.
	 * 
	 * <p>
	 * Hay ciertos casos, donde la operación de eliminación puede ser omitida,
	 * por ejemplo en los casos de entidades que implementan la interfaz
	 * {@link py.una.pol.karaku.replication.Shareable}. Donde existe un
	 * <i>interceptor</i> que se encarga de modificarla y actualizarla.
	 * </p>
	 * 
	 * @see py.una.pol.karaku.dao.BaseDAO#remove(Object)
	 */
	@Nonnull
	DELETE;
}
