package multithread.ejb;

import java.util.Map;

public interface EjbService<S, R> {
	public R send (S Request, Map<String, Object> context);
	public Object getInitialContext();
}
