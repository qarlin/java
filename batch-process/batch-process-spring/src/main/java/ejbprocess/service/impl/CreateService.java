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

import org.apache.log4j.Logger;
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
	private static Logger logger = Logger.getLogger(CreateService.class);
	private static String BEAN = "batch-process-ejb/ContractEJB!ejb.ContractEJBRemote";
	
	private ContractDAO contractDAO;
	@Autowired
	private TransformData<Contract, ContractDTO> transfomrData;
	@Autowired
	@Qualifier("EJBRemoteService")
	private EJBService<ContractEJBRemote> ejbService;
	
	@Override
	public void execute(Map<String, Object> context) {
		Date date = (Date) context.get("DATE");
		
		// Loading Data
		List<Contract> list = contractDAO.getInstructions(date);
		logger.debug("Get Instructions for date " + date +  " Instructions: " + list.size());

		// Transform Data
		List<ContractDTO> transformed = new ArrayList<ContractDTO>();
		for (Contract contract : list) {
			transformed.add(transfomrData.transform(contract, context));	
		}
		
		// Send EJB
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		List<Future<ContractResponseDTO>> futures = new ArrayList<Future<ContractResponseDTO>>(transformed.size());
		
		for (final ContractDTO contractDTO : transformed) {
			futures.add(beanCalled(executorService, contractDTO));
		}
		
		executorService.shutdown();
		while(!executorService.isTerminated()){}
		
		logger.debug("Finished!");
		// Transform Data
		
		// Save Data
		
	}
	
	public Future<ContractResponseDTO> beanCalled(ExecutorService executorService, final ContractDTO contractDTO){
		Future<ContractResponseDTO> future = executorService.submit(new Callable<ContractResponseDTO>() {

			@Override
			public ContractResponseDTO call() throws Exception {
				ContractEJBRemote remote = ejbService.getBean(BEAN);
				logger.debug("Context: " + ejbService.getContext() + " - Bean: " + remote);
				ContractResponseDTO response = remote.create(contractDTO);
				return response;
			}
		});
		return future;
	}
	@Autowired
	public void setContractDAO(ContractDAO contractDAO){
		this.contractDAO = contractDAO;
	}

}
