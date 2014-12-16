package net.carlosu.ftp.protocol;

import java.util.Collection;
import java.util.HashMap;

import net.carlosu.ftp.BusinessException;

public interface IDistributeProtocol extends IProtocol {
	public void uploadFiles(Collection<String> files) throws BusinessException;
	public void uploadContent(HashMap<String, String> content) throws BusinessException;
}
