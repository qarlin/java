package com.hatumruna.taskmanager.domain.addons;

import com.hatumruna.taskmanager.domain.referencial.ViewCategory;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-05-14T12:35:00.969-0400")
@StaticMetamodel(ViewAtributes.class)
public class ViewAtributes_ {
	public static volatile SingularAttribute<ViewAtributes, Long> uid;
	public static volatile SingularAttribute<ViewAtributes, ViewCategory> category;
	public static volatile SingularAttribute<ViewAtributes, Integer> stars;
}
