package carlosu.batch.jobs.readers;

import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.ItemReader;

import carlosu.batch.domain.contract.Instruction;
import carlosu.batch.repository.InstructionDao;

public class InstructionItemReader implements ItemReader<Instruction>{
	private ItemReader<Instruction> instructionReader;
    private InstructionDao instructionDAO;
    
	public void setInstructionDAO(InstructionDao instructionDao) {
		this.instructionDAO = instructionDao;
	}

	public void setItemReader(ItemReader<Instruction> instructionReader) {
		this.instructionReader = instructionReader;
	}

	@Override
	public Instruction read() throws Exception, UnexpectedInputException,
			ParseException, NonTransientResourceException {
		Instruction instruction = instructionReader.read();
		
		if(instruction == null) {
            return null;
        } else {
            instruction.setContracts(instructionDAO.getContracts(instruction.getCommentId()));
            return instruction;
        }
	}
}
