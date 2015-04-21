package net.carlosu.wildfly.control;

public class NotEnoughMoneyException extends Throwable {
	private static final long serialVersionUID = -5513509524375298750L;

	public NotEnoughMoneyException(String string) {
		super(string);
	}

}
