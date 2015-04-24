package test.mockito;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.hamcrest.core.Is.is;

import java.math.BigDecimal;

import mockito.EmployeeDAO;
import mockito.SalaryService;
import mockito.TaxCalculator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SalaryServiceTest {

	private static final long UserId = 123l;

    @InjectMocks
    private SalaryService salaryService;

    @Mock
    private EmployeeDAO employeeDAO;

    @Mock
    private TaxCalculator taxCalculator;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMinimumSalary() {
        BigDecimal annualSalary = new BigDecimal(10000);
        when(employeeDAO.getAnnualSalary(UserId)).thenReturn(annualSalary);
        when(taxCalculator.calculateTaxes(annualSalary)).thenReturn(new BigDecimal(1000));
        BigDecimal actual = salaryService.getNetSalary(UserId);
        assertThat(actual.compareTo(new BigDecimal(10000)), is(0));
    }

    @Test
    public void testMaximumSalary() {
        BigDecimal annualSalary = new BigDecimal(80000);
        when(employeeDAO.getAnnualSalary(UserId)).thenReturn(annualSalary);
        when(taxCalculator.calculateTaxes(annualSalary)).thenReturn(new BigDecimal(8000));
        BigDecimal actual = salaryService.getNetSalary(UserId);
        assertThat(actual.compareTo(new BigDecimal(72000)), is(0));
    }

}
