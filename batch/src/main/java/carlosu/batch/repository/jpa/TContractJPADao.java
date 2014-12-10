package carlosu.batch.repository.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import carlosu.batch.domain.tcontract.TContract;
import carlosu.batch.repository.TContractDao;

@Repository
public class TContractJPADao extends AbstractDb2JPADao<TContract, Long> implements TContractDao{

	@SuppressWarnings("unchecked")
	public List<TContract> findAllContracts(){
		Query query = getEntityManager().createNamedQuery("findAllTContracts");
		List<Object[]> result = query.getResultList();
		List<TContract> contracts = new ArrayList<TContract>();
		for (Object[] object : result) {
			contracts.add((TContract) object[0]);
		}
		return contracts;
	}
}
