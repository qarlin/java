package net.carlosu.spring.boot.jpa.observer.domain;

import javax.persistence.Entity;

@Entity
public class User extends AbstractEntity<Long>{
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
}
