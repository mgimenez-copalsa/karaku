/*-
 * Copyright (c)
 *
 * 		2012-2014, Facultad Politécnica, Universidad Nacional de Asunción.
 * 		2012-2014, Facultad de Ciencias Médicas, Universidad Nacional de Asunción.
 * 		2012-2013, Centro Nacional de Computación, Universidad Nacional de Asunción.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package py.una.pol.karaku.test.util;

import org.hibernate.Session;
import py.una.pol.karaku.dao.entity.interceptors.UriInterceptor;

/**
 * 
 * @author Arturo Volpe
 * @since 2.2.8
 * @version 1.0 Dec 11, 2013
 * 
 */
public class TestUriInterceptor extends UriInterceptor {

	Session session;

	@Override
	protected Session getSession() {

		if (session != null) {
			return session;
		} else {
			return super.getSession();
		}
	}

	/**
	 * Session que se utilizara.
	 * 
	 * @param session
	 */
	public void setSession(Session session) {

		this.session = session;
	}
}
