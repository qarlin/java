package multithread.ejb.impl;

import java.util.Map;

import multithread.ejb.Client2EjbService;
import multithread.ejb.EjbService;

import org.springframework.stereotype.Service;

@Service("contract2EjbService")
public class Contract2EjbServiceImpl extends Client2EjbService implements EjbService<String, String>{
	
	@Override
	public String send(String Request, Map<String, Object> context) {
		return null;
	}

}
