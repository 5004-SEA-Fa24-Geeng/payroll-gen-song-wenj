package student;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
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
        Arguments arguments = Arguments.process(args);

        List<String> employeeLines = FileUtil.readFileToList(arguments.getEmployeeFile());
        List<String> timeCards = FileUtil.readFileToList(arguments.getTimeCards());

        List<IEmployee> employees = employeeLines.stream().map(Builder::buildEmployeeFromCSV)
                .collect(Collectors.toList());
        List<ITimeCard> timeCardList = timeCards.stream().map(Builder::buildTimeCardFromCSV)
                .collect(Collectors.toList());

        updateEmployeeRecords(employees, timeCardList, arguments.getEmployeeFile());

        generatePayStubs(employees, arguments.getPayrollFile());
    }

    /**
     * Updates employee records with their payroll information based on time card data.
     * @param employees List of employees to update.
     * @param timeCardList List of time cards to use for updates.
     * @param employeeFile The file path to write the updated employee records to.
     */
    private static void updateEmployeeRecords(List<IEmployee> employees, List<ITimeCard> timeCardList, String employeeFile) {
        for (IEmployee employee : employees) {
            Optional<ITimeCard> matchingTimeCard = timeCardList.stream()
                    .filter(tc -> tc.getEmployeeID().equals(employee.getID()))
                    .findFirst();

            if (matchingTimeCard.isPresent()) {
                double hoursWorked = matchingTimeCard.get().getHoursWorked();

                Employee emp = (Employee) employee;

                emp.processPayroll(hoursWorked);
            }

            writeUpdatedEmployeesToFile(employees, employeeFile);
        }
    }

    /**
     * Writes the updated employee records to the specified file.
     * @param employees List of employees with updated records.
     * @param employeeFile The file path to write the updated employee records to.
     */
    private static void writeUpdatedEmployeesToFile(List<IEmployee> employees, String employeeFile) {
        List<String> updatedEmployeeLines = employees.stream()
                .map(IEmployee::toCSV)
                .collect(Collectors.toList());

        updatedEmployeeLines.add(0, FileUtil.EMPLOYEE_HEADER);

        try {
            Files.write(Paths.get(employeeFile), updatedEmployeeLines, StandardOpenOption.TRUNCATE_EXISTING);
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
        List<String> payStubLines = new ArrayList<>();

        payStubLines.add(FileUtil.PAY_STUB_HEADER);

        for (IEmployee employee : employees) {
            Employee emp = (Employee) employee;
            PayStub payStub = emp.getPayStub();
            if (payStub != null) {
                payStubLines.add(payStub.toCSV());
            }
        }

        try {
            Files.write(Paths.get(payrollFile), payStubLines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
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
