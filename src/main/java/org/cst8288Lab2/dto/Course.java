/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.cst8288Lab2.dto;

/**
 * This class is dto of table Course in database lab2
 * @author ryany
 */
public class Course {
    /**
     * The unique identifier for the course.
     */
    private String courseId;
    
    /**
     * the name of course
     */
    private String courseName;

    /**
     * Constructor of the class
     * @param courseId
     * @param courseName 
     */
    public Course(String courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }

    /**
     * getter of courseId
     * @return String courseId
     */
    public String getCourseId() {
        return courseId;
    }

    /**
     * getter of courseName
     * @return String courseName
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * setter of courseId
     * @param courseId 
     */
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    /**
     * setter of courseName
     * @param courseName 
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    
}
