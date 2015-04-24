package mockito;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class EmployeeDAO {
	public BigDecimal getAnnualSalary(long employeeId) {
        // conncetTODB
        // run select for employeeId;
        return new BigDecimal(70000);
    }
}
