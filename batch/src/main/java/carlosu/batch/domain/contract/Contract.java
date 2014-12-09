package carlosu.batch.domain.contract;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@NamedQueries({
	@NamedQuery(name = "findAllContracts", 
			query = "SELECT c FROM Contract c"),
})
@Entity
public class Contract {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long contractId;
	@OneToOne
	@JoinColumn(name = "instructionId")
	private Instruction instruction;
	@Column
	private String externalId;
	@Column
	private String type;
	
	public Long getContractId() {
		return contractId;
	}
	public void setContractId(Long id) {
		this.contractId = id;
	}
	public Instruction getInstruction() {
		return instruction;
	}
	public void setInstruction(Instruction instruction) {
		this.instruction = instruction;
	}
	public String getExternalId() {
		return externalId;
	}
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
