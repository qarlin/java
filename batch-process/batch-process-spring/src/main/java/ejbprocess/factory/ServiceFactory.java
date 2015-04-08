package ejbprocess.factory;

import ejbprocess.service.BatchService;

public interface ServiceFactory {
	public BatchService getService(String service);
}
