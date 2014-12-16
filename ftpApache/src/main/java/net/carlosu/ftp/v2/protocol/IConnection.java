package net.carlosu.ftp.v2.protocol;

import net.carlosu.ftp.customer.FtpAddress;

public interface IConnection<T> {
	public abstract T create(FtpAddress ftpAddress);
	public abstract boolean validate(T o);
	public abstract void expire(T o);
}
