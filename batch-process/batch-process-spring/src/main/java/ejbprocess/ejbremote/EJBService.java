package ejbprocess.ejbremote;

import java.util.concurrent.ConcurrentHashMap;

import javax.naming.Context;
import javax.naming.NamingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("EJBRemoteService")
public class EJBService<S> {
	@Autowired
	@Qualifier("EJBService")
	private Context EJBService;
	
	private ConcurrentHashMap<String, Object> mEJBs = new ConcurrentHashMap<String, Object>();
	
	@SuppressWarnings("unchecked")
	public S getBean(String remoteJNDIName){
		try {
			if (mEJBs.get(remoteJNDIName) == null){
				mEJBs.put(remoteJNDIName, EJBService.lookup(remoteJNDIName));
			}
			return (S) mEJBs.get(remoteJNDIName) ;
		} catch (NamingException e){
			e.printStackTrace();
		}
		return null;
	}
	
	public Context getContext(){
		return EJBService;
	}
}
