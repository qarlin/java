package net.cts.hibernate.converter;

import javax.persistence.AttributeConverter;

import net.cts.model.enums.Difficulty;

public class DifficultyConverter implements AttributeConverter<String, Difficulty> {

	public Difficulty convertToDatabaseColumn(String attribute){
		if ( attribute == null ) 
			return null;
		
		return Difficulty.fromId(attribute);
	}

	public String convertToEntityAttribute(Difficulty difficulty) {
		if ( difficulty == null ) 
			return null;

		return difficulty.getDifficulty();
	}
}
