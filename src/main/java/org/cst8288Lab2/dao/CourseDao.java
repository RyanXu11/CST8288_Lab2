/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.cst8288Lab2.dao;

import org.cst8288Lab2.dto.Course;

/**
 * This is the Dao interface for table course
 * @author ryany
 */
public interface CourseDao {
    int addCourse(Course course);
//    Course getCourse(int courseId);
//    void updateCourse(Course courseId);
//    void deleteCourse(int courseId);
    void purge();    
}
