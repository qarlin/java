package jdbc.exceptions;

import org.springframework.dao.DataAccessException;

public class DeleteFailedException extends DataAccessException {
	private static final long serialVersionUID = -2684119753202438638L;

	public DeleteFailedException(String msg) {
		super(msg);
	}
}
