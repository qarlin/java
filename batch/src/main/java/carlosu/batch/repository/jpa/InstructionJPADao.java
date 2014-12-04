package carlosu.batch.repository.jpa;

import java.util.List;

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

}
