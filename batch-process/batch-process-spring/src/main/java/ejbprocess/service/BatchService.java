package ejbprocess.service;

import java.util.Map;

public interface BatchService {
	public void execute(Map<String, Object> context);
}
