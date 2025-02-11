package student;

import java.math.BigDecimal;

/**
 * The Employee class is an abstract class that implements the IEmployee interface.
 * It provides common functionality for different types of employees, including calculating
 * payroll details such as net pay, taxes, year-to-date earnings, and year-to-date taxes paid.
 */
public abstract class Employee implements IEmployee {

    /**
     * The type of the employee.
     */
    private String employeeType;

    /**
     * The name of the employee.
     */
    private String name;

    /**
     * The ID of the employee.
     */
    private String id;

    /**
     * The pay rate of the employee.
     */
    private double payRate;

    /**
     * The year-to-date earnings of the employee.
     */
    private double ytdEarnings;

    /**
     * The year-to-date taxes paid of the employee.
     */
    private double ytdTaxesPaid;

    /**
     * The pre-tax dedections of the employee.
     */
    private double pretaxDeductions;

    /**
     * The tax rate.
     */
    private double taxRate = 0.2265;

    /**
     * The pay stub for the employee.
     */
    private PayStub payStub;

    /**
     * Constructs an Employee instance with the specified details.
     * @param employeeType The type of the employee.
     * @param name The name of the employee.
     * @param id The ID of the employee.
     * @param payRate The pay rate of the employee.
     * @param pretaxDeductions The pre-tax deductions of the employee.
     * @param ytdEarnings The year-to-date earnings of the employee.
     * @param ytdTaxesPaid The year-to-date taxes paid by the employee.
     */
    public Employee(String employeeType, String name, String id, double payRate,
                    double ytdEarnings, double ytdTaxesPaid, double pretaxDeductions) {
        this.employeeType = employeeType;
        this.name = name;
        this.id = id;
        this.payRate = payRate;
        this.ytdEarnings = ytdEarnings;
        this.ytdTaxesPaid = ytdTaxesPaid;
        this.pretaxDeductions = pretaxDeductions;
    }

    /**
     * Calculates the gross pay for the employee based on hours worked.
     * @param hoursWorked The number of hours worked by the employee.
     * @return The gross pay.
     */
    protected abstract BigDecimal calculateGrossPay(double hoursWorked);

    /**
     * Calculates the net pay for the employee based on hours worked.
     * @param hoursWorked The number of hours worked by the employee.
     * @return The net pay.
     */
    public BigDecimal calculateNetPay(double hoursWorked) {
        BigDecimal netPay;
        BigDecimal grossPay = calculateGrossPay(hoursWorked);

        netPay = (grossPay.subtract(BigDecimal.valueOf(pretaxDeductions)))
                .multiply(BigDecimal.valueOf(1).subtract(BigDecimal.valueOf(taxRate)));

        return netPay;
    }

    /**
     * Calculates the taxes for the employee based on hours worked.
     * @param hoursWorked The number of hours worked by the employee.
     * @return The taxes.
     */
    public BigDecimal calculateTaxes(double hoursWorked) {
        BigDecimal taxes;
        BigDecimal grossPay = calculateGrossPay(hoursWorked);

        taxes = (grossPay.subtract(BigDecimal.valueOf(pretaxDeductions))).multiply(BigDecimal.valueOf(taxRate));

        return taxes;
    }

    /**
     * Calculates the year-to-date earnings for the employee based on hours worked.
     * @param hoursWorked The number of hours worked by the employee.
     * @return The year-to-date earnings.
     */
    public BigDecimal calculateYTDEarnings(double hoursWorked) {
        BigDecimal netPay = calculateNetPay(hoursWorked);

        BigDecimal ytdEarnings = BigDecimal.valueOf(this.ytdEarnings).add(netPay);

        return ytdEarnings;
    }

    /**
     * Calculates the year-to-date taxes paid by the employee based on hours worked.
     * @param hoursWorked The number of hours worked by the employee.
     * @return The year-to-date taxes paid.
     */
    public BigDecimal calculateYTDTaxesPaid(double hoursWorked) {
        BigDecimal taxes = calculateTaxes(hoursWorked);

        BigDecimal ytdTaxesPaid = BigDecimal.valueOf(this.ytdTaxesPaid).add(taxes);

        return ytdTaxesPaid;
    }

    /**
     * Gets the type of the employee.
     * @return The employee's type.
     */
    @Override
    public String getEmployeeType() {
        return employeeType;
    }

    /**
     * Gets the name of the employee.
     * @return The employee's name.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Gets the ID of the employee.
     * @return The employee's ID.
     */
    @Override
    public String getID() {
        return id;
    }

    /**
     * Gets the pay rate of the employee.
     * @return The employee's pay rate.
     */
    @Override
    public double getPayRate() {
        return payRate;
    }

    /**
     * Gets the pre-tax deductions of the employee.
     * @return The employee's pre-tax deductions.
     */
    @Override
    public double getPretaxDeductions() {
        return pretaxDeductions;
    }

    /**
     * Gets the year-to-date earnings of the employee.
     * @return The employee's year-to-date earnings.
     */
    @Override
    public double getYTDEarnings() {
        return ytdEarnings;
    }

    /**
     * Sets the year-to-date earnings by the employee.
     * @param ytdEarnings The employee's year-to-date earnings.
     */
    public void setYTDEarnings(double ytdEarnings) {
        this.ytdEarnings = ytdEarnings;
    }

    /**
     * Sets the year-to-date taxes paid by the employee.
     * @return The year-to-date taxes paid to set.
     */
    @Override
    public double getYTDTaxesPaid() {
        return ytdTaxesPaid;
    }

    /**
     * Sets the year-to-date taxes paid by the employee.
     * @param ytdTaxesPaid The employee's year-to-date taxes paid.
     */
    public void setYTDTaxesPaid(double ytdTaxesPaid) {
        this.ytdTaxesPaid = ytdTaxesPaid;
    }

    /**
     * Gets the pay stub associated with the employee.
     * @return The pay stub.
     */
    public PayStub getPayStub() {
        return payStub;
    }

    /**
     * Updates the pay stub of the employee based on hours worked.
     * @param hoursWorked The number of hours worked.
     */
    private void updatePayStub(double hoursWorked) {
        BigDecimal netPay = calculateNetPay(hoursWorked);
        BigDecimal taxes = calculateTaxes(hoursWorked);
        BigDecimal updatedYTDEarnings = calculateYTDEarnings(hoursWorked);
        BigDecimal updatedYTDTaxesPaid = calculateYTDTaxesPaid(hoursWorked);

        setYTDEarnings(updatedYTDEarnings.doubleValue());
        setYTDTaxesPaid(updatedYTDTaxesPaid.doubleValue());

        this.payStub = new PayStub(this, netPay.doubleValue(), taxes.doubleValue(),
                updatedYTDEarnings.doubleValue(), updatedYTDTaxesPaid.doubleValue());
    }

    /**
     * Processes the payroll for the employee based on hours worked.
     * @param hoursWorked The number of hours worked.
     */
    public void processPayroll(double hoursWorked) {
        updatePayStub(hoursWorked);
    }

    /**
     * Converts the employee's data to a CSV format.
     * @return A string representing the employee's data in CSV format.
     */
    @Override
    public String toCSV() {
        return String.join(",",
                getEmployeeType(),
                getName(),
                getID(),
                String.format("%.1f", getPayRate()),
                String.format("%.1f", getPretaxDeductions()),
                String.format("%.2f", getYTDEarnings()),
                String.format("%.2f", getYTDTaxesPaid())
        );
    }
}
