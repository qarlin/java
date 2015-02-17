package multithread.ejb.impl;

import java.util.Map;

import multithread.ejb.Client1EjbService;
import multithread.ejb.EjbService;

import org.springframework.stereotype.Service;

@Service("contract1EjbService")
public class Contract1EjbServiceImpl extends Client1EjbService implements EjbService<String, String>{
	
	@Override
	public String send(String Request, Map<String, Object> context) {
		return null;
	}
}
