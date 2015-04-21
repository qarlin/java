package ejbprocess.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import model.Contract;
import model.ContractDTO;
import model.ContractResponseDTO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import ejb.ContractEJBRemote;
import ejbprocess.dao.ContractDAO;
import ejbprocess.ejbremote.EJBService;
import ejbprocess.service.BatchService;
import ejbprocess.transform.TransformData;

@Component("CreateService")
public class CreateService implements BatchService {
	private static final Logger logger = LogManager.getLogger(CreateService.class.getName());
	
	private static String BEAN = "batch-process-ejb/ContractEJB!ejb.ContractEJBRemote";
	
	private ContractDAO contractDAO;
	@Autowired
	private TransformData<Contract, ContractDTO> transfomrData;
	@Autowired
	@Qualifier("EJBRemoteService")
	private EJBService<ContractEJBRemote> ejbService;
	@Autowired
	@Qualifier("ContractRemote")
	private ContractEJBRemote remote;
	
	@Override
	public void execute(Map<String, Object> context) {
		Date date = (Date) context.get("DATE");
		
		// Loading Data
		List<Contract> list = contractDAO.getInstructions(date);
		logger.debug("Get Instructions for date {}, Instructions: {} ", date, list.size());

		// Transform Data
		List<ContractDTO> transformed = new ArrayList<ContractDTO>();
		for (Contract contract : list) {
			transformed.add(transfomrData.transform(contract, context));	
		}
		
		// Send EJB
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		List<Future<ContractResponseDTO>> futures = new ArrayList<Future<ContractResponseDTO>>(transformed.size());
		
		for (final ContractDTO contractDTO : transformed) {
			Future<ContractResponseDTO> future = executorService.submit(new Callable<ContractResponseDTO>() {

				@Override
				public ContractResponseDTO call() throws Exception {
					ContractEJBRemote remote = ejbService.getBean(BEAN);
					logger.debug("Context: {} - Bean: {} ", ejbService.getContext(), remote);
					ContractResponseDTO response = remote.create(contractDTO);
					return response;
				}
			});
			futures.add(future);
			
		}
		
		executorService.shutdown();
		while(!executorService.isTerminated()){}
		
		
		// Transform Data
		
		// Save Data
		
	}
	
	@Autowired
	public void setContractDAO(ContractDAO contractDAO){
		this.contractDAO = contractDAO;
	}

}
