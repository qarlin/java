package carlosu.batch.repository;

import java.util.List;

import carlosu.batch.domain.Contract;

public interface InstructionDao {

	public List<Contract> getContracts(String commentId);

}
