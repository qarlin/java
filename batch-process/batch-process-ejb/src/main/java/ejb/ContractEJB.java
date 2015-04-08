package ejb;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote(ContractEJBRemote.class)
public class ContractEJB implements ContractEJBRemote{

	@Override
	public String create(List<Object> list) {
		return "Done";
	}

}
