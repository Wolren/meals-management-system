package org.lookout_studios.meals_management_system.meals_management_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
class accessData {
    static String url = "jdbc:mysql://localhost:3306/mms_database";
    static String username = "root";
    static String password = "";
    static String connectionInstanceName = "com.mysql.cj.jdbc.Driver";
}
class databaseData {
    static String fridges = "select fridgeId, userId from fridges";
    static String productInstance = "select ProductTypeId, productName, fridgeId, expirationDate, entryDate, measurementUnitName, Amount from productinstance";
    static String users = "select email, password, userId from users";
}
/**
 * Class establishing connection with database.
 * 
 * @author Hubert Borysowski  
 */
public class EstablishConnectionWithDatabase {
    /**
     * Method establishing connection with database.
     *
     * <p><code>accessConnection = DriverManager.getConnection (accessData.url, accessData.username, accessData.password);</code> connects to the database using provided data.</p>
     * <p><code>executeSqlStatement = accessConnection.createStatement ();</code> creates an SQL statement, so we can prepare the object to receive data.</p>
     * <p><code>resultSet = executeSqlStatement.executeQuery (databaseData.fooTable);</code> receives and stores information about the current table.</p>
     */
    public void createSqlConnection () {
        Connection accessConnection;
        Statement executeSqlStatement;
        ResultSet storeTableDataSet;
        try {
            Class.forName (accessData.connectionInstanceName);
            accessConnection = DriverManager.getConnection (accessData.url, accessData.username, accessData.password);
            executeSqlStatement = accessConnection.createStatement ();
            storeTableDataSet = executeSqlStatement.executeQuery (databaseData.fridges);
            while(storeTableDataSet.next ()) {
                /* Insert operations here. */
            }
            executeSqlStatement = accessConnection.createStatement ();
            storeTableDataSet = executeSqlStatement.executeQuery (databaseData.productInstance);
            while(storeTableDataSet.next ()) {
                /* Insert operations here. */
            }
            executeSqlStatement = accessConnection.createStatement ();
            storeTableDataSet = executeSqlStatement.executeQuery (databaseData.users);
            while(storeTableDataSet.next ()) {
                /* Insert operations here. */
            }
            accessConnection.close ();
        }
        catch (Exception error) {
            System.out.println (error);
        }
    }
}