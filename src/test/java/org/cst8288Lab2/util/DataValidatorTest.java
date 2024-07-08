/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package org.cst8288Lab2.util;

import java.util.List;
import org.cst8288Lab2.dto.Term;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for DataValidator class
 * @author ryany
 */
public class DataValidatorTest {
    
    /**
     * constructor
     */
    public DataValidatorTest() {
    }
    
    /**
     * setUpclass before all
     */
    @BeforeAll
    public static void setUpClass() {
    }
    
    /**
     * tearDown after all
    */
    @AfterAll
    public static void tearDownClass() {
    }
    
    /**
     * setup
     */
    @BeforeEach
    public void setUp() {
    }
    
    /**
     * tear down
     */
    @AfterEach
    public void tearDown() {
    }

    /**
     * create the object of DataValidator
     */
    private final DataValidator validator = new DataValidator();
    
    /**
     * Test of validateData method, of class DataValidator.
     */
    @Test
    public void testValidateData_ValidData() {
        int rowNumber = 1;
        String studentId = "123456789";
        String courseId = "CST8288";
        Term term = Term.SUMMER;
        int year = 2024;
        
        List<String> errors = validator.validateData(rowNumber, studentId, courseId, term, year);
        assertTrue(errors.isEmpty(), "Expected no validation errors");
    }

    /**
     * Test for scenario with invalid studentId
     */
    @Test
    public void testValidateData_InvalidStudentId() {
        int rowNumber = 1;
        String studentId = "12345678"; // Invalid student ID
        String courseId = "CST8288";
        Term term = Term.SUMMER;
        int year = 2023;

        List<String> errors = validator.validateData(rowNumber, studentId, courseId, term, year);
        assertEquals(1, errors.size());
        assertEquals("\nType 1 error of row 1: studentId '12345678' - Length is not 9 digits", errors.get(0));
    }

     /**
     * Test for scenario with invalid courseId
     */
    @Test
    public void testValidateData_InvalidCourseId() {
        int rowNumber = 1;
        String studentId = "123456789";
        String courseId = "CST8288T"; // Invalid course ID
        Term term = Term.SUMMER;
        int year = 2023;

        List<String> errors = validator.validateData(rowNumber, studentId, courseId, term, year);
        assertEquals(1, errors.size());
        assertEquals("\nType 2 error of row 1: courseId 'CST8288T' - Invalid format (should be 3 letters followed by 4 digits)", errors.get(0));
    }

    /**
     * Test for scenario with invalid term
     */
    @Test
    public void testValidateData_InvalidTerm() {
        int rowNumber = 1;
        String studentId = "123456789";
        String courseId = "CST8288";
        Term term = null; // Invalid term
        int year = 2023;

        List<String> errors = validator.validateData(rowNumber, studentId, courseId, term, year);
        assertEquals(1, errors.size());
        assertEquals("\nType 3 error of row 1: term 'null' - Invalid term (should be one of WINTER, SUMMER, FALL)", errors.get(0));
    }
    
    /**
     * Test for scenario with invalid year
     */
    @Test
    public void testValidateData_InvalidYear() {
        int rowNumber = 1;
        String studentId = "123456789";
        String courseId = "CST8288";
        Term term = Term.SUMMER;
        int year = 1950; // Invalid year

        List<String> errors = validator.validateData(rowNumber, studentId, courseId, term, year);
        assertEquals(1, errors.size());
        assertEquals("\nType 4 error of row 1: year '1950' - Invalid year (should be between 1965 and 2025)", errors.get(0));
    }
  
}
