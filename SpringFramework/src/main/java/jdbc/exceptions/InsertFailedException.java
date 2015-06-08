package jdbc.exceptions;

import org.springframework.dao.DataAccessException;

public class InsertFailedException extends DataAccessException {
	private static final long serialVersionUID = 1092445669113881513L;

	public InsertFailedException(String msg) {
		super(msg);
	}
}
