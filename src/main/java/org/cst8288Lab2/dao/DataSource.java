/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.cst8288Lab2.dao;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * This is the dataSource of the database
 * @author ryany
 */
public class DataSource {
    
    private static Connection conn = null;
    private static String url;
    private static String username;
    private static String password;

    // Private constructor to prevent instantiation
    private DataSource() { }

    /**
     * Returns a connection to the database.
     * If the connection is closed or null, it initializes the connection.
     * 
     * @return a Connection object to the database
     */
    public static Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                loadDBProp();
                conn = DriverManager.getConnection(url, username, password);
            } else {
                System.out.println("Reusing existing connection.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return conn;
    }

    /**
     * This function is used to load database properties from database.properties
     */
    private static void loadDBProp(){
        Properties props = new Properties();
        try {
            // set the abslute path
            String propFilePath = "data/database.properties";
            InputStream in = Files.newInputStream(Paths.get(propFilePath));
            props.load(in);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // Get properties

        username = props.getProperty("user");
        password = props.getProperty("pass");
        
        String host = props.getProperty("host");
        String db = props.getProperty("db");
        String port = props.getProperty("port");
        String name = props.getProperty("name");

        url = "jdbc:" + db + "://" + host + ":" + port + "/" + name;

//        System.out.printf("DataSource:\nurl:%s\nusername:%s\npassword:%s\n", url, username, password);
    }
}