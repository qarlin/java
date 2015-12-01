package net.carlosu.java.patterns.factoryspring;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ProcessFactory{
	@Inject
    private Map<String, ProcessType> process;
	
	public ProcessType getProcess(String type){
		return process.get(type);
	}
}
