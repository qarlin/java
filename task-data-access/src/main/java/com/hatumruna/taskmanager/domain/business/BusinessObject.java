package com.hatumruna.taskmanager.domain.business;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import com.hatumruna.taskmanager.domain.IDomainObject;
import com.hatumruna.taskmanager.domain.addons.ViewAtributes;
import com.hatumruna.taskmanager.domain.referencial.StatusType;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Indexed
public abstract class BusinessObject implements IDomainObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6469320869441756057L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long uid;
	@Field
	private Date creationDate;
	@ManyToOne
	@JoinColumn(name="STATUS_ID")
	private StatusType status;
	@OneToOne
	@PrimaryKeyJoinColumn
	private ViewAtributes viewAttribute;
	
	public StatusType getStatus() {
		return status;
	}
	public void setStatus(StatusType status) {
		this.status = status;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public ViewAtributes getViewAttribute() {
		return viewAttribute;
	}
	public void setViewAttribute(ViewAtributes viewAttribute) {
		this.viewAttribute = viewAttribute;
	}
}
