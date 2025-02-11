package student;

/**
 * The Builder class provides static methods to create instances of IEmployee and ITimeCard
 * from CSV data.
 */
public final class Builder {

    /**
     * Private constructor to prevent instantiation.
     */
    private Builder() {

    }

    /**
     * Creates an IEmployee instance from a CSV string.
     * @param csv The CSV string containing employee data.
     * @return An IEmployee instance created from the CSV data.
     * @throws IllegalArgumentException if the employee type is invalid.
     */
    public static IEmployee buildEmployeeFromCSV(String csv) {
        String[] employeeFields = csv.split(",");

        String employeeType = employeeFields[0].trim();
        String name = employeeFields[1].trim();
        String id = employeeFields[2].trim();
        double payRate = Double.parseDouble(employeeFields[3].trim());
        double pretaxDeductions = Double.parseDouble(employeeFields[4].trim());
        double YTDEarnings = Double.parseDouble(employeeFields[5].trim());
        double YTDTaxesPaid = Double.parseDouble(employeeFields[6].trim());

        IEmployee employee = null;

        if (employeeType.equals("HOURLY")) {
            employee = new HourlyEmployee(name, id, payRate, pretaxDeductions, YTDEarnings, YTDTaxesPaid);
        } else if (employeeType.equals("SALARY")) {
            employee = new SalaryEmployee(name, id, payRate, pretaxDeductions, YTDEarnings, YTDTaxesPaid);
        } else {
            throw new IllegalArgumentException("Invalid employee type: " + employeeType);
        }

        return employee;
    }

    /**
     * Creates an ITimeCard instance from a CSV string.
     * @param csv The CSV string containing time card data.
     * @return An ITimeCard instance created from the CSV data.
     */
    public static ITimeCard buildTimeCardFromCSV(String csv) {
        String[] timeCardFields = csv.split(",");

        String employeeID = timeCardFields[0].trim();
        double hoursWorked = Double.parseDouble(timeCardFields[1].trim());

        ITimeCard timeCard = null;

        timeCard = new TimeCard(employeeID, hoursWorked);

        return timeCard;
    }
}
