package carlosu.batch.domain.tcontract;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToOne;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;

@NamedNativeQueries({ 
	@NamedNativeQuery(name = "findAllTContracts", resultSetMapping = "tContractMapping", 
	query = "SELECT tc.contractId, tc.externalId, tc.notes, tc.status, tm.messageId as message_messageId, " 
			+ " tm.messageId, tm.comments, tm.status as status2, tm.externalId as externalId2 " 
			+ "FROM tcontract tc JOIN tmessage tm ON (tc.externalId = tm.externalId) "),
	@NamedNativeQuery(name = "findTContracts", resultSetMapping = "tContractMapping", 
			query = "SELECT tc.contractId, tc.externalId, tc.notes, tc.status, tm.messageId as message_messageId, " 
					+ " tm.messageId, tm.comments, tm.status as status2, tm.externalId as externalId2 " 
					+ "FROM tcontract tc JOIN tmessage tm ON (tc.externalId = tm.externalId) "
					+ "WHERE tc.externalId like :date "),
	@NamedNativeQuery(name = "findTContract", resultSetMapping = "tContractMapping", 
			query = "SELECT tc.contractId, tc.externalId, tc.notes, tc.status, tm.messageId as message_messageId, " 
					+ " tm.messageId, tm.comments, tm.status as status2, tm.externalId as externalId2 " 
					+ "FROM tcontract tc JOIN tmessage tm ON (tc.externalId = tm.externalId) "
					+ "WHERE tc.externalId = :externalId ")
})
@SqlResultSetMappings({ 
	@SqlResultSetMapping(name = "tContractMappingNoWork", 
		entities = {
		@EntityResult(entityClass = TContract.class, fields = {
				@FieldResult(name = "contractId", column = "contractId"),
				@FieldResult(name = "externalId", column = "externalId"),
				@FieldResult(name = "notes", column = "notes"),
				@FieldResult(name = "status", column = "status"),
				}, discriminatorColumn="message"),
		@EntityResult(entityClass = TMessage.class, fields = {
				@FieldResult(name = "messageId", column = "messageId"),
				@FieldResult(name = "comments", column = "comments"),
				@FieldResult(name = "status", column = "status2"),
				@FieldResult(name = "externalId", column = "externalId2")
				 }) }),
	@SqlResultSetMapping(name="tContractMapping",
				      entities={@EntityResult(entityClass=TContract.class),
								@EntityResult(entityClass=TMessage.class)}
				)}
)

@Entity
public class TContract {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long contractId;
	@Column
	private String externalId;
	@Column
	private String notes;
	@Column
	private String status;
	@OneToOne
	private TMessage message;

	public TMessage getMessage() {
		return message;
	}

	public void setMessage(TMessage message) {
		this.message = message;
	}

	public Long getContractId() {
		return contractId;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
