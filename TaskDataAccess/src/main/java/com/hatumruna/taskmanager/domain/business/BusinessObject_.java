package com.hatumruna.taskmanager.domain.business;

import com.hatumruna.taskmanager.domain.addons.ViewAtributes;
import com.hatumruna.taskmanager.domain.referencial.StatusType;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-05-14T12:35:01.006-0400")
@StaticMetamodel(BusinessObject.class)
public class BusinessObject_ {
	public static volatile SingularAttribute<BusinessObject, Long> uid;
	public static volatile SingularAttribute<BusinessObject, Date> creationDate;
	public static volatile SingularAttribute<BusinessObject, StatusType> status;
	public static volatile SingularAttribute<BusinessObject, ViewAtributes> viewAttribute;
}
