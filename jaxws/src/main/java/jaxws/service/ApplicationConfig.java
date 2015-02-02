package jaxws.service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import jaxws.service.customer.CustomerRestService;

@ApplicationPath("rs")
public class ApplicationConfig extends Application {
	// ======================================
	// = Attributes =
	// ======================================
	private final Set<Class<?>> classes;

	// ======================================
	// = Constructors =
	// ======================================
	public ApplicationConfig() {
		HashSet<Class<?>> c = new HashSet<>();
		c.add(CustomerRestService.class);
		classes = Collections.unmodifiableSet(c);
	}

	// ======================================
	// = Getters & Setters =
	// ======================================
	@Override
	public Set<Class<?>> getClasses() {
		return classes;
	}
}
