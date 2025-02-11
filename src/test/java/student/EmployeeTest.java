package student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    private HourlyEmployee employee;

    @BeforeEach
    public void setUp() {
        employee = new HourlyEmployee("Jane Doe", "10", 20.0, 0.0, 10000.00, 800.00);
    }

    @Test
    void testCalculateNetPay() {
        double hoursWorked = 40.0;
        BigDecimal expectedNetPay = BigDecimal.valueOf(20.0 * 40.0 - 0.0).multiply(BigDecimal.valueOf(1 - 0.2265))
                .setScale(2, BigDecimal.ROUND_HALF_EVEN);
        assertEquals(expectedNetPay, employee.calculateNetPay(hoursWorked).setScale(2, BigDecimal.ROUND_HALF_EVEN));
    }

    @Test
    void testCalculateTaxes() {
        double hoursWorked = 40.0;
        BigDecimal expectedTaxes = BigDecimal.valueOf(20.0 * 40.0 - 0.0).multiply(BigDecimal.valueOf(0.2265))
                .setScale(2, BigDecimal.ROUND_HALF_EVEN);
        assertEquals(expectedTaxes, employee.calculateTaxes(hoursWorked).setScale(2, BigDecimal.ROUND_HALF_EVEN));
    }

    @Test
    void testCalculateYTDEarnings() {
        double hoursWorked = 40.0;
        BigDecimal netPay = employee.calculateNetPay(hoursWorked);
        BigDecimal expectedYTDEarnings = BigDecimal.valueOf(10000.00).add(netPay);
        assertEquals(expectedYTDEarnings, employee.calculateYTDEarnings(hoursWorked));
    }

    @Test
    void testCalculateYTDTaxesPaid() {
        double hoursWorked = 40.0;
        BigDecimal taxes = employee.calculateTaxes(hoursWorked);
        BigDecimal expectedYTDTaxesPaid = BigDecimal.valueOf(800.00).add(taxes);
        assertEquals(expectedYTDTaxesPaid, employee.calculateYTDTaxesPaid(hoursWorked));
    }

    @Test
    void testProcessPayroll() {
        double hoursWorked = 40.0;
        employee.processPayroll(hoursWorked);

        BigDecimal expectedNetPay = BigDecimal.valueOf(20.0 * 40.0 - 0.0).multiply(BigDecimal.valueOf(1 - 0.2265))
                .setScale(2, BigDecimal.ROUND_HALF_EVEN);
        BigDecimal expectedTaxes = BigDecimal.valueOf(20.0 * 40.0 - 0.0).multiply(BigDecimal.valueOf(0.2265))
                .setScale(2, BigDecimal.ROUND_HALF_EVEN);
        BigDecimal expectedYTDEarnings = BigDecimal.valueOf(10000.00).add(expectedNetPay);
        BigDecimal expectedYTDTaxesPaid = BigDecimal.valueOf(800.00).add(expectedTaxes);

        assertEquals(expectedNetPay.doubleValue(), employee.getPayStub().getNetPay());
        assertEquals(expectedTaxes.doubleValue(), employee.getPayStub().getTaxes());
        assertEquals(expectedYTDEarnings.doubleValue(), employee.getYTDEarnings());
        assertEquals(expectedYTDTaxesPaid.doubleValue(), employee.getYTDTaxesPaid());
    }

    @Test
    void testToCSV() {
        String expectedCSV = "HOURLY,Jane Doe,10,20.0,0.0,10000.00,800.00";
        assertEquals(expectedCSV, employee.toCSV());
    }
}