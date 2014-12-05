package carlosu.batch.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Transient;

@NamedQueries({
	@NamedQuery(name = "findInstructions", 
			query = "SELECT i FROM Instruction i WHERE i.bizDay = :bizday"),
	@NamedQuery(name = "findAllInstructions", 
			query = "SELECT i FROM Instruction i")
})
@NamedNativeQueries({
	@NamedNativeQuery(name = "findNativeInstruction", resultClass = Instruction.class, 
			query = "SELECT i.instructionId, i.bizDay, i.commentId " 
					+ "FROM instruction i LEFT JOIN contract c ON (i.instructionId = c.instructionId) "
					+ "WHERE c.contractId is null")
})
@Entity
public class Instruction {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long instructionId;
	@Column
	private String commentId;
	@Column
	private String secId;
	@Column 
	private BigDecimal qty;
	@Column
	private Date bizDay;
	@Transient
	private List<Contract> contracts;
	
	public String getCommentId() {
		return commentId;
	}
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	public Long getInstructionId() {
		return instructionId;
	}
	public void setInstructionId(Long id) {
		this.instructionId = id;
	}
	public List<Contract> getContracts() {
		return contracts;
	}
	public void setContracts(List<Contract> contracts) {
		this.contracts = contracts;
	}
	public String getSecId() {
		return secId;
	}
	public void setSecId(String secId) {
		this.secId = secId;
	}
	public BigDecimal getQty() {
		return qty;
	}
	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}
	public Date getBizDay() {
		return bizDay;
	}
	public void setBizDay(Date bizDay) {
		this.bizDay = bizDay;
	}
}
