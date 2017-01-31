package com.positions;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class PositionMapper implements FieldSetMapper<Position>{
	@Override
	public Position mapFieldSet(FieldSet fieldSet) throws BindException {
		Position position = new Position();
		
		position.setSecurityId(fieldSet.readString(0));
		position.setClientId(fieldSet.readString(1));
		position.setPositionId(fieldSet.readString(2));
		position.setQuantity(fieldSet.readDouble(3));
	
		return position;
	}
}
