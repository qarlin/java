package com.hatumruna.taskmanager.domain.referencial;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class StatusType implements IReferenceObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6602281719011820526L;
	@Id
	private String code;
	private String description;
	private String icon;
	private String color;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}

}
