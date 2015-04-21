package model;

import java.io.Serializable;

public class Contract implements Serializable{
	private static final long serialVersionUID = 4145862024422515127L;

	private int id;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
