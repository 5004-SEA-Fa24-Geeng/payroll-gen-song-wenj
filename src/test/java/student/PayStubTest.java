package student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PayStubTest {

    private SalaryEmployee salaryEmployee;
    private PayStub payStub;

    @BeforeEach
    void setUp() {
        salaryEmployee = new SalaryEmployee("John Doe", "12", 110000.0, 10000.0,
                0.00, 0.00);

        double netPay = 77350.00;
        double taxes = 22650.00;
        double ytdEarnings = 77350.00;
        double ytdTaxesPaid = 22650.00;

        payStub = new PayStub(salaryEmployee, netPay, taxes, ytdEarnings, ytdTaxesPaid);
    }

    @Test
    void getEmployeeName() {
        assertEquals("John Doe", payStub.getEmployeeName());
    }

    @Test
    void getNetPay() {
        assertEquals(77350.00, payStub.getNetPay());
    }

    @Test
    void getTaxes() {
        assertEquals(22650.00, payStub.getTaxes());
    }

    @Test
    void getYtdEarnings() {
        assertEquals(77350.00, payStub.getYtdEarnings());
    }

    @Test
    void getYtdTaxesPaid() {
        assertEquals(22650.00, payStub.getYtdTaxesPaid());
    }

    @Test
    void toCSV() {
        String expectedCSV = "John Doe,77350.00, 22650.00, 77350.00, 22650.00";
        assertEquals(expectedCSV, payStub.toCSV());
    }
}