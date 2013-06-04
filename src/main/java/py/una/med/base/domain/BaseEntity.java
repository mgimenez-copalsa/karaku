/*
 * @BaseEntity.java 1.0 May 24, 2013 Sistema Integral de Gestion Hospitalaria
 */
package py.una.med.base.domain;

/**
 * 
 * @author Arturo Volpe
 * @since 1.0
 * @version 1.0 May 24, 2013
 * 
 */
public class BaseEntity {

	private Long id;

	/**
	 * @return id
	 */
	public Long getId() {

		return id;
	}

	/**
	 * @param id
	 *            id para setear
	 */
	public void setId(Long id) {

		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result += getClass().hashCode();
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {

		if (obj == null) {
			return false;
		}

		/**
		 * Si es la misma referencia necesariamente es igual.
		 */
		if (this == obj) {
			return true;
		}

		/**
		 * Nos aseguramos que sea asignable desde la clase actual, esto
		 * significa que es una subclase o de la misma clase.
		 */
		if (obj.getClass().isAssignableFrom(getClass())) {
			BaseEntity other = (BaseEntity) obj;
			if (this.id == null) {
				if (other.id == null)
					return true;
				else
					return false;
			} else {
				return id.equals(other.id);
			}

		}

		return false;
	}

}