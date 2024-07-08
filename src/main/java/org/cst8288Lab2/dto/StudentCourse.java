/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.cst8288Lab2.dto;

/**
 * This class is dto of table studentCourse in database lab2, 
 * represents a student's enrollment in a specific course for a specific term and year.
 * @author ryany
 */
public class StudentCourse {
    /**
     * The unique identifier for the student.
     */
    private int studentId;
    
    /**
     * The unique identifier for the course.
     */
    private String courseId;
    
    /**
     * The term during which the course is taken.
     */
    private Term term;
    
    /**
     * The year during which the course is taken.
     */
    private int year;

    /**
     * Constructs a new StudentCourse with the specified student ID, course ID, term, and year.
     *
     * @param studentId the unique identifier for the student
     * @param courseId the unique identifier for the course
     * @param term the term during which the course is taken
     * @param year the year during which the course is taken
     */
    public StudentCourse(int studentId, String courseId, Term term, int year) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.term = term;
        this.year = year;
    }

    /**
     * Returns the unique identifier for the student.
     *
     * @return the student ID
     */
    public int getStudentId() {
        return studentId;
    }

    /**
     * Returns the unique identifier for the course.
     *
     * @return the course ID
     */    
    public String getCourseId() {
        return courseId;
    }
    
    /**
     * Returns the term during which the course is taken.
     *
     * @return the term
     */
    public Term getTerm() {
        return term;
    }

    /**
     * Returns the year during which the course is taken.
     *
     * @return the year
     */    
    public int getYear() {
        return year;
    }
    
    /**
     * Sets the unique identifier for the student.
     *
     * @param studentId the new student ID
     */
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    
    /**
     * Sets the unique identifier for the course.
     *
     * @param courseId the new course ID
     */
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    /**
     * Sets the term during which the course is taken.
     *
     * @param term the new term
     */
    public void setTerm(Term term) {
        this.term = term;
    }
    
    /**
     * Sets the year during which the course is taken.
     *
     * @param year the new year
     */
    public void setYear(int year) {
        this.year = year;
    }
   
}
