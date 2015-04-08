package ejbprocess.transform.impl;

import java.util.Map;

import org.springframework.stereotype.Component;

import ejbprocess.model.Contract;
import ejbprocess.transform.TransformData;

@Component
public class TransformInstruction implements TransformData<Contract, Object> {

	@Override
	public Object transform(Contract s, Map<String, Object> context) {
		return "OK";
	}

}
