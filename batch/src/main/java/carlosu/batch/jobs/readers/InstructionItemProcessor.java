package carlosu.batch.jobs.readers;

import org.springframework.batch.item.ItemProcessor;

import carlosu.batch.domain.contract.Instruction;
import carlosu.batch.domain.contract.SourceReport;

public class InstructionItemProcessor implements
		ItemProcessor<Instruction, SourceReport> {

	@Override
	public SourceReport process(Instruction item) throws Exception {
		SourceReport sr = new SourceReport();
		sr.setId(item.getExternalId());
		sr.setMessage(item.getCommentId());
		return sr;
	}

}
