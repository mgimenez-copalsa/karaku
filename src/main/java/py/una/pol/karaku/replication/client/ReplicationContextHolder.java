/*
 * @ReplicationContextHolder.java 1.0 Nov 29, 2013 Sistema Integral de Gestion
 * Hospitalaria
 */
package py.una.pol.karaku.replication.client;

/**
 * 
 * @author Arturo Volpe
 * @since 2.2.8
 * @version 1.0 Nov 29, 2013
 * 
 */
public final class ReplicationContextHolder {

	private static final ThreadLocal<ReplicationContext> HOLDER = new ThreadLocal<ReplicationContext>();

	public static ReplicationContext getContext() {

		return HOLDER.get();

	}

	static void setContext(ReplicationContext rc) {

		HOLDER.set(rc);
	}

	private ReplicationContextHolder() {

	}

	public static final class ReplicationContext {

		private String replicationUser;
		private String currentClassName;

		/**
		 * @return currentClassName
		 */
		public String getCurrentClassName() {

			return currentClassName;
		}

		/**
		 * @return replicationUser
		 */
		public String getReplicationUser() {

			return replicationUser;
		}

		/**
		 * @param currentClassName
		 *            currentClassName para setear
		 */
		public void setCurrentClassName(String currentClassName) {

			this.currentClassName = currentClassName;
		}

		/**
		 * @param replicationUser
		 *            replicationUser para setear
		 */
		public void setReplicationUser(String replicationUser) {

			this.replicationUser = replicationUser;
		}
	}

}
