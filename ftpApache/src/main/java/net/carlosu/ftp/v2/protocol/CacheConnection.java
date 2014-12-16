package net.carlosu.ftp.v2.protocol;

import java.util.HashMap;

import net.carlosu.ftp.customer.Customer;

public class CacheConnection {
	private static final HashMap<Long, FTPConnection<?>> cacheMap = new HashMap<>();

	public static FTPConnection<?> getCache(Customer customer) {
		FTPConnection<?> o = cacheMap.get(customer.getId());
		if (o == null) {
			o = FTPFactory.getInstance().getConnection(customer, false);
			cacheMap.put(customer.getId(), (FTPConnection<?>) o);
		}
		return o;
	}
}
