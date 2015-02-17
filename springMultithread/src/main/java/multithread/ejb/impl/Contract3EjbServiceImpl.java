package multithread.ejb.impl;

import java.util.Map;

import multithread.ejb.Client3EjbService;
import multithread.ejb.EjbService;

import org.springframework.stereotype.Service;

@Service("contract3EjbService")
public class Contract3EjbServiceImpl extends Client3EjbService implements EjbService<String, String>{
	
	@Override
	public String send(String Request, Map<String, Object> context) {
		return null;
	}

}
