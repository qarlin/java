package net.cts.model.question;

public class SingleOptionQuestion extends Question{
	private OptionAnswer answer;

	public OptionAnswer getAnswer() {
		return answer;
	}

	public void setAnswer(OptionAnswer answer) {
		this.answer = answer;
	}
}
