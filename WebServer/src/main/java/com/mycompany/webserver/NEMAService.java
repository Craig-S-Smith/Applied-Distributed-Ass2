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
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
}
