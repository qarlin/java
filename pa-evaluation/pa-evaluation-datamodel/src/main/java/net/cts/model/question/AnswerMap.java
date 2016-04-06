package net.cts.model.question;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import net.cts.model.AbstractEntity;

public class AnswerMap extends AbstractEntity<Long>{
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	private String textAnswers;
	private boolean rightAnswers;
	
	public String getTextAnswers() {
		return textAnswers;
	}
	public void setTextAnswers(String textAnswers) {
		this.textAnswers = textAnswers;
	}
	public boolean isRightAnswers() {
		return rightAnswers;
	}
	public void setRightAnswers(boolean rightAnswers) {
		this.rightAnswers = rightAnswers;
	}
}
