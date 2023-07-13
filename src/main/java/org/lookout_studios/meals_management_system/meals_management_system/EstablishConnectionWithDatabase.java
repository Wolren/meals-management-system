package org.lookout_studios.meals_management_system.meals_management_system;

import java.sql.Connection;
import java.sql.DriverManager;
class accessData {
    /* TO-DO: Create config file. Remove credentials from source code. */
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
    /**
     * 
     * The method connects with database using database url and admin credentials.
     */
    public void createSqlConnection () {
        /* My SQL connection establishment.*/
        Connection accessConnection;
        try {
            Class.forName (accessData.connectionInstanceName);
            accessConnection = DriverManager.getConnection (accessData.databaseJdbcUrl, accessData.databaseUsername, accessData.databasePassword);
            accessConnection.close ();
        }
        catch (Exception error) {
            System.out.println (error);
        }
    }
}