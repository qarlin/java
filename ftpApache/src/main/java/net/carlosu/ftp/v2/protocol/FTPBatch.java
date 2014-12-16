package net.carlosu.ftp.v2.protocol;


public class FTPBatch<T> extends FTPConnection<T> {

	@Override
	public T checkOut() {
		return conn.create(ftpAddress);
	}

	@Override
	public void checkIn(T t) {
		conn.expire(t);
	}


}
