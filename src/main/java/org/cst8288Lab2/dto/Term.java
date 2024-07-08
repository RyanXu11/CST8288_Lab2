/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.cst8288Lab2.dto;

/**
 * Enumeration representing the academic terms.
 * @author ryany
 */
public enum Term {
    /**
     * Winter term with a value of 1.
     */    
    WINTER(1),
    
    /**
     * Summer term with a value of 2.
     */    
    SUMMER(2),
    
    /**
     * Fall term with a value of 3.
     */    
    FALL(3);


    /**
     * The integer value associated with the term.
     */
    private final int value;

    /**
     * Constructs a Term enum with the specified integer value.
     *
     * @param value the integer value associated with the term
     */
    Term(int value) {
        this.value = value;
    }

    /**
     * Returns the integer value associated with the term.
     *
     * @return the integer value of the term
     */
    public int getValue() {
        return value;
    }
    
    /**
     * Returns the Term enum corresponding to the specified string.
     *
     * @param term the string representation of the term
     * @return the Term enum corresponding to the specified string
     * @throws IllegalArgumentException if the specified string does not match any term
     */
    public static Term fromString(String term) {
        switch (term.toUpperCase()) {
            case "WINTER":
                return WINTER;
            case "SUMMER":
                return SUMMER;
            case "FALL":
                return FALL;
            default:
                throw new IllegalArgumentException("Invalid term: " + term);
        }
    }

}
