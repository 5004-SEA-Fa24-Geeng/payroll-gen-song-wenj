package student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

class SalaryEmployeeTest {

    private SalaryEmployee salaryEmployee;

    @BeforeEach
    void setUp() {
        salaryEmployee = new SalaryEmployee("John Doe", "12", 110000.0, 10000.0,
                0.00, 0.00);
    }

    @Test
    void testConstructor() {
        assertEquals("SALARY", salaryEmployee.getEmployeeType());
        assertEquals("John Doe", salaryEmployee.getName());
        assertEquals("12", salaryEmployee.getID());
        assertEquals(110000.0, salaryEmployee.getPayRate());
        assertEquals(10000.0, salaryEmployee.getPretaxDeductions());
        assertEquals(0.00, salaryEmployee.getYTDEarnings());
        assertEquals(0.00, salaryEmployee.getYTDTaxesPaid());
    }

    @Test
    void testCalculateGrossPay() {
        BigDecimal grossPay = salaryEmployee.calculateGrossPay(60);
        BigDecimal expectedGrossPay = BigDecimal.valueOf(110000.0).divide(BigDecimal.valueOf(24),
                2, RoundingMode.HALF_EVEN);
        assertEquals(expectedGrossPay, grossPay);
    }

    @Test
    void getEmployeeType() {
        assertEquals("SALARY", salaryEmployee.getEmployeeType());
    }
}