package ejbprocess.dao;

import java.util.Date;
import java.util.List;

import ejbprocess.model.Contract;

public interface ContractDAO {
	public List<Contract> getInstructions(Date date);
}
