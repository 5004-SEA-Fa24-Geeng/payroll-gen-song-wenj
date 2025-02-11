package student;

/**
 * The IPayStub interface defines the methods that must be implemented by any class representing a pay stub.
 * This interface includes methods for retrieving pay stub details and converting pay stub data to a CSV format.
 */
public interface IPayStub {

    /**
     * Gets the name of the employee associated with the pay stub.
     * @return The employee's name.
     */
    String getEmployeeName();

    /**
     * Gets the net pay of the employee.
     * @return The employee's net pay.
     */
    double getNetPay();

    /**
     * Gets the taxes paid by the employee.
     * @return The taxes paid by the employee.
     */
    double getTaxes();

    /**
     * Gets the year-to-date earnings of the employee.
     * @return The employee's year-to-date earnings.
     */
    double getYtdEarnings();

    /**
     * Gets the year-to-date taxes paid by the employee.
     * @return The employee's year-to-date taxes paid.
     */
    double getYtdTaxesPaid();

    /**
     * Converts the pay stub data to a CSV format.
     * @return A string representing the pay stub data in CSV format.
     */
    String toCSV();
}
