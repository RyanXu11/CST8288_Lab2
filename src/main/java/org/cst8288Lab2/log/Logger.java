/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.cst8288Lab2.log;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is used to save the logging for read csv file into database
 * @author ryany
 */
public class Logger {
    
    /**
     * The file path for the import success report log.
     */
    private static final String LOG_FILE_PASSED = "src/main/java/org/cst8288Lab2/log/import-report.md";
    
    /**
     * The file path for the import error report log.
     */
    private static final String LOG_FILE_ERROR = "src/main/java/org/cst8288Lab2/log/error-report.md";
    
    /**
     * Singleton instance of the Logger class.
     */
    private static Logger instance = null;
    
    /**
    * Private constructor of Logger.
    */
    private Logger() { }
    
    /**
     * Private constructor to prevent direct instantiation of Logger.
     */
    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }
    
    /**
     * Creates the file if it does not exist at the specified filePath.
     *
     * @param filePath the path of the file to create
     */    
    protected static void createFileIfNotExists(String filePath) {
        Path path = Paths.get(filePath);
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Logs an import success report with student, course, and student-course numbers to the success log file.
     *
     * @param studentNumber the number of student records imported
     * @param courseNumber the number of course records imported
     * @param studentCourseNumber the number of student-course records imported
     */    
    public static void importSuccessReport(int studentNumber, int courseNumber, int studentCourseNumber) {
        createFileIfNotExists(LOG_FILE_PASSED);
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(LOG_FILE_PASSED), StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            writer.write("# Import Report - " + now.format(formatter) + "\n");
            writer.write("- Table Student: Records Number is " + studentNumber + "\n");
            writer.write("- Table Course: Records Number is " + courseNumber + "\n");
            writer.write("- Table StudentCourse: Records Number is " + studentCourseNumber + "\n\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Logs an import error report with detailed error information to the error log file.
     *
     * @param errorReport the list of error rows, each containing a list of error messages
     */    
    public static void importErrorReport(List<List> errorReport) {
        createFileIfNotExists(LOG_FILE_ERROR);
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(LOG_FILE_ERROR), StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            writer.write("\n# Error Report - " + now.format(formatter) + "\n");
            writer.write("There are " + errorReport.size() + " rows that failed validation.\n");
            for (List<String> errorRow : errorReport) {
//                System.out.println(errorRow.getClass().getName());
//                System.out.println(errorRow.get(0).getClass().getName());
                for (String error : errorRow) {
                    writer.write(error + "\n");
                }
            }
            writer.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
