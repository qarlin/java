package com.hatumruna.taskmanager.domain.addons;

import com.hatumruna.taskmanager.domain.referencial.ViewCategory;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-11-24T14:40:20.044-0500")
@StaticMetamodel(ViewAtributes.class)
public class ViewAtributes_ {
	public static volatile SingularAttribute<ViewAtributes, Long> uid;
	public static volatile SingularAttribute<ViewAtributes, ViewCategory> category;
	public static volatile SingularAttribute<ViewAtributes, Integer> stars;
}
