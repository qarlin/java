package model;

import java.io.Serializable;

public class ContractDTO implements Serializable{
	private static final long serialVersionUID = 1423361590978217747L;

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
