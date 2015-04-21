package ejb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import model.ContractDTO;
import model.ContractResponseDTO;

@Stateless
@Remote(ContractEJBRemote.class)
public class ContractEJB implements ContractEJBRemote{

	@Override
	public List<ContractResponseDTO> create(List<ContractDTO> list) {
		try {
			Random rand = new Random();
			int randomNum = rand.nextInt((5000) + 1);
			Thread.sleep(randomNum);
			
			List<ContractResponseDTO> responses = new ArrayList<ContractResponseDTO>();
			for (ContractDTO contractDTO : list) {
				ContractResponseDTO dto = new ContractResponseDTO();
				dto.setId(contractDTO.getId());
				dto.setName(contractDTO.getName() + " Response");
				responses.add(dto);
			}
			return responses;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	@Override
	public ContractResponseDTO create(ContractDTO contractDTO) {
		try {
			Random rand = new Random();
			int randomNum = rand.nextInt((5000) + 1);
			Thread.sleep(randomNum);
			
			ContractResponseDTO dto = new ContractResponseDTO();
			dto.setId(contractDTO.getId());
			dto.setName(contractDTO.getName() + " Response");

			return dto;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String test() {
		return "Done";
	}

}
