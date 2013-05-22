/*
 * @HostResolver.java 1.0 May 16, 2013 Sistema Integral de Gestion Hospitalaria
 */
package py.una.med.base.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import py.una.med.base.configuration.PropertiesUtil;

/**
 * Clase de ayuda que determina la URL de los sistemas.
 * 
 * @author Jorge Ramírez
 * @since 1.0
 * @version 1.0 May 16, 2013
 * 
 */
@Component
public class HostResolver {

	@Autowired
	private PropertiesUtil properties;

	/**
	 * Retorna la URL del sistema dado como parámetro
	 * 
	 * @param app
	 *            "CONF", "IDP" o "SAF"
	 **/
	public String getSystemURL(final String app) {

		// por el momento se asume que todos los sistemas corren en el mismo
		// servidor
		String url = "";

		if ("CONF".equals(app)) {
			url += "/" + properties.getProperty("conf.urlFragment") + "/faces";
		} else if ("IDP".equals(app)) {
			url += "/" + properties.getProperty("idp.urlFragment") + "/faces";
		} else if ("SAF".equals(app)) {
			url += "/" + properties.getProperty("saf.urlFragment") + "/faces";
		}
		return url;
	}
}