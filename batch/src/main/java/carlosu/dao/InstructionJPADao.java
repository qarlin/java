package carlosu.dao;

import org.springframework.stereotype.Repository;

import carlosu.model.Instruction;

@Repository
public class InstructionJPADao extends AbstractJPADao<Instruction, Long> {

	public InstructionJPADao() {
		super(Instruction.class);
	}

}
