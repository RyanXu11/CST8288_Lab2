/*
 * Main
 */
package org.cst8288Lab2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;
import org.cst8288Lab2.dao.CourseDao;
import org.cst8288Lab2.dao.CourseDaoImpl;
import org.cst8288Lab2.dao.DataSource;
import org.cst8288Lab2.dao.StudentCourseDao;
import org.cst8288Lab2.dao.StudentCourseDaoImpl;
import org.cst8288Lab2.dao.StudentDao;
import org.cst8288Lab2.dao.StudentDaoImpl;
import org.cst8288Lab2.dto.Course;
import org.cst8288Lab2.dto.Student;
import org.cst8288Lab2.dto.StudentCourse;
import org.cst8288Lab2.dto.Term;
import org.cst8288Lab2.log.Logger;
import org.cst8288Lab2.util.DataValidator;

/**
 * starter code for Lab2
 */
public class Lab2Starter {
    
    private static int studentNumber = 0;
    private static int courseNumber = 0;
    private static int studentCourseNumber = 0;
    private final static List<List> errors = new ArrayList<>();

    /**
     * Parses the file: bulk-import.csv Validates each item in each row and
     * updates the database accordingly.
     *
     * @param args -
     */
    public static void main(String[] args) {
        //Ensure that you use the Properties class to load values from the database.properties file
        Properties dbConnection = new Properties();
        StudentDao studentDaoImpl = new StudentDaoImpl();
        CourseDao courseDaoImpl = new CourseDaoImpl();
        StudentCourseDao studentCourseDaoImpl = new StudentCourseDaoImpl();
        DataValidator dataValidator = new DataValidator();

        //Preserve this input path
        try (InputStream in = new FileInputStream("data/database.properties")) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
                String out;
                while ((out = br.readLine()) != null) {
                    System.out.println(out.toString());
                }
            }            
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Preserve this input path
        try (InputStream in = new FileInputStream("data/bulk-import_NameDuplicates.csv")) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
                
                DataSource.getConnection();
                studentCourseDaoImpl.purge();
                studentDaoImpl.purge();
                courseDaoImpl.purge();
                
                String csvLine;  // The raw data of one line of CSV file
                int rowNumber = 0;
                
                // The first line is the header of the csv
                boolean skipHeader = true;
                while ((csvLine = br.readLine()) != null) {
                    if (skipHeader) {
                        skipHeader = false;
                        continue;
                    }
                    // Split the csvLine into fields which comma as delimiter
                    String[] rowFields = csvLine.split(",");
                    String studentId = rowFields[0];
                    String firstName = rowFields[1];
                    String lastName = rowFields[2];
                    String courseId = rowFields[3];
                    String courseName = rowFields[4];
                    String termString = rowFields[5];
                    int year = Integer.parseInt(rowFields[6]);
                    
                    //If 'termString' is not one of the enum types of Term, set 'term' to null
                    Term term;
                    try {
                        term = Term.fromString(termString);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Row " + rowNumber + ": Invalid term '" + termString + "'");
                        term = null;  
                    }

                    // Validate each field
                    List<String> errorsForRow = dataValidator.validateData(rowNumber, studentId, courseId, term, year);
                    // if passed, call three dao to write the data to database, 
                    // save the numbers wrote to the tables
                    if (errorsForRow.isEmpty()) {
                        // Three dtc constructors
                        Student student = new Student(Integer.parseInt(studentId), firstName, lastName);
                        Course course = new Course(courseId, courseName);
                        StudentCourse studentCourse = new StudentCourse(Integer.parseInt(studentId), courseId, term, year);
                        
                        System.out.println("Row " + rowNumber + ": Passed validation");
                        studentNumber += studentDaoImpl.addStudent(student);
                        courseNumber += courseDaoImpl.addCourse(course);
                        studentCourseNumber += studentCourseDaoImpl.addStudentCourse(studentCourse);
                        
                    } else {
                        // if validation failed, add row number and the raw data to error report
                        System.out.println("Row " + rowNumber + ": Validation failed - " + errorsForRow);
                        // Log errors, save to error report, etc.
                        errorsForRow.add(0, "  " + csvLine);
                        errorsForRow.add(0, "\n\n## Row " + rowNumber + ":");
                        errors.add(errorsForRow);
                    }                    
                    rowNumber++;
                    System.out.println(csvLine.toString());
                }
                // Call Logger write the result to two markdown log files
                Logger.importSuccessReport(studentNumber, courseNumber, studentCourseNumber);
                Logger.importErrorReport(errors);         
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
