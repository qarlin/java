package carlosu.batch.services;

import java.util.List;

import carlosu.batch.domain.tcontract.TContract;

public interface ContractService {

	List<TContract> findContracts(String date);
	List<TContract> findContract(String string);
}
