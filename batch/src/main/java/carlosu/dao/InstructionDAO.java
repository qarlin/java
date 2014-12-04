package carlosu.dao;

import java.util.List;

import carlosu.model.Contract;

public interface InstructionDAO {

	public List<Contract> getContracts(String commentId);

}
