package carlosu.batch.domain.tcontract;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class TContract {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long contractId;
	@Column
	private String externalId;
	@Column
	private String notes;
	@Column
	private String status;
	
	//@Column
	//@JoinColumn(name="externalId")
	@Transient
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
