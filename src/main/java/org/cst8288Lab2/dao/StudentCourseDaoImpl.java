/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.cst8288Lab2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import org.cst8288Lab2.dto.Course;
import org.cst8288Lab2.dto.StudentCourse;

/**
 * This is Dao implementation for studentcourse
 * @author ryany
 */
public class StudentCourseDaoImpl implements StudentCourseDao {
    
    /**
     * This is using to add new record in table studentcourse
     * @param studentCourse
     * @return int rowsAffected, 0 for failure, 1 for success
     */
    @Override
    public int addStudentCourse(StudentCourse studentCourse) {
        int rowsAffected = 0;
        try (Connection conn = DataSource.getConnection()) {
            String query = "INSERT INTO studentcourse (studentId, courseId, term, year) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, studentCourse.getStudentId());
            preparedStatement.setString(2, studentCourse.getCourseId());
            preparedStatement.setInt(3, studentCourse.getTerm().getValue());
            preparedStatement.setInt(4, studentCourse.getYear());
            
            rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("studentCourse inserted successfully.");
            } else {
                System.out.println("studentCourse insertion failed. No rows affected.");
            }            
        } catch (SQLIntegrityConstraintViolationException e) {
            // This exception is thrown when there is a constraint violation, such as a duplicate key
            System.out.println("Failed to insert studentCourse: Duplicate Course ID.");
        } catch (SQLException e) {
            // Other SQL exceptions can be caught here
            e.printStackTrace();
        }

        return rowsAffected;  // Return the number of rows affected, 0 or 1
    }    
 
    /**
     * This is used to purge the table if needed
     */
    @Override
    public void purge() {
        String logMessage;
        try {
            Connection conn = DataSource.getConnection();
            String query = "DELETE FROM StudentCourse";
            PreparedStatement pstmt = conn.prepareStatement(query);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                logMessage = "All StudentCourse deleted successfully!";
            } else {
                logMessage = "No StudentCourse to delete.";
            }
            System.out.println(logMessage);			
        } catch(SQLException e) {
            logMessage = "SQLException: " + e;
            System.out.println(logMessage);
//            e.printStackTrace();
        }
    }     
}
