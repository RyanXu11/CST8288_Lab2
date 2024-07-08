/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.cst8288Lab2.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.cst8288Lab2.dto.Term;

/**
 * The DataValidator class provides methods to validate student data records.
 * @author ryany
 */
public class DataValidator {

    /**
     * Pattern for validating course IDs. The format should be 3 letters followed by 4 digits.
     *
     */
    private static final Pattern COURSE_ID_PATTERN = Pattern.compile("[A-Za-z]{3}\\d{4}");
    
    /**
     * List of valid terms.
     */
    private static final List<String> TERM_LIST = List.of("WINTER", "SUMMER", "FALL");

    /**
     * Validates the data for a single row, checking student ID length, course ID format,
     * term validity, and year range.
     *
     * @param rowNumber the row number of the data being validated
     * @param studentId the student ID to validate
     * @param courseId the course ID to validate
     * @param term the term to validate
     * @param year the year to validate
     * @return a list of error messages for the row, or an empty list if no errors
     */    
    public List<String> validateData(int rowNumber, String studentId, String courseId, Term term, int year) {
        List<String> errorsForRow = new ArrayList<>();

        if (studentId.length() != 9) {
            errorsForRow.add(String.format("\nType 1 error of row %d: studentId '%s' - Length is not 9 digits", rowNumber, studentId));
        }
        if (!COURSE_ID_PATTERN.matcher(courseId).matches()) {
            errorsForRow.add(String.format("\nType 2 error of row %d: courseId '%s' - Invalid format (should be 3 letters followed by 4 digits)", rowNumber, courseId));
        }
        if (term == null) {
            errorsForRow.add(String.format("\nType 3 error of row %d: term '%s' - Invalid term (should be one of WINTER, SUMMER, FALL)", rowNumber, term));
        }
        if (year < 1965 || year > 2025) {
            errorsForRow.add(String.format("\nType 4 error of row %d: year '%d' - Invalid year (should be between 1965 and 2025)", rowNumber, year));
        }
        return errorsForRow;
    }

}

