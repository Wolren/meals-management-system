package org.lookout_studios.meals_management_system.meals_management_system;

import java.sql.Connection;
import java.sql.DriverManager;
/* TO-DO: Mock the behavior of database. Only connection with asserts. */
class accessData {
    static String url = "jdbc:mysql://localhost:3306/test_mms_database";
    static String username = "root";
    static String password = "";
    static String connectionInstanceName = "com.mysql.cj.jdbc.Driver";
}
/**
 * Class establishing connection with database.
 * 
 * @author Hubert Borysowski  
 */
public class EstablishConnectionWithDatabaseTest {
    /**
     * 
     * The method connects with database using database url and admin credentials.
     */
    public void createSqlConnection () {
        Connection accessConnection;
        try {
            Class.forName (accessData.connectionInstanceName);
            accessConnection = DriverManager.getConnection (accessData.url, accessData.username, accessData.password);
            accessConnection.close ();
        }
        catch (Exception error) {
            System.out.println (error);
        }
    }
}