package model;

import java.io.Serializable;

public class ContractResponseDTO implements Serializable {
	private static final long serialVersionUID = -9135224529261410362L;

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
