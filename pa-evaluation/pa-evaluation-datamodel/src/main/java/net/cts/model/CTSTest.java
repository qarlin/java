package net.cts.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class CTSTest extends AbstractEntity<Long>{

	@OneToOne
	private User user;
	@OneToMany
	private Set<CTSTestAnswer> answers;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Set<CTSTestAnswer> getAnswers() {
		return answers;
	}
	public void setAnswers(Set<CTSTestAnswer> answers) {
		this.answers = answers;
	}
}
