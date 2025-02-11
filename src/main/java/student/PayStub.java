package student;

/**
 * The PayStub class represents a pay stub for an employee. It implements the IPayStub interface
 * and includes details such as net pay, taxes, year-to-date earnings, and year-to-date taxes paid.
 */
public class PayStub implements IPayStub {

    /**
     * The employee associated with the pay stub.
     */
    private Employee employee;

    /**
     * The net pay of the employee.
     */
    private double netPay;

    /**
     * The taxes paid by the employee.
     */
    private double taxes;

    /**
     * The year-to-date earnings of the employee.
     */
    private double ytdEarnings;

    /**
     * The year-to-date taxes paid by the employee.
     */
    private double ytdTaxesPaid;

    /**
     * Constructs a PayStub object with the specified employee and payroll details.
     * @param employee The employee associated with the pay stub.
     * @param netPay The net pay of the employee.
     * @param taxes The taxes paid by the employee.
     * @param ytdEarnings The year-to-date earnings of the employee.
     * @param ytdTaxesPaid The year-to-date taxes paid by the employee.
     */
    public PayStub(Employee employee, double netPay, double taxes, double ytdEarnings, double ytdTaxesPaid) {
        this.employee = employee;
        this.netPay = netPay;
        this.taxes = taxes;
        this.ytdEarnings = ytdEarnings;
        this.ytdTaxesPaid = ytdTaxesPaid;
    }

    /**
     * Gets the name of the employee associated with the pay stub.
     * @return The employee's name.
     */
    @Override
    public String getEmployeeName() {
        return employee.getName();
    }

    /**
     * Gets the net pay of the employee.
     * @return The employee's net pay.
     */
    @Override
    public double getNetPay() {
        return netPay;
    }

    /**
     * Gets the taxes paid by the employee.
     * @return The taxes paid by the employee.
     */
    @Override
    public double getTaxes() {
        return taxes;
    }

    /**
     * Gets the year-to-date earnings of the employee.
     * @return The employee's year-to-date earnings.
     */
    @Override
    public double getYtdEarnings() {
        return ytdEarnings;
    }

    /**
     * Gets the year-to-date taxes paid by the employee.
     * @return The employee's year-to-date taxes paid.
     */
    @Override
    public double getYtdTaxesPaid() {
        return ytdTaxesPaid;
    }

    /**
     * Converts the pay stub data to a CSV format.
     * @return A string representing the pay stub data in CSV format.
     */
    @Override
    public String toCSV() {
        return String.join(",",
                getEmployeeName(),
                String.format("%.2f", getNetPay()),
                String.format("%.2f", getTaxes()),
                String.format("%.2f", getYtdEarnings()),
                String.format("%.2f", getYtdTaxesPaid())
        );
    }
}
