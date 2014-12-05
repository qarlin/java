package carlosu.batch.repository.jpa;

import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import carlosu.batch.domain.Contract;
import carlosu.batch.domain.Instruction;
import carlosu.batch.repository.InstructionDao;

@Repository
public class InstructionJPADao extends AbstractJPADao<Instruction, Long> implements InstructionDao{

	public InstructionJPADao() {
		super(Instruction.class);
	}

	@Override
	public List<Contract> getContracts(String commentId) {
		return null;
	}

	public List<Instruction> getAllInstructions(Date bizDay) {
		TypedQuery<Instruction> query = entityManager.createNamedQuery("findInstructions", Instruction.class);
		query.setParameter("bizday", bizDay);
		return query.getResultList();
	}

	@Override
	public List<Instruction> getInstructions(Date bizDay) {
		TypedQuery<Instruction> query = entityManager.createNamedQuery("findNativeInstruction", Instruction.class);
		return query.getResultList();
	}
	@Override
	public List<Contract> getContracts(Date date) {
		TypedQuery<Contract> query = entityManager.createQuery("SELECT c FROM Contract c", Contract.class);
		return query.getResultList();
	}

}
