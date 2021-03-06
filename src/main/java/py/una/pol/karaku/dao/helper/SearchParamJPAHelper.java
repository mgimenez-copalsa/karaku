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
package py.una.pol.karaku.dao.helper;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import py.una.pol.karaku.dao.search.OrderParam;
import py.una.pol.karaku.dao.search.SearchParam;

public class SearchParamJPAHelper<T> {

	public TypedQuery<T> apply(SearchParam params, TypedQuery<T> query) {

		if (params == null) {
			return query;
		}

		if (params.getLimit() != null && params.getLimit() > 0L) {
			query.setMaxResults(params.getLimit().intValue());
		}
		if (params.getOffset() != null && params.getOffset() > 0L) {
			query.setFirstResult(params.getOffset().intValue());
		}
		return query;
	}

	public CriteriaQuery<T> apply(SearchParam params, CriteriaQuery<T> query,
			Root<T> root, CriteriaBuilder builder) {

		if (params == null) {
			return query;
		}
		List<Order> orders = new ArrayList<Order>();
		if (params.getOrders() != null) {
			for (OrderParam orderParam : params.getOrders()) {
				if (orderParam.isAsc()) {
					orders.add(builder.asc(root.get(orderParam.getColumnName())));
				} else {
					orders.add(builder.desc(root.get(orderParam.getColumnName())));
				}
			}
		}
		query.orderBy(orders);
		return query;
	}

	public CriteriaQuery<T> aplly(CriteriaBuilder criteriaBuilder,
			CriteriaQuery<T> criteriaQuery, Root<T> root, T ejemplo) {

		return criteriaQuery;

	}
}
