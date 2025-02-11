package student;

/**
 * The TimeCard class represents a time card for an employee. It implements the ITimeCard interface
 * and includes details such as the employee ID and hours worked.
 */
public class TimeCard implements ITimeCard {

    /**
     * The ID of the employee associated with the time card.
     */
    private String employeeID;

    /**
     * The number of hours worked by the employee.
     */
    private double hoursWorked;

    /**
     * Constructs a TimeCard object with the specified employee ID and hours worked.
     * @param employeeID The ID of the employee.
     * @param hoursWorked The number of hours worked by the employee.
     */
    public TimeCard(String employeeID, double hoursWorked) {
        this.employeeID = employeeID;
        this.hoursWorked = hoursWorked;
    }

    /**
     * Gets the ID of the employee associated with the time card.
     * @return The employee's ID.
     */
    @Override
    public String getEmployeeID() {
        return employeeID;
    }

    /**
     * Gets the number of hours worked by the employee.
     * @return The hours worked by the employee.
     */
    @Override
    public double getHoursWorked() {
        return hoursWorked;
    }
}
