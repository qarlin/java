package net.carlosu.java.patterns.singleton;

public class SingletonLazyInit {

	private static SingletonLazyInit instance = null;

	protected SingletonLazyInit() {
	}

	// Lazy Initialization (If required then only)
	public static SingletonLazyInit getInstance() {
		if (instance == null) {
			// Thread Safe. Might be costly operation in some case
			synchronized (SingletonLazyInit.class) {
				if (instance == null) {
					instance = new SingletonLazyInit();
					instance.initialize();
				}
			}
		}
		return instance;
	}

	private void initialize() {
		// TODO Auto-generated method stub
		
	}
}
