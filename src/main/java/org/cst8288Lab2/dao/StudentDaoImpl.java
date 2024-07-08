/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.cst8288Lab2.dao;

import org.cst8288Lab2.dto.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * This is Dao implementaion for table student
 * @author ryany
 */
public class StudentDaoImpl implements StudentDao {
	
    /**
     * This is used to add new record to table student
     * @param student
     * @return int rowsAffected, 0 for failure, 1 for success
     */
    @Override
    public int addStudent(Student student) {
        int rowsAffected = 0;
        try (Connection conn = DataSource.getConnection()) {
            String query = "INSERT INTO Student (studentId, firstName, lastName) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, student.getStudentId());
            preparedStatement.setString(2, student.getFirstName());
            preparedStatement.setString(3, student.getLastName());

            rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Student inserted successfully.");
            } else {
                System.out.println("Student insertion failed. No rows affected.");
            }            
        } catch (SQLIntegrityConstraintViolationException e) {
            // This exception is thrown when there is a constraint violation, such as a duplicate key
            System.out.println("Failed to insert student: Duplicate student ID.");
        } catch (SQLException e) {
            // Other SQL exceptions can be caught here
            e.printStackTrace();
        }

        return rowsAffected;  // Return the number of rows affected, 0 or 1
    }
	
    /**
     * This is used to get Student information by studentId
     * @param studentId
     * @return Student student
     */
    @Override
    public Student getStudent(int studentId) {
        Student student = null;
        try (Connection conn = DataSource.getConnection()) {
            String query = "SELECT * FROM Student WHERE studentId = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, studentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                student = new Student(resultSet.getInt("studentId"),
                                      resultSet.getString("firstName"),
                                      resultSet.getString("lastName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }
	
    /**
     * This is used to update student
     * @param student 
     */
    @Override
    public void updateStudent(Student student) {
        try (Connection conn = DataSource.getConnection()) {
            String query = "UPDATE Student SET firstName = ?, lastName = ? WHERE studentId = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getLastName());
            preparedStatement.setInt(3, student.getStudentId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
    /**
     * This is used to delete student by studentId
     * @param studentId 
     */
    @Override
    public void deleteStudent(int studentId) {
        try (Connection conn = DataSource.getConnection()) {
            String query = "DELETE FROM Student WHERE studentId = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, studentId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This is used to purge table student if needed
     */
    @Override
    public void purge() {
        String logMessage;
        try {
            Connection conn = DataSource.getConnection();
            String query = "DELETE FROM student";
            PreparedStatement pstmt = conn.prepareStatement(query);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                logMessage = "All students deleted successfully!";
            } else {
                logMessage = "No student to delete.";
            }
            System.out.println(logMessage);			
        } catch(SQLException e) {
            logMessage = "SQLException: " + e;
            System.out.println(logMessage);
//            e.printStackTrace();
        }
    }
}
