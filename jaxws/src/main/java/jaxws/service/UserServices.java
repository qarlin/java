package jaxws.service;

import javax.ejb.Stateful;

@Stateful
public class UserServices {

	public String sayHello() {
		return "hello";
	}

}
