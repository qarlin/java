package carlosu.batch.jobs.readers;

import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import carlosu.batch.domain.contract.Instruction;
import carlosu.batch.domain.tcontract.TContract;
import carlosu.batch.services.ContractService;

public class InstructionItemReader implements ItemReader<Instruction>{
	private ItemReader<Instruction> instructionReader;
    private ContractService constractService;
    
	public void setContractService(ContractService contractService) {
		this.constractService = contractService;
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
        	List<TContract> contracts = constractService.findContract(instruction.getExternalId()); 
            instruction.setTContracts(contracts);
            return instruction;
        }
	}


}
