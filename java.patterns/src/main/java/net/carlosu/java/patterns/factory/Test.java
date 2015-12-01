package net.carlosu.java.patterns.factory;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

public class Test {
	public static void main(String[] args) {
		 
		   Weld weld = new Weld();
		   WeldContainer container = weld.initialize();
		   Client client = container.instance().select(Client.class).get();
		   client.doMessage();
		   weld.shutdown();
		 
		  }
}
