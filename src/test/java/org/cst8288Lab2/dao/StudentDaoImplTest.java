/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package org.cst8288Lab2.dao;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import org.cst8288Lab2.dto.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 * Unit test class for the StudentDaoImpl class.
 * 
 * This class uses Mockito to mock dependencies and test the behavior of the 
 * StudentDaoImpl class methods.
 * @author ryany
 */
public class StudentDaoImplTest {
    
    /**
     * Mocked DataSource instance.
     */    
    @Mock
    private DataSource dataSource;

    /**
     * Mocked Connection instance.
     */
    @Mock
    private Connection connection;
    
    /**
     * Mocked PreparedStatement instance.
     */
    @Mock
    private PreparedStatement preparedStatement;

    /**
     * Mocked ResultSet instance.
     */    
    @Mock
    private ResultSet resultSet;
    
    /**
     * The StudentDaoImpl instance with injected mocks.
     */    
    @InjectMocks
    private StudentDaoImpl studentDaoImpl;
    
    /**
     * Mocked static DataSource instance.
     */    
    private MockedStatic<DataSource> dataSourceMock;


    /**
     * Sets up resources before any tests are run.
     *
     * @throws Exception if an error occurs during setup
     */    
    @org.junit.jupiter.api.BeforeAll
    public static void setUpClass() throws Exception {
    }

    /**
     * Cleans up resources after all tests are run.
     *
     * @throws Exception if an error occurs during cleanup
     */
    @org.junit.jupiter.api.AfterAll
    public static void tearDownClass() throws Exception {
    }

    /**
     * Sets up the test environment before each test.
     * Initializes mocks and configures the DataSource mock to return the mocked connection.
     *
     * @throws Exception if an error occurs during setup
     */    
    @org.junit.jupiter.api.BeforeEach
    public void setUp() throws Exception {
        StudentDao studentDaoImpl = new StudentDaoImpl();   
        MockitoAnnotations.openMocks(this);
        dataSourceMock = Mockito.mockStatic(DataSource.class);
        dataSourceMock.when(DataSource::getConnection).thenReturn(connection);        
    }

    
    /**
     * Cleans up the test environment after each test.
     * Closes the static mock for DataSource.
     *
     * @throws Exception if an error occurs during cleanup
     */    
    @org.junit.jupiter.api.AfterEach
    public void tearDown() throws Exception {
        dataSourceMock.close();
    }
    
    /**
     * Test of addStudent method, of class StudentDaoImpl.
     */
    @org.junit.jupiter.api.Test
    public void testAddStudent_Inserted() throws Exception {
        Student student = new Student(123456789, "John", "Smith");

        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);

        int result = studentDaoImpl.addStudent(student);

        assertEquals(1, result);
        verify(preparedStatement).setInt(1, student.getStudentId());
        verify(preparedStatement).setString(2, student.getFirstName());
        verify(preparedStatement).setString(3, student.getLastName());
        verify(preparedStatement).executeUpdate();
    }

    /**
     * Test of addStudent method, of class StudentDaoImpl.
     */
    @org.junit.jupiter.api.Test
    public void testAddStudent_Duplicated() throws Exception {
        Student student = new Student(123456789, "John", "Smith");

        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        doThrow(new SQLIntegrityConstraintViolationException()).when(preparedStatement).executeUpdate();

        int result = studentDaoImpl.addStudent(student);

        assertEquals(0, result);
        verify(preparedStatement).setInt(1, student.getStudentId());
        verify(preparedStatement).setString(2, student.getFirstName());
        verify(preparedStatement).setString(3, student.getLastName());
        verify(preparedStatement).executeUpdate();
    }    
    
    /**
     * Test of getStudent method, of class StudentDaoImpl.
     */
    @org.junit.jupiter.api.Test
    public void testGetStudent_Success() throws Exception{
        int studentId = 123456789;
        String firstName = "John";
        String lastName = "Smith";

        // Stubbing
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("studentId")).thenReturn(studentId);
        when(resultSet.getString("firstName")).thenReturn(firstName);
        when(resultSet.getString("lastName")).thenReturn(lastName);

        // Execute the method
        Student result = studentDaoImpl.getStudent(studentId);

        // Verify the result
        assertEquals(studentId, result.getStudentId());
        assertEquals(firstName, result.getFirstName());
        assertEquals(lastName, result.getLastName());

        // Verify interactions
        verify(preparedStatement).setInt(1, studentId);
        verify(preparedStatement).executeQuery();
        verify(resultSet).next();
        verify(resultSet).getInt("studentId");
        verify(resultSet).getString("firstName");
        verify(resultSet).getString("lastName");
    }

    /**
     * Test for scenario the student can't be found
     * @throws Exception 
     */
    @Test
    public void testGetStudent_NotFound() throws Exception {
        // Set the test studentId
        int studentId = 123456789;

        // Stubbing
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(false);

        // Execute the method
        Student result = studentDaoImpl.getStudent(studentId);

        // Verify the result
        assertEquals(null, result);

        // Verify interactions
        verify(preparedStatement).setInt(1, studentId);
        verify(preparedStatement).executeQuery();
        verify(resultSet).next();
    }
    
    
    /**
     * Test of purge method, of class StudentDaoImpl.
     */
    @org.junit.jupiter.api.Test
    public void testPurge() throws Exception{
        // Stubbing
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(10); // assuming 10 rows are deleted

        // Capture the system output
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Execute the method
        studentDaoImpl.purge();

        // Verify interactions
        verify(connection).prepareStatement("DELETE FROM student");
        verify(preparedStatement).executeUpdate();

        // Verify output
        String expectedOutput = "All students deleted successfully!\n";
        assertEquals(expectedOutput.trim(), outContent.toString().trim());

        // Restore the original system output
        System.setOut(originalOut);
    }
    
}
