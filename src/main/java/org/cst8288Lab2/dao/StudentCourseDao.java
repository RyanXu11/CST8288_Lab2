/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.cst8288Lab2.dao;

import org.cst8288Lab2.dto.Course;
import org.cst8288Lab2.dto.StudentCourse;

/**
 * This is Dao interface for table studentcourse
 * @author ryany
 */
public interface StudentCourseDao {
    int addStudentCourse(StudentCourse studentCourse);
    void purge();     
}
