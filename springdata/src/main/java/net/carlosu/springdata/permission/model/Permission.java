package net.carlosu.springdata.permission.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Permission extends AbstractEntity<Long> {
	@OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
    private Action action;
	@OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
    private Resource resource;
	
	public Action getAction() {
		return action;
	}
	public void setAction(Action action) {
		this.action = action;
	}
	public Resource getResource() {
		return resource;
	}
	public void setResource(Resource resource) {
		this.resource = resource;
	}
}
