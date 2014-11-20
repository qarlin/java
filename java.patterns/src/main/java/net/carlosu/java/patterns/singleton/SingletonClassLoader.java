package net.carlosu.java.patterns.singleton;

//Problem not lazy initialization, initialized before any clients call
public class SingletonClassLoader {
	private static final SingletonClassLoader INSTANCE = new SingletonClassLoader();

	private SingletonClassLoader() {
	}

	public static SingletonClassLoader getInstance() {
		return INSTANCE;
	}

	public void show() {
		System.out.println("Singleon using static initialization in Java");
	}

}
