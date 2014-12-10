package carlosu.batch.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import carlosu.batch.domain.tcontract.TContract;

@Service
public class ContractServiceImpl implements ContractService {
	@PersistenceContext(unitName="tcontractUnit")
	EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<TContract> findContracts(String date) {
		Query query = entityManager.createNamedQuery("findTContracts");
		query.setParameter("date", date);
		return mapTContracts(query.getResultList());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TContract> findContract(String externalId) {
		Query query = entityManager.createNamedQuery("findTContract");
		query.setParameter("externalId", externalId);
		return mapTContracts(query.getResultList());
	}
	
	private List<TContract> mapTContracts(List<Object[]> result){
		List<TContract> contracts = new ArrayList<TContract>();
		for (Object[] object : result) {
			contracts.add((TContract) object[0]);
		}
		return contracts;
	}
}
