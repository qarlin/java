package mockito;

import java.math.BigDecimal;

public class TaxDao {
	public BigDecimal getTaxPercentageForYear(int year) {
		return new BigDecimal(4.5);
	}
}
