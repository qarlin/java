package jdbc.exceptions;

import org.springframework.dao.DataAccessException;

public class UpdateFailedException extends DataAccessException {
	private static final long serialVersionUID = -1173061337698982428L;

	public UpdateFailedException(String msg) {
		super(msg);
	}
}
