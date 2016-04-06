package net.cts.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import net.cts.model.question.Question;

@Entity
public class Exam extends AbstractEntity<Long>{
	private String name;
	private String description;
	private Date dateCreated;
	@ManyToOne
	@JoinColumn(name="user_created")
	private User userCreated;
	@ManyToMany
	@JoinTable(name = "exam_question",
            joinColumns = @JoinColumn(name = "exam_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id"))
	private Set<Question> fixedQuestions;
	@OneToMany
	private Set<ExamConfiguration> configurations;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public User getUserCreated() {
		return userCreated;
	}
	public void setUserCreated(User userCreated) {
		this.userCreated = userCreated;
	}
	public Set<Question> getFixedQuestions() {
		return fixedQuestions;
	}
	public void setFixedQuestions(Set<Question> fixedQuestions) {
		this.fixedQuestions = fixedQuestions;
	}
	public Set<ExamConfiguration> getConfigurations() {
		return configurations;
	}
	public void setConfigurations(Set<ExamConfiguration> configurations) {
		this.configurations = configurations;
	}
}
