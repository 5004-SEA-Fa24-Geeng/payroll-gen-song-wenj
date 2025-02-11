package student;

/**
 * The IEmployee interface defines the methods that must be implemented by any class representing an employee.
 * This interface includes methods for retrieving employee details, calculating earnings and taxes,
 * and converting employee data to a CSV format.
 */
public interface IEmployee {

    /**
     * Gets the name of the employee.
     * @return The employee's name.
     */
    String getName();

    /**
     * Gets the ID of the employee.
     * @return The employee's ID.
     */
    String getID();

    /**
     * Gets the pay rate of the employee.
     * @return The employee's pay rate.
     */
    double getPayRate();

    /**
     * Gets the type of the employee.
     * @return The employee's type.
     */
    String getEmployeeType();

    /**
     * Gets the year-to-date earnings of the employee.
     * @return The employee's year-to-date earnings.
     */
    double getYTDEarnings();

    /**
     * Gets the year-to-date taxes paid by the employee.
     * @return The employee's year-to-date taxes paid.
     */
    double getYTDTaxesPaid();

    /**
     * Gets the pre-tax deductions of the employee.
     * @return The employee's pre-tax deductions.
     */
    double getPretaxDeductions();

    /**
     * Converts the employee's data to a CSV format.
     * @return A string representing the employee's data in CSV format.
     */
    String toCSV();
}
