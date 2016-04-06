package net.cts.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

import net.cts.model.enums.Difficulty;

@Entity
public class ExamConfiguration extends AbstractEntity<Long>{
	@OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
	private Library library;
	@Enumerated
	private Difficulty difficulty;
	private int totalQuestions;
	
	public Library getLibrary() {
		return library;
	}
	public void setLibrary(Library library) {
		this.library = library;
	}
	public Difficulty getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}
	public int getTotalQuestions() {
		return totalQuestions;
	}
	public void setTotalQuestions(int totalQuestions) {
		this.totalQuestions = totalQuestions;
	}
}
