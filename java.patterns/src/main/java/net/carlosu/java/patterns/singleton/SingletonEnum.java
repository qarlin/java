package net.carlosu.java.patterns.singleton;

public enum SingletonEnum {
	INSTANCE;
	 
    public void show(){
        System.out.println("Singleton using Enum in Java");
    }
}
