package carlosu.batch.repository.jpa;

import org.springframework.stereotype.Repository;

import carlosu.batch.domain.tcontract.TContract;
import carlosu.batch.repository.TContractDao;

@Repository
public class TContractJPADao extends AbstractDb2JPADao<TContract, Long> implements TContractDao{
	
}
