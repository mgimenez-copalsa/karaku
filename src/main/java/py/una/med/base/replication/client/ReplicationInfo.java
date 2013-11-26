/*
 * @ReplicationInfo.java 1.0 Nov 22, 2013 Sistema Integral de Gestion
 * Hospitalaria
 */
package py.una.med.base.replication.client;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import py.una.med.base.dao.annotations.CaseSensitive;
import py.una.med.base.dao.entity.annotations.Time;
import py.una.med.base.dao.entity.annotations.Time.Type;
import py.una.med.base.domain.BaseEntity;
import py.una.med.base.replication.DTO;
import py.una.med.base.replication.Shareable;
import py.una.med.base.services.client.WSEndpoint;

/**
 * 
 * @author Arturo Volpe
 * @since 2.2.8
 * @version 1.0 Nov 22, 2013
 * 
 */
@Entity
@Table(name = "replication_info")
@SequenceGenerator(name = "REPLICATION_INFO_SEQ", sequenceName = "replication_info_seq")
public class ReplicationInfo extends BaseEntity {

	private static final long serialVersionUID = 2664426116899009727L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REPLICATION_INFO_SEQ")
	private Long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "ws_end_point_id")
	private WSEndpoint wsEndpoint;

	@Column(name = "last_id")
	private String lastId;

	@NotNull
	@Time(type = Type.DATETIME)
	@Column(name = "last_sync")
	private Date lastSync;

	@CaseSensitive
	@NotNull
	@Column(name = "entity_class_name")
	private String entityClassName;

	@CaseSensitive
	@NotNull
	@Column(name = "dto_class_name")
	private String dtoClassName;

	@CaseSensitive
	@NotNull
	@Column(name = "request_class_name")
	private String requestClassName;

	@CaseSensitive
	@NotNull
	@Column(name = "response_class_name")
	private String responseClassName;

	@Transient
	private Class<? extends Shareable> entityClazz;

	@Transient
	private Class<? extends DTO> daoClazz;

	@Transient
	private Class<?> requestClazz;

	@Transient
	private Class<?> responseClazz;

	private int interval;

	private boolean active;

	@Override
	public void setId(Long id) {

		this.id = id;

	}

	ReplicationInfo() {

	}

	/**
	 * @param id
	 * @param lastSync
	 * @param entityClazz
	 * @param daoClazz
	 * @param requestClazz
	 * @param responseClazz
	 * @param interval
	 */
	public ReplicationInfo(Long id, String lastId,
			Class<? extends Shareable> entityClazz,
			Class<? extends DTO> daoClazz, Class<?> requestClazz,
			Class<?> responseClazz, int interval) {

		super();
		this.id = id;
		this.lastId = lastId;
		this.entityClazz = entityClazz;
		this.daoClazz = daoClazz;
		this.requestClazz = requestClazz;
		this.responseClazz = responseClazz;
		this.interval = interval;
	}

	@Override
	public Long getId() {

		return this.id;
	}

	/**
	 * @return lastId
	 */
	public String getLastId() {

		return lastId;
	}

	/**
	 * @param lastId
	 *            lastId para setear
	 */
	void setLastId(String lastId) {

		this.lastId = lastId;
	}

	/**
	 * @return wsEndpoint
	 */
	public WSEndpoint getWsEndpoint() {

		return wsEndpoint;
	}

	/**
	 * @param wsEndpoint
	 *            wsEndpoint para setear
	 */
	void setWsEndpoint(WSEndpoint wsEndpoint) {

		this.wsEndpoint = wsEndpoint;
	}

	/**
	 * @return interval
	 */
	public int getInterval() {

		return interval;
	}

	/**
	 * @param interval
	 *            interval para setear
	 */
	void setInterval(int interval) {

		this.interval = interval;
	}

	/**
	 * @return active
	 */
	public boolean isActive() {

		return active;
	}

	/**
	 * @param active
	 *            active para setear
	 */
	void setActive(boolean active) {

		this.active = active;
	}

	/**
	 * @return lastSync
	 */
	public Date getLastSync() {

		return lastSync;
	}

	/**
	 * @param lastSync
	 *            lastSync para setear
	 */
	void setLastSync(Date lastSync) {

		this.lastSync = lastSync;
	}

	/**
	 * @return daoClazz
	 */
	public Class<? extends DTO> getDaoClazz() {

		return daoClazz;
	}

	/**
	 * @param daoClazz
	 *            daoClazz para setear
	 */
	void setDaoClazz(Class<? extends DTO> daoClazz) {

		this.daoClazz = daoClazz;
	}

	/**
	 * @return entityClazz
	 */
	public Class<? extends Shareable> getEntityClazz() {

		return entityClazz;
	}

	/**
	 * @param entityClazz
	 *            entityClazz para setear
	 */
	void setEntityClazz(Class<? extends Shareable> entityClazz) {

		this.entityClazz = entityClazz;
	}

	/**
	 * @return entityClassName
	 */
	public String getEntityClassName() {

		return entityClassName;
	}

	/**
	 * @param entityClassName
	 *            entityClassName para setear
	 */
	void setEntityClassName(String entityClassName) {

		this.entityClassName = entityClassName;
	}

	/**
	 * @return daoClassName
	 */
	public String getDtoClassName() {

		return dtoClassName;
	}

	/**
	 * @param dtoClassName
	 *            daoClassName para setear
	 */
	void setDtoClassName(String dtoClassName) {

		this.dtoClassName = dtoClassName;
	}

	/**
	 * @return requestClassName
	 */
	public String getRequestClassName() {

		return requestClassName;
	}

	/**
	 * @param requestClassName
	 *            requestClassName para setear
	 */
	void setRequestClassName(String requestClassName) {

		this.requestClassName = requestClassName;
	}

	/**
	 * @return responseClassName
	 */
	public String getResponseClassName() {

		return responseClassName;
	}

	/**
	 * @param responseClassName
	 *            responseClassName para setear
	 */
	void setResponseClassName(String responseClassName) {

		this.responseClassName = responseClassName;
	}

	/**
	 * @return requestClazz
	 */
	public Class<?> getRequestClazz() {

		return requestClazz;
	}

	/**
	 * @param requestClazz
	 *            requestClazz para setear
	 */
	void setRequestClazz(Class<?> requestClazz) {

		this.requestClazz = requestClazz;
	}

	/**
	 * @return responseClazz
	 */
	public Class<?> getResponseClazz() {

		return responseClazz;
	}

	/**
	 * @param responseClazz
	 *            responseClazz para setear
	 */
	void setResponseClazz(Class<?> responseClazz) {

		this.responseClazz = responseClazz;
	}
}
