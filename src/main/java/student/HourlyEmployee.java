package student;

import java.math.BigDecimal;

/**
 * The HourlyEmployee class represents an employee who is paid on an hourly basis.
 * It extends the Employee class and provides an implementation for calculating gross pay.
 */
public class HourlyEmployee extends Employee {

    /**
     * Constructs an HourlyEmployee object with the specified details.
     * @param name The name of the employee.
     * @param id The ID of the employee.
     * @param payRate The hourly pay rate of the employee.
     * @param pretaxDeductions The pre-tax deductions of the employee.
     * @param ytdEarnings The year-to-date earnings of the employee.
     * @param ytdTaxesPaid The year-to-date taxes paid by the employee.
     */
    public HourlyEmployee(String name, String id, double payRate, double pretaxDeductions,
                          double ytdEarnings, double ytdTaxesPaid) {
        super(name, id, payRate, pretaxDeductions, ytdEarnings, ytdTaxesPaid);
    }

    /**
     * Calculates the gross pay of the hourly employee based on hours worked.
     * @param hoursWorked The number of hours worked.
     * @return The gross pay.
     */
    @Override
    protected BigDecimal calculateGrossPay(double hoursWorked) {
        BigDecimal hourlyRate = BigDecimal.valueOf(getPayRate());
        BigDecimal grossPay;

        if (hoursWorked <= 40) {
            grossPay = hourlyRate.multiply(BigDecimal.valueOf(hoursWorked));
        } else {
            BigDecimal normalPay = hourlyRate.multiply(BigDecimal.valueOf(40));
            BigDecimal overtimePay = hourlyRate.multiply(BigDecimal.valueOf(hoursWorked)
                    .subtract(BigDecimal.valueOf(40))).multiply(BigDecimal.valueOf(1.5));
            grossPay = normalPay.add(overtimePay);
        }

        return grossPay;
    }

    /**
     * Gets the employee type of the hourly employee.
     * @return A string representing the employee type.
     */
    @Override
    public String getEmployeeType() {
        return "HOURLY";
    }
}
