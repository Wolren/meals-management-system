package org.lookout_studios.meals_management_system.meals_management_system;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Class establishing connection with database.
 * 
 * @author Hubert Borysowski
 */
public class DatabaseService {
    static String connectionInstanceName = "com.mysql.cj.jdbc.Driver";
    static String jsonConfigFilePath = ".config\\establishConnectionWithDatabaseConfig.json";
    static String jsonUrlKey = "databaseJdbcUrl";
    static String jsonUsernameKey = "databaseUsername";
    static String jsonPasswordKey = "databasePassword";

    /**
     * Creates an SQL connection with a database,
     * executes a query and closes the connection.
     * 
     * @param query mySQL query
     * @return Result of the query
     * @param connection Connection with the database
     *                   established with establishConnection();
     * @throws Exception
     */
    public ResultSet executeQuery(String query, Connection connection) throws Exception {
        /*
         * Create JSONParser object, so you can read configuration data from JSON file.
         */
        ResultSet result = null;
        try {
            Statement statement = connection.createStatement();
            result = statement.executeQuery(query);
        } catch (Exception exception) {
            throw exception;
        }
        return result;
    }

    /**
     * Reads credentials form a config file and establishes
     * a connection with a database using them.
     * 
     * @return A Connection object ready to be used
     */
    private Connection establishConnection() {
        Connection accessConnection = null;
        try {
            JSONParser jsonParser = new JSONParser();
            FileReader jsonFileDataReader = new FileReader(jsonConfigFilePath);
            JSONObject saveJsonData = (JSONObject) jsonParser.parse(jsonFileDataReader);
            String databaseJdbcUrl = (String) saveJsonData.get(jsonUrlKey);
            String databaseUsername = (String) saveJsonData.get(jsonUsernameKey);
            String databasePassword = (String) saveJsonData.get(jsonPasswordKey);
            Class.forName(connectionInstanceName);
            accessConnection = DriverManager.getConnection(
                    databaseJdbcUrl,
                    databaseUsername,
                    databasePassword);
        } catch (FileNotFoundException fileNotFoundError) {
            System.out.println(fileNotFoundError);
        } catch (IOException ioError) {
            System.out.println(ioError);
        } catch (Exception mysqlError) {
            System.out.println(mysqlError);
        }
        return accessConnection;
    }

    /**
     * Checks if a user with a given email is present in the database
     * 
     * @param email of a user
     * @return true if an email is found in the database, false if it isn't
     * @throws Exception
     */
    public boolean isUserRegistered(String email) throws Exception {
        Connection connection = establishConnection();
        ResultSet result = executeQuery(
                String.format(
                        "SELECT u.email FROM users u WHERE u.email = \"%s\"",
                        email),
                connection);
        if (!result.next()) {
            return false;
        }
        connection.close();
        return true;
    }
}