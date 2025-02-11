package student;

/**
 * The ITimeCard interface defines the methods that must be implemented by any class representing a time card.
 * This interface includes methods for retrieving time card details such as the employee ID and hours worked.
 */
public interface ITimeCard {

    /**
     * Gets the ID of the employee associated with the time card.
     * @return The employee's ID.
     */
    String getEmployeeID();

    /**
     * Gets the number of hours worked by the employee.
     * @return The hours worked by the employee.
     */
    double getHoursWorked();
}
