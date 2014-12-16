package net.carlosu.ftp;
import java.util.Collection;
import java.util.HashMap;

import net.carlosu.ftp.connection.FTPApachePool;
import net.carlosu.ftp.customer.Customer;
import net.carlosu.ftp.protocol.IDistributeProtocol;
import net.carlosu.ftp.protocol.ILoadProtocol;
import net.carlosu.ftp.protocol.ProtocolType;

public class DistributeService {
	public void distributeDocuments(Collection<String> files, Customer customer, ProtocolType type){
		IDistributeProtocol protocol = ProtocolFactory.getDistributeProtocol(type);
		protocol.setAddress(customer.getAddress(type));
		try {
			protocol.uploadFiles(files);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	
	public void distributeDocuments(HashMap<String, String> files, Customer customer, ProtocolType type){
		IDistributeProtocol protocol = ProtocolFactory.getDistributeProtocol(type);
		protocol.setAddress(customer.getAddress(type));
		try {
			protocol.uploadContent(files);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	
	public String[] getFileNames(Customer customer, ProtocolType type){
		ILoadProtocol protocol = ProtocolFactory.getLoadProtocol(type);
		protocol.setAddress(customer.getAddress(type));
		try {
			return protocol.getFileNames();
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void downloadFiles(String localFolder, Customer customer, ProtocolType type){
		ILoadProtocol protocol = ProtocolFactory.getLoadProtocol(type);
		protocol.setAddress(customer.getAddress(type));
		try {
			protocol.downloadFiles(localFolder);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	
	HashMap<String, FTPApachePool> map = new HashMap<>();
	
	public void distributeDocumentsPool(Customer customer, Collection<String> files){
		FTPApachePool pool;
		if (map.get(customer.getCode()) != null)
			pool = map.get(customer.getCode());
		else {
			pool = new FTPApachePool(customer.getFtpConfiguration());
			map.put(customer.getCode(), pool);
		}
		IDistributeProtocol protocol = ProtocolFactory.getDistributeProtocol(pool);
		protocol.setAddress(customer.getAddress(ProtocolType.FTP));
		try {
			protocol.uploadFiles(files);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
}
