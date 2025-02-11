package student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class HourlyEmployeeTest {

    private HourlyEmployee hourlyEmployee;

    @BeforeEach
    void setUp() {
        hourlyEmployee = new HourlyEmployee("John Doe", "12", 20.0, 0.0,
                0.00, 0.00);
    }

    @Test
    void testConstructor() {
        assertEquals("HOURLY", hourlyEmployee.getEmployeeType());
        assertEquals("John Doe", hourlyEmployee.getName());
        assertEquals("12", hourlyEmployee.getID());
        assertEquals(20.0, hourlyEmployee.getPayRate());
        assertEquals(0.0, hourlyEmployee.getPretaxDeductions());
        assertEquals(0.00, hourlyEmployee.getYTDEarnings());
        assertEquals(0.00, hourlyEmployee.getYTDTaxesPaid());
    }

    @Test
    void testCalculateGrossPay() {
        BigDecimal grossPay = hourlyEmployee.calculateGrossPay(60)
                .setScale(2, BigDecimal.ROUND_HALF_EVEN);;
        BigDecimal expectedGrossPay = BigDecimal.valueOf(20.0).multiply(BigDecimal.valueOf(40))
                .add(BigDecimal.valueOf(20.0).multiply(BigDecimal.valueOf(20)).multiply(BigDecimal.valueOf(1.5)))
                .setScale(2, BigDecimal.ROUND_HALF_EVEN);
        assertEquals(expectedGrossPay, grossPay);
    }

    @Test
    void getEmployeeType() {
        assertEquals("HOURLY", hourlyEmployee.getEmployeeType());
    }
}