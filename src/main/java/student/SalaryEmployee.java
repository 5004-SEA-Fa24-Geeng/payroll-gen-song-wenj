package student;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * The SalaryEmployee class represents an employee who is paid a fixed salary.
 * It extends the Employee class and provides an implementation for calculating gross pay.
 */
public class SalaryEmployee extends Employee {

    /**
     * Constructs an SalaryEmployee object with the specified details.
     * @param name The name of the employee.
     * @param id The ID of the employee.
     * @param payRate The annual salary of the employee.
     * @param pretaxDeductions The pre-tax deductions of the employee.
     * @param ytdEarnings The year-to-date earnings of the employee.
     * @param ytdTaxesPaid The year-to-date taxes paid by the employee.
     */
    public SalaryEmployee(String name, String id, double payRate, double pretaxDeductions,
                          double ytdEarnings, double ytdTaxesPaid) {
        super(name, id, payRate, pretaxDeductions, ytdEarnings, ytdTaxesPaid);
    }

    /**
     * Calculates the gross pay of the salary employee based on hours worked.
     * The gross pay is calculated by dividing the annual salary by 24.
     * @param hoursWorked The number of hours worked.
     * @return The gross pay.
     */
    @Override
    protected BigDecimal calculateGrossPay(double hoursWorked) {
        BigDecimal annualRate = BigDecimal.valueOf(getPayRate());
        BigDecimal grossPay;

        grossPay = annualRate.divide(BigDecimal.valueOf(24), 2, RoundingMode.HALF_EVEN);

        return grossPay;
    }

    /**
     * Gets the employee type of the salary employee.
     * @return A string representing the employee type.
     */
    @Override
    public String getEmployeeType() {
        return "SALARY";
    }
}
