package ejbprocess.transform;

import java.util.Map;

public interface TransformData<S, R> {
	
	public R transform(S s, Map<String, Object> context);
	
}
