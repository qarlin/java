package net.cts.model.question;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import net.cts.model.AbstractEntity;
import net.cts.model.Library;
import net.cts.model.User;
import net.cts.model.enums.Difficulty;

@Entity
public abstract class Question extends AbstractEntity<Long>{
	private String questionText;
	private Long timeLimit;
	private Integer score;
	
	@Enumerated
	private Difficulty difficulty;
	@OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
	private Library library;
	
	@ManyToOne
	private User userCreated;
	private Date dateCreated;
	
	public String getQuestionText() {
		return questionText;
	}
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
	public Long getTimeLimit() {
		return timeLimit;
	}
	public void setTimeLimit(Long timeLimit) {
		this.timeLimit = timeLimit;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Difficulty getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}
	public Library getLibrary() {
		return library;
	}
	public void setLibrary(Library library) {
		this.library = library;
	}
	public User getUserCreated() {
		return userCreated;
	}
	public void setUserCreated(User userCreated) {
		this.userCreated = userCreated;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

}
