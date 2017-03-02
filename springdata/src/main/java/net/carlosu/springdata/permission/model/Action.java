package net.carlosu.springdata.permission.model;

import javax.persistence.Entity;

@Entity
public class Action extends AbstractEntity<Long> {
	private String actionName;

	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
}
