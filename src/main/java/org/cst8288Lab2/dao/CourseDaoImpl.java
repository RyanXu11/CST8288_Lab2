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

/**
 * This is the Dao implementation for table course
 * @author ryany
 */
public class CourseDaoImpl implements CourseDao {
    
    /**
     * This is used to add new record in table course
     * @param course
     * @return 
     */
    @Override
    public int addCourse(Course course) {
        int rowsAffected = 0;
        try (Connection conn = DataSource.getConnection()) {
            String query = "INSERT INTO course (courseId, courseName) VALUES (?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, course.getCourseId());
            preparedStatement.setString(2, course.getCourseName());
            
            rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Course inserted successfully.");
            } else {
                System.out.println("Course insertion failed. No rows affected.");
            }            
        } catch (SQLIntegrityConstraintViolationException e) {
            // This exception is thrown when there is a constraint violation, such as a duplicate key
            System.out.println("Failed to insert Course: Duplicate Course ID.");
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
            String query = "DELETE FROM course";
            PreparedStatement pstmt = conn.prepareStatement(query);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                logMessage = "All course deleted successfully!";
            } else {
                logMessage = "No course to delete.";
            }
            System.out.println(logMessage);			
        } catch(SQLException e) {
            logMessage = "SQLException: " + e;
            System.out.println(logMessage);
//            e.printStackTrace();
        }
    }    
}
