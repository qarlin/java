package net.cts.model.enums;

public enum Difficulty {
	
	JUNIOR ("J"),
	INTERMEDIATE ("I"),
	SENIOR ("S");
	
	private final String id;

	private Difficulty(String id) {
		this.id = id;
	}

	public String getDifficulty() {
		return id;
	}

	public static Difficulty fromId(String id) {
		if ( id.equals("J")){
			return JUNIOR;
		}
		if ( id.equals("I")){ 
			return INTERMEDIATE;
		}
		if ( id.equals("S")){
			return SENIOR;
		}
		throw new IllegalArgumentException();
	}
}
