package org.lookout_studios.meals_management_system.meals_management_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
class accessData {
    static String databaseJdbcUrl = "jdbc:mysql://localhost:3306/mms_database";
    static String databaseUsername = "root";
    static String databasePassword = "";
    static String connectionInstanceName = "com.mysql.cj.jdbc.Driver";
}
/**
 * Class establishing connection with database.
 * 
 * @author Hubert Borysowski  
 */
public class EstablishConnectionWithDatabase {
    /* Database Data */
    static String fridges = "select * from fridges";
    static String productInstance = "select * from productinstance";
    static String users = "select * from users";
    static String MeasurementUnits = "select * from MeasurementUnits";
    /**
     * 
     * The method connects with database using database url and admin credentials.
     */
    public void createSqlConnection () {
        /* My SQL connection establishment and POST/GET operations. */
        Connection accessConnection;
        Statement statement;
        try {
            Class.forName (accessData.connectionInstanceName);
            accessConnection = DriverManager.getConnection (accessData.databaseJdbcUrl, accessData.databaseUsername, accessData.databasePassword);
            statement = accessConnection.createStatement();
            /* Waiting for response on what to do. */
            accessConnection.close ();
        }
        catch (Exception error) {
            System.out.println (error);
        }
    }
}