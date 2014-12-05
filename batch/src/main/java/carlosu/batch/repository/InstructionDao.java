package carlosu.batch.repository;

import java.util.Date;
import java.util.List;

import carlosu.batch.domain.Contract;
import carlosu.batch.domain.Instruction;

public interface InstructionDao extends AbstractDao<Instruction, Long>{

	public List<Contract> getContracts(String commentId);
	public List<Instruction> getInstructions(Date bizDay);
	public List<Contract> getContracts(Date date);
	
}
