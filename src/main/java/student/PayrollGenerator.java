package student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The PayrollGenerator class is responsible for processing payroll information.
 * It reads employee and time card data from CSV files, calculates payroll details,
 * updates employee records, and generates pay stub records.
 */
public final class PayrollGenerator {
    /**
     * Default file path for employee records.
     */
    private static final String DEFAULT_EMPLOYEE_FILE = "resources/employees.csv";
    /**
     * Default file path for pay stub records.
     */
    private static final String DEFAULT_PAYROLL_FILE = "resources/pay_stubs.csv";
    /**
     * Default file path for time card records.
     */
    private static final String DEFAULT_TIME_CARD_FILE = "resources/time_cards.csv";

    /**
     * Private constructor to prevent instantiation.
     */
    private PayrollGenerator() {

    }

    /**
     * The main method that runs the payroll generation process.
     * @param args Command-line arguments for specifying file paths.
     */
    public static void main(String[] args) {
        // Process the command-line arguments and store them in an Arguments object
        Arguments arguments = Arguments.process(args);

        // Read the employee data from the specified file and store it in a list of strings
        List<String> employeeLines = FileUtil.readFileToList(arguments.getEmployeeFile());

        // Read the time card data from the specified file and store it in a list of strings
        List<String> timeCards = FileUtil.readFileToList(arguments.getTimeCards());

        // Convert the list of employee CSV strings to a list of IEmployee objects using the Builder class
        List<IEmployee> employees = employeeLines.stream().map(Builder::buildEmployeeFromCSV)
                .collect(Collectors.toList());

        // Convert the list of time card CSV strings to a list of ITimeCard objects using the Builder class
        List<ITimeCard> timeCardList = timeCards.stream().map(Builder::buildTimeCardFromCSV)
                .collect(Collectors.toList());

        // Update the employee records based on the time cards and write the updated records back to the employee file
        updateEmployeeRecords(employees, timeCardList, arguments.getEmployeeFile());

        // Generate pay stubs for the employees and write them to the specified payroll file
        generatePayStubs(employees, arguments.getPayrollFile());
    }

    /**
     * Updates employee records with their payroll information based on time card data.
     * @param employees List of employees to update.
     * @param timeCardList List of time cards to use for updates.
     * @param employeeFile The file path to write the updated employee records to.
     */
    private static void updateEmployeeRecords(List<IEmployee> employees, List<ITimeCard> timeCardList,
                                              String employeeFile) {
        for (IEmployee employee : employees) {
            // Find the first time card that matches the current employee's ID
            Optional<ITimeCard> matchingTimeCard = timeCardList.stream()
                    .filter(tc -> tc.getEmployeeID().equals(employee.getID()))
                    .findFirst();

            // If a matching time card is found
            if (matchingTimeCard.isPresent()) {
                // Get the hours worked from the matching time card
                double hoursWorked = matchingTimeCard.get().getHoursWorked();

                // If the hours worked are negative, skip this iteration and move to the next employee
                if (hoursWorked < 0) {
                    continue;
                }

                // Cast the IEmployee interface to the Employee class
                Employee emp = (Employee) employee;

                // Process the payroll for the employee based on the hours worked
                emp.processPayroll(hoursWorked);
            }

            // Write the updated employee records to a file
            writeUpdatedEmployeesToFile(employees, employeeFile);
        }
    }

    /**
     * Writes the updated employee records to the specified file.
     * @param employees List of employees with updated records.
     * @param employeeFile The file path to write the updated employee records to.
     */
    private static void writeUpdatedEmployeesToFile(List<IEmployee> employees, String employeeFile) {
        // Convert each employee to a CSV formatted string using the toCSV method and collect them into a list
        List<String> updatedEmployeeLines = employees.stream()
                .map(IEmployee::toCSV)
                .collect(Collectors.toList());

        // Add the CSV header line at the beginning of the list
        updatedEmployeeLines.add(0, FileUtil.EMPLOYEE_HEADER);

        // Try to write the list of CSV lines to the specified file
        try {
            FileUtil.writeFile(employeeFile, updatedEmployeeLines);
        } catch (Exception e) {
            System.err.println("Error writing update employee records to file: " + e.getMessage());
        }
    }

    /**
     * Generates pay stub records for each employee and writes them to the specified file.
     * @param employees List of employees to generate pay stubs for.
     * @param payrollFile The file path to write the generated pay stub records to.
     */
    private static void generatePayStubs(List<IEmployee> employees, String payrollFile) {
        // Create a list to store the CSV lines for the pay stubs
        List<String> payStubLines = new ArrayList<>();

        // Add the CSV header line for the pay stubs
        payStubLines.add(FileUtil.PAY_STUB_HEADER);

        for (IEmployee employee : employees) {
            // Cast the IEmployee interface to the Employee class to call the getPayStub method
            Employee emp = (Employee) employee;
            PayStub payStub = emp.getPayStub();

            // If the pay stub is not null, add it to the list of pay stub lines in CSV format
            if (payStub != null) {
                payStubLines.add(payStub.toCSV());
            }
        }

        // Try to write the list of pay stub lines to the specified file
        try {
            FileUtil.writeFile(payrollFile, payStubLines);
        } catch (Exception e) {
            System.err.println("Error writing pay stub records to file: " + e.getMessage());
        }
    }

    /**
     * Inner class to handle command-line arguments and default file paths.
     */
    private static final class Arguments {
        /**
         * File path for employee records.
         */
        private String employeeFile = DEFAULT_EMPLOYEE_FILE;
        /**
         * File path for pay stub records.
         */
        private String payrollFile = DEFAULT_PAYROLL_FILE;
        /**
         * File path for time card records.
         */
        private String timeCards = DEFAULT_TIME_CARD_FILE;

        /**
         * Private constructor to prevent instantiation.
         */
        private Arguments() {

        }

        /**
         * Gets the employee file path.
         * @return The employee file path.
         */
        public String getEmployeeFile() {
            return employeeFile;
        }

        /**
         * Gets the payroll file path.
         * @return The payroll file path.
         */
        public String getPayrollFile() {
            return payrollFile;
        }

        /**
         * Gets the time cards file path.
         * @return The time cards file path.
         */
        public String getTimeCards() {
            return timeCards;
        }

        /**
         * Prints the help message for the command-line arguments.
         */
        public void printHelp() {
            System.out.println(
                    "Usage: java student.PayrollGenerator [-e employee_file] [-t time_cards_file] [-o payroll_file]");
            System.out.println("Options:");
            System.out.println(
                    "  -e employee_file  Input file containing employee information. Default is employees.csv");
            System.out.println(
                    "  -t time_cards_file  Input file containing time card information. Default is time_cards.csv");
            System.out.println(
                    "  -o payroll_file  Output file containing payroll information. Default is pay_stubs.csv");
            System.out.println("  -h                Print this help message");
        }

        /**
         * Processes the command-line arguments and returns an Arguments object with the specified file paths.
         * @param args The command-line arguments.
         * @return An Arguments object with the specified file paths.
         */
        public static Arguments process(String[] args) {
            Arguments arguments = new Arguments();
            for (int i = 0; i < args.length; i++) {
                if (args[i].equals("-e")) {
                    if (i + 1 < args.length && !args[i + 1].startsWith("-")) {
                        arguments.employeeFile = args[i + 1];
                    } else {
                        System.out.println("Missing argument for -e option");
                        arguments.printHelp();
                        System.exit(1);
                    }
                } else if (args[i].equals("-t")) {
                    if (i + 1 < args.length && !args[i + 1].startsWith("-")) {
                        arguments.timeCards = args[i + 1];
                    } else {
                        System.out.println("Missing argument for -t option");
                        arguments.printHelp();
                        System.exit(1);
                    }
                } else if (args[i].equals("-o")) {
                    if (i + 1 < args.length && !args[i + 1].startsWith("-")) {
                        arguments.payrollFile = args[i + 1];
                    } else {
                        System.out.println("Missing argument for -o option");
                        arguments.printHelp();
                        System.exit(1);
                    }
                } else if (args[i].equals("-h")) {
                    arguments.printHelp();
                    System.exit(0);
                } else if (args[i].startsWith("-")) {
                    System.out.println("Unknown option: " + args[i]);
                    arguments.printHelp();
                    System.exit(1);
                }
            }
            return arguments;
        }
    }
}
