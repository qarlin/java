package jaxws.service.customer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso(Customer.class)
public class Customers extends ArrayList<Customer> {

	private static final long serialVersionUID = 1L;

	// ======================================
	// = Constructors =
	// ======================================
	public Customers() {
		super();
	}

	public Customers(Collection<? extends Customer> c) {
		super(c);
	}

	// ======================================
	// = Getters & Setters =
	// ======================================
	@XmlElement(name = "customer")
	public List<Customer> getCustomers() {
		return this;
	}

	public void setBooks(List<Customer> customers) {
		this.addAll(customers);
	}
}