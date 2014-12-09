package carlosu.batch.repository.jpa;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import carlosu.batch.domain.contract.Contract;
import carlosu.batch.domain.contract.Instruction;
import carlosu.batch.repository.InstructionDao;

@Repository
public class InstructionJPADao extends AbstractDb1JPADao<Instruction, Long> implements InstructionDao{

	@Override
	public List<Contract> getContracts(String commentId) {
		return null;
	}

	public List<Instruction> getAllInstructions(Date bizDay) {
		TypedQuery<Instruction> query = getEntityManager().createNamedQuery("findInstructions", Instruction.class);
		query.setParameter("bizday", bizDay);
		return query.getResultList();
	}

	@Override
	public List<Instruction> getInstructions(Date bizDay) {
		TypedQuery<Instruction> query = getEntityManager().createNamedQuery("findNativeInstruction", Instruction.class);
		query.setParameter("bizday", bizDay);
		return query.getResultList();
	}
	@Override
	public List<Contract> getContracts(Date date) {
		TypedQuery<Contract> query = getEntityManager().createQuery("SELECT c FROM Contract c", Contract.class);
		return query.getResultList();
	}

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

}
