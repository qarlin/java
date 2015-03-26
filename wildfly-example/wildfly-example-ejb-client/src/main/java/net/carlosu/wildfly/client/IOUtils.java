package net.carlosu.wildfly.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IOUtils {
	
	public static String readLine(String s) {
		System.out.print(s);
		String returnval = null;
		try {
			BufferedReader bufRead = new BufferedReader(new InputStreamReader(
					System.in));
			returnval = bufRead.readLine();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return returnval;
	}

	public static void welcomeMessage() {
		System.out.println("Theater booking system");
		System.out.println("======================");
		System.out.println("Commands: book, list, money, quit");
	}

	public static int readInt(String s) {
		System.out.print(s);
		int returnval;
		try {
			BufferedReader bufRead = new BufferedReader(new InputStreamReader(System.in));
			returnval = Integer.valueOf(bufRead.readLine());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return returnval;
	}

}
