package net.carlosu.java.patterns.singleton;

public class SingletonLazyInit2 {

		private static SingletonLazyInit2 instance = null;
		private static Object syncObj = new Object();

		protected SingletonLazyInit2() {
		}

		// Lazy Initialization (If required then only)
		public static SingletonLazyInit2 getInstance() {
			if (instance == null) {
				// Thread Safe. Might be costly operation in some case
				synchronized (syncObj) {
					if (instance == null) {
						instance = new SingletonLazyInit2();
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
