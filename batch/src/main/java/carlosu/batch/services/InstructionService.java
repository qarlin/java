package carlosu.batch.services;

import java.util.Date;
import java.util.List;

import carlosu.batch.domain.contract.Instruction;

public interface InstructionService {

	List<Instruction> findInstructions(Date bizDate);
}
