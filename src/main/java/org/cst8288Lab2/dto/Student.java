/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.cst8288Lab2.dto;

/**
 * This Student class is dto of table student in database lab2.
 * @author ryany
 */
public class Student {
    /**
     * The unique identifier of student.
     */
    private int studentId;
    
    /**
     * The first name of student.
     */
    private String firstName;
    
    /**
     * The last name of student.
     */
    private String lastName;

    /**
     * Constructor of student
     * @param studentId
     * @param firstName
     * @param lastName 
     */
    public Student(int studentId, String firstName, String lastName) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * getter of studentId
     * @return int studentId
     */
    public int getStudentId() {
        return studentId;
    }

    /**
     * getter of first name
     * @return String firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * getter of last name
     * @return String lastName
     */
    public String getLastName() {
        return lastName;
    }

    
    /**
     * setter of studentId
     * @param studentId 
     */
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    /**
     * setter of first name
     * @param firstName 
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * setter of last name
     * @param lastName 
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
