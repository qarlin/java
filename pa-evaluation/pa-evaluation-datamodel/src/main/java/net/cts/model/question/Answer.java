package net.cts.model.question;

import javax.persistence.Entity;

import net.cts.model.AbstractEntity;

@Entity
public class Answer extends AbstractEntity<Long>{
	private int score;

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
}
