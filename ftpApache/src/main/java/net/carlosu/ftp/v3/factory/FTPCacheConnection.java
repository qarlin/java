package net.carlosu.ftp.v3.factory;

import java.util.HashMap;

import net.carlosu.ftp.v3.connection.ObjectPool;

public class FTPCacheConnection {

	private static final HashMap<String, ObjectPool<?>> cacheMap = new HashMap<>();

	public static ObjectPool<?> getCache(String server, String port, String user) {
		ObjectPool<?> o = cacheMap.get(server+port+user);
		if (o == null) {
			o = FTPFactory.getInstance().newConnection(server, port, user);
			cacheMap.put(server+port+user, o);
		}
		return o;
	}
}
