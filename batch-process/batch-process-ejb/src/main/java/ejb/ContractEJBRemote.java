package ejb;

import java.util.List;

import model.ContractDTO;
import model.ContractResponseDTO;

public interface ContractEJBRemote {
	public List<ContractResponseDTO> create(List<ContractDTO> list);
	public ContractResponseDTO create(ContractDTO contractDTO);
	public String test();
}
