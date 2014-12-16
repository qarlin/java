package net.carlosu.ftp.v2.protocol;

import java.util.Enumeration;
import java.util.Hashtable;

public class FTPPool<T> extends FTPConnection<T>{
	private long expirationTime;

	private Hashtable<T, Long> locked, unlocked;

	public FTPPool() {
		expirationTime = 60000; // 60 seconds
		locked = new Hashtable<T, Long>();
		unlocked = new Hashtable<T, Long>();
	}
	
	@Override
	public synchronized T checkOut() {
		long now = System.currentTimeMillis();
		T t;
		if (unlocked.size() > 0) {
			Enumeration<T> e = unlocked.keys();
			while (e.hasMoreElements()) {
				t = e.nextElement();
				if ((now - unlocked.get(t)) > expirationTime) {
					// object has expired
					unlocked.remove(t);
					conn.expire(t);
					t = null;
				} else {
					if (conn.validate(t)) {
						unlocked.remove(t);
						locked.put(t, now);
						return (t);
					} else {
						// object failed validation
						unlocked.remove(t);
						conn.expire(t);
						t = null;
					}
				}
			}
		}
		// no objects available, create a new one
		t = conn.create(this.ftpAddress);
		locked.put(t, now);
		return (t);
	}

	@Override
	public synchronized void checkIn(T t) {
		locked.remove(t);
		unlocked.put(t, System.currentTimeMillis());
	}
}