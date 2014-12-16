package net.carlosu.ftp.protocol;

import net.carlosu.ftp.BusinessException;

public interface ILoadProtocol extends IProtocol {
	public String[] getFileNames() throws BusinessException;
	public void downloadFiles(String localFolder) throws BusinessException;

}
