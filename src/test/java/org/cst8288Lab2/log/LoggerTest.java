/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package org.cst8288Lab2.log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test class for the Logger class
 * 
 * @author ryany
 */
public class LoggerTest {
    
    /**
     * constructor
     */
    public LoggerTest() {
    }
    
    /**
     * The string of file path for the import error report log.
     */
    private final String TEST_LOG_FILE = "src/main/java/org/cst8288Lab2/log/import-report.md";
    
    /**
     * The file path for the import success report log.
     */    
    private final Path TEST_FILE_PATH = Paths.get(TEST_LOG_FILE);
    
    /**
     * The string of file path for the import error report log.
     */
    private final String ERROR_LOG_FILE = "src/main/java/org/cst8288Lab2/log/error-report.md";
    
    /**
     * The file path for the import error report log.
     */
    private final Path ERROR_FILE_PATH = Paths.get(ERROR_LOG_FILE);
    
    /**
     * setUpClass
     */
    @BeforeAll
    public static void setUpClass() {
    }
    
    /**
     * tearDownClass
     */
    @AfterAll
    public static void tearDownClass() {
    }
    
    /**
     * before each test check if the file exists, if not create it
     * @throws IOException 
     */
    @BeforeEach
    public void setUp() throws IOException {
        // Create the file before each test if it does not exist
        if (Files.notExists(TEST_FILE_PATH)) {
            Files.createFile(TEST_FILE_PATH);
        }
        if (Files.notExists(ERROR_FILE_PATH)) {
            Files.createFile(ERROR_FILE_PATH);
        }        
    }
    
    /**
     * Delete the file if needed
     * @throws IOException 
     */
    @AfterEach
    public void tearDown() throws IOException{
        // Delete the file after each test
//        Files.deleteIfExists(TEST_FILE_PATH);
    }

    /**
     * Test of getInstance method, of class Logger.
     */
    @Test
    public void testGetInstance() {
        Logger instance1 = Logger.getInstance();
        Logger instance2 = Logger.getInstance();
        
        // Verify that the instances are the same
        assertSame(instance1, instance2);
        
        // Ensure the instance is not null
        assertNotNull(instance1);
    }

    
    /**
     * Test of importSuccessReport method of class Logger.
     */
    @Test
    public void testImportSuccessReport() throws IOException{
        int studentNumber = 40;
        int courseNumber = 20;
        int studentCourseNumber = 40;

        // Call the method to test
        Logger.importSuccessReport(studentNumber, courseNumber, studentCourseNumber);
        
        // Read the file and get the last four non-empty lines
        List<String> lastLines = getLastNonEmptyLines(TEST_FILE_PATH, 4);
//        for (String line:lastLines){
//            System.out.println(line);
//        }

        // Verify the file contents
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String firstLine = lastLines.get(0);
        assertTrue(firstLine.startsWith("# Import Report - "));
        assertTrue(firstLine.endsWith(now.format(formatter)));

        String secondLine = lastLines.get(1);
        assertEquals("- Table Student: Records Number is " + studentNumber, secondLine);

        String thirdLine = lastLines.get(2);
        assertEquals("- Table Course: Records Number is " + courseNumber, thirdLine);

        String fourthLine = lastLines.get(3);
        assertEquals("- Table StudentCourse: Records Number is " + studentCourseNumber, fourthLine);   
    }

    /**
     * Test of importErrorReport method of class Logger.
     * @throws IOException 
     */
    @Test
    public void testImportErrorReport() throws IOException {
        List<List> errorReport = new ArrayList<>();
        List<String> errorRow1 = new ArrayList<>();
        errorRow1.add("Error 1");
        errorRow1.add("Error 2");
        errorReport.add(errorRow1);

        List<String> errorRow2 = new ArrayList<>();
        errorRow2.add("Error 3");
        errorRow2.add("Error 4");
        errorReport.add(errorRow2);

        // Call the method to test
        Logger.importErrorReport(errorReport);

        // Read the file and get the last non-empty lines
        List<String> lastLines = getLastNonEmptyLines(ERROR_FILE_PATH, 6);
//        for (String line:lastLines){
//            System.out.println(line);
//        }

        // Verify the contents of the last non-empty lines
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        assertTrue(lastLines.get(0).startsWith("# Error Report - "));
        assertTrue(lastLines.get(0).endsWith(now.format(formatter)));

        assertEquals("There are 2 rows that failed validation.", lastLines.get(1));
        assertEquals("Error 1", lastLines.get(2));
        assertEquals("Error 2", lastLines.get(3));
        assertEquals("Error 3", lastLines.get(4));
        assertEquals("Error 4", lastLines.get(5));
    }    
    
    /**
     * private method to get the last several lines of the log files
     * @param filePath path of the file
     * @param lineCount lines need to get
     * @return
     * @throws IOException 
     */
    private List<String> getLastNonEmptyLines(Path filePath, int lineCount) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    if (lines.size() == lineCount) {
                        lines.remove(0);
                    }
                    lines.add(line);
                }
            }
        }
        return lines;
    }

}
