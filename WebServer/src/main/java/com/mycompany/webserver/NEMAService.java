/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webserver;

import java.sql.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
/**
 *
 * @author diamo
 */

@Path("service")
public class NEMAService {
    // Database connection authentication details
    static final String DatabaseURL = "jdbc:mysql://localhost:3306/ibdms_server";
    static final String DatabaseUser = "user";
    static final String DatabasePass = "pass";
    
    @GET
    @Path("drone")
    @Produces(MediaType.APPLICATION_JSON)
    public String getDrones() {
        String result = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try {
            // Connect to database
            connection = DriverManager.getConnection(DatabaseURL, DatabaseUser, DatabasePass);
            
            // Preparing statement
            statement = connection.createStatement();
            
            // Execute Query
            String sql = "SELECT * FROM drone";
            resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()) {
                
                // Gets details from mySQL
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int x_pos = resultSet.getInt("xpos");
                int y_pos = resultSet.getInt("ypos");
                
                // Adds to result string
                result += "ID: " + id + ", Name: " + name + ", X Position: " + x_pos + ", Y Position: " + y_pos + "\n";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close all the resources
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        // Returns result
        return result;
    }
    
    @GET
    @Path("fires")
    @Produces(MediaType.APPLICATION_JSON)
    public String getFires() {
        // Gets current fires that haven't been deleted
        String result = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        // This boolean will be activated if there is an active fire
        boolean activeFires = false;
        
        try {
            // Connect to database
            connection = DriverManager.getConnection(DatabaseURL, DatabaseUser, DatabasePass);
            
            // Preparing statement
            statement = connection.createStatement();
            
            // Execute Query
            String sql = "SELECT * FROM fire";
            resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()) {
                
                // Gets details from mySQL
                int id = resultSet.getInt("id");
                boolean isActive = resultSet.getBoolean("isActive");
                int intensity = resultSet.getInt("intensity");
                int x_pos = resultSet.getInt("xpos");
                int y_pos = resultSet.getInt("ypos");
                
                // Checks if fire is active, if so, adds it to result
                if (isActive) {
                    activeFires = true;
                    // Adds to result string
                    result += "ID: " + id + ", Active: true" + ", Intensity: " + intensity + ", X Position: " + x_pos + ", Y Position: " + y_pos + "\n";
                }
            }
            
            // If active fires is still false, sets result to say there are no active fires
            if (!activeFires) {
                result = "There are no active fires.";
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close all the resources
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        // Returns result
        return result;
        
    }
    
    @GET
    @Path("oldfires")
    @Produces(MediaType.APPLICATION_JSON)
    public String getOldFires() {
        // Gets fires that are old and have been deleted
        String result = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        // This boolean will be activated if there is an inactive fire
        boolean inactiveFires = false;
        
        try {
            // Connect to database
            connection = DriverManager.getConnection(DatabaseURL, DatabaseUser, DatabasePass);
            
            // Preparing statement
            statement = connection.createStatement();
            
            // Execute Query
            String sql = "SELECT * FROM fire";
            resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()) {
                
                // Gets details from mySQL
                int id = resultSet.getInt("id");
                boolean isActive = resultSet.getBoolean("isActive");
                int intensity = resultSet.getInt("intensity");
                int x_pos = resultSet.getInt("xpos");
                int y_pos = resultSet.getInt("ypos");
                
                // Checks if fire is inactive, if so, adds it to result
                if (!isActive) {
                    inactiveFires = true;
                    // Adds to result string
                    result += "ID: " + id + ", Active: false" + ", Intensity: " + intensity + ", X Position: " + x_pos + ", Y Position: " + y_pos + "\n";
                }
            }
            
            // If active fires is still false, sets result to say there are no active fires
            if (!inactiveFires) {
                result = "There are no current fires.";
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close all the resources
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        // Returns result
        return result;
        
    }
    
    @GET
    @Path("firetrucks")
    @Produces(MediaType.APPLICATION_JSON)
    public String getFireTrucks() {
        // Gets fire trucks
        String result = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        // This boolean will be activated if there is a fire truck
        boolean isFireTrucks = false;
        
        try {
            // Connect to database
            connection = DriverManager.getConnection(DatabaseURL, DatabaseUser, DatabasePass);
            
            // Preparing statement
            statement = connection.createStatement();
            
            // Execute Query
            String sql = "SELECT * FROM fire";
            resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()) {
                
                // Gets details from mySQL
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int designatedFireId = resultSet.getInt("designatedFireId");
                
                isFireTrucks = true;
                
                // Adds to result string
                result += "ID: " + id + ", Name: " + name + ", Designated Fire ID: " + designatedFireId + "\n";
            }
            
            // If active fires is still false, sets result to say there are no active fires
            if (!isFireTrucks) {
                result = "There are no firetrucks.";
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close all the resources
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        // Returns result
        return result;
        
    }
}
