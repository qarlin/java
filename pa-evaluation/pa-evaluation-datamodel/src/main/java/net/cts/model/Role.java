package net.cts.model;

import javax.persistence.Entity;

@Entity
public class Role extends AbstractEntity<Long>{
	private String name;
	private boolean isAdmin;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
}
