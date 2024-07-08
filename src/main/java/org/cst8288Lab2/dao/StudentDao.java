/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.cst8288Lab2.dao;

import org.cst8288Lab2.dto.Student;

/**
 * This is the Dao for table student
 * @author ryany
 */
public interface StudentDao {

    int addStudent(Student student);
    Student getStudent(int studentId);
    void updateStudent(Student student);
    void deleteStudent(int studentId);
    void purge();
    
}
