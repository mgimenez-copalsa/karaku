/*
 * @DummyReplicationProvider.java 1.0 Nov 7, 2013 Sistema Integral de Gestion
 * Hospitalaria
 */
package py.una.med.base.replication.server;

import java.util.List;
import org.apache.poi.ss.formula.eval.NotImplementedException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import py.una.med.base.replication.Shareable;

/**
 * {@link ReplicationProvider} que simplemente envía toda la tabla cada vez que
 * se solicita un cambio.
 * 
 * <p>
 * Solo funciona cuando el id del cambio es {@link Bundle#ZERO_ID}, en caso
 * contrario lanza una {@link NotImplementedException}</p<
 * 
 * @author Arturo Volpe
 * @since 2.2.8
 * @version 1.0 Nov 7, 2013
 * 
 */
@Component
@Transactional
public class DummyReplicationProvider implements ReplicationProvider {

	@Autowired
	SessionFactory factory;

	@Override
	@Transactional(readOnly = true)
	public <T extends Shareable> Bundle<T> getChanges(Class<T> clazz,
			String lastId) {

		if (Bundle.ZERO_ID.equals(lastId)) {
			return getAll(clazz);
		}
		throw new NotImplementedException(
				"Dummy Replication provider dont support a ID != 'ZERO'");
	}

	@SuppressWarnings("unchecked")
	private <T extends Shareable> Bundle<T> getAll(Class<T> clazz) {

		List<T> entities = getSession().createCriteria(clazz).list();
		Bundle<T> toRet = new Bundle<T>();
		for (T entitie : entities) {
			toRet.add(entitie, Bundle.ZERO_ID);
		}
		return toRet;

	}

	protected Session getSession() {

		return factory.getCurrentSession();
	}

}
