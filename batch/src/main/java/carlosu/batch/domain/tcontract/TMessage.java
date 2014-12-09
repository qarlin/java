package carlosu.batch.domain.tcontract;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class TMessage {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long messageId;
	@Column
	private String comments;
	@Column
	private String status;
	//@OneToOne
	//@JoinColumn(name="externalId")
	@Transient
	private TContract externalId;
	
	public Long getMessageId() {
		return messageId;
	}
	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public TContract getExternalId() {
		return externalId;
	}
	public void setExternalId(TContract externalId) {
		this.externalId = externalId;
	}
}
