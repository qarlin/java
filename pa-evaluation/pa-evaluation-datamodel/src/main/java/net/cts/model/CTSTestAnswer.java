package net.cts.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import net.cts.model.question.Answer;
import net.cts.model.question.Question;

@Entity
public class CTSTestAnswer extends AbstractEntity<Long>{

	@ManyToOne
	@JoinColumn(name="question_id")
	private Question question;
	@OneToOne
	private Answer answer;
	
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public Answer getAnswer() {
		return answer;
	}
	public void setAnswer(Answer answer) {
		this.answer = answer;
	}
}
