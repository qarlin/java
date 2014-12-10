package carlosu.batch.services;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;

import carlosu.batch.domain.contract.Instruction;

@Service
public class InstructionServiceImpl implements InstructionService {
	@PersistenceContext(unitName="contractUnit")
	EntityManager entityManager;
	
	@Override
	public List<Instruction> findInstructions(Date bizDate) {
		TypedQuery<Instruction> query = entityManager.createNamedQuery("findInstructions", Instruction.class);
		query.setParameter("bizday", bizDate);
		return query.getResultList();
	}

}
