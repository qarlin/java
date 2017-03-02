package com.hatumruna.taskmanager.domain.addons;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.hatumruna.taskmanager.domain.IDomainObject;
import com.hatumruna.taskmanager.domain.referencial.ViewCategory;

@Entity
public class ViewAtributes implements IDomainObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3511312024052382043L;
	@Id
	private long uid;
	@ManyToOne
	@JoinColumn(name="CATEGORY_ID")
	private ViewCategory category;
	private int stars;
	
	public ViewCategory getCategory() {
		return category;
	}
	public void setCategory(ViewCategory category) {
		this.category = category;
	}
	public int getStars() {
		return stars;
	}
	public void setStars(int stars) {
		this.stars = stars;
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
}
