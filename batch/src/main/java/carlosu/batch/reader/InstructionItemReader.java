package carlosu.batch.reader;

import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.ItemReader;

import carlosu.dao.InstructionDAO;
import carlosu.model.Instruction;

public class InstructionItemReader implements ItemReader<Instruction>{
	private ItemReader<Instruction> instructionReader;
    private InstructionDAO instructionDAO;
    
	public void setInstructionDAO(InstructionDAO instructionDao) {
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
