package net.carlosu.spring.boot.jpa.observer.domain;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name="Book.findByPrice",query="select name,author,price from Book b where b.price=?1")
public class Book extends AbstractEntity<Long>{
	private String name;
	private String author;
	private String price;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
}
