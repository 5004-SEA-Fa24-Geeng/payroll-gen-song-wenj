package student;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Collections;
import java.util.List;

/**
 * The FileUtil class provides utility methods for reading and writing files.
 * It includes methods to read a file into a list of strings, write a list of strings to a file,
 * and create backups of files.
 */
public final class FileUtil {

    /**
     * The header for the employee CSV file.
     */
    public static final String EMPLOYEE_HEADER =
            "employee_type,name,ID,payRate,pretaxDeductions,YTDEarnings,YTDTaxesPaid";

    /**
     * The header for the pay stub CSV file.
     */
    public static final String PAY_STUB_HEADER =
            "employee_name,net_pay,taxes,ytd_earnings,ytd_taxes_paid";

    /**
     * Private constructor to prevent instantiation.
     */
    private FileUtil() {

    }

    /**
     * Reads a file and returns its content as a list of strings, excluding the header line.
     * @param file The file path to read.
     * @return A list of strings containing the lines of the files, excluding the header line.
     */
    public static List<String> readFileToList(String file) {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Path.of(file));
            lines.remove(0);
        } catch (IOException e) {
            System.err.println("Error reading employee file: " + e.getMessage());
        }
        return lines;
    }

    /**
     * Writes a list of strings to a file.
     * @param outFile The file path to write to.
     * @param lines The list of strings to write.
     */
    public static void writeFile(String outFile, List<String> lines) {
        writeFile(outFile, lines, true);
    }

    /**
     * Writes a list of strings to a file, with an option to create a backup of the file if it exists.
     * @param outFile The file path to write to.
     * @param lines The list of strings to write.
     * @param backup Whether to create a backup of the file if it exists.
     */
    public static void writeFile(String outFile, List<String> lines, boolean backup) {
        if (backup) {
            try {
                if (Files.exists(Path.of(outFile))) {
                    Files.move(Path.of(outFile), Path.of(outFile + ".bak"),
                            StandardCopyOption.REPLACE_EXISTING);
                }
            } catch (IOException e) {
                System.err.println("Error backing up file: " + e.getMessage());
                e.printStackTrace();
                return;
            }
        }

        try {
            Files.write(Path.of(outFile), lines);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
