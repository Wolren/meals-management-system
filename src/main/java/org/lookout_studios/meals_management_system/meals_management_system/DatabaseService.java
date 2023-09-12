package org.lookout_studios.meals_management_system.meals_management_system;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

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
    static String executeQueryExceptionReason = "Failed to execute query";

    Logger log = LoggerFactory.getLogger(DatabaseService.class);

    /**
     * Creates an SQL connection with a database,
     * executes a SELECT query and closes the connection.
     *
     * @param query mySQL SELECT query
     * @return Result of the query
     * @param connection Connection with the database
     *                   established with establishConnection();
     * @throws Exception
     */
    public ResultSet executeSelectQuery(String query, Connection connection, Object... parameters) throws Exception {
        /*
         * Create JSONParser object, so you can read configuration data from JSON file.
         */
        ResultSet result;
        try {
            log.debug(String.format("Executing query %s", query));
            PreparedStatement statement = connection.prepareStatement(query);
            for (int i = 0; i < parameters.length; i++) {
                statement.setObject(i + 1, parameters[i]);
            }
            result = statement.executeQuery();
        } catch (Exception exception) {
            log.error(exception.getMessage());
            throw exception;
        }
        return result;
    }

    /**
     *
     * @param query      Non-SELECT mySQL query
     * @return A boolean representing database response. See
     *         java.sql.Statement.execute() for further details
     * @throws Exception
     */
    public boolean executeOtherQuery(String query, Object... parameters) throws Exception {
        boolean result = false;
        Optional<Connection> connection = establishConnection();
        if (connection.isPresent()) {
            try {
                log.debug(String.format("Executing query %s", query));
                PreparedStatement statement = connection.get().prepareStatement(query);
                for (int i = 0; i < parameters.length; i++) {
                    statement.setObject(i + 1, parameters[i]);
                }
                result = statement.execute();
            } catch (Exception exception) {
                log.error(exception.getMessage());
                throw exception;
            }
        }
        return result;
    }

    /**
     * Reads credentials from a config file and establishes
     * a connection with a database using them.
     *
     * @return A Connection object ready to be used
     */
    private Optional<Connection> establishConnection() {
        Connection accessConnection;
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
            return Optional.of(accessConnection);
        } catch (Exception mysqlError) {
            System.out.println(mysqlError);
            return Optional.empty();
        }
    }

    /**
     * Checks if a user with a given email is present in the database
     *
     * @param email of a user
     * @return true if an email is found in the database, false if it isn't
     * @throws Exception
     */
    public boolean isUserRegistered(String email) throws Exception {
        String query = "SELECT u.email FROM users u WHERE u.email = ?";
        Optional<Connection> connection = establishConnection();
        if (connection.isPresent()) {
            ResultSet result = executeSelectQuery(query, connection.get(), email);
            return result.next();
        }
        return false;
    }

    /**
     * Registers new user in the database
     *
     * @param user A user object representing a user to be registered
     * @throws Exception
     */
    public void registerNewUser(User user) throws Exception {
        String query = "INSERT INTO users (email, password, isVerified, registrationToken) VALUES (?, ?, false, ?)";
        executeOtherQuery(query, user.getEmail(), user.getPasswordHash(), user.getRegistrationToken());
    }

    public boolean verifyRegistrationToken(int userId, String registrationToken) throws Exception {
        String query = "SELECT u.registrationToken FROM users u WHERE userId = ?";
        Optional<Connection> connection = establishConnection();
        if (connection.isPresent()) {
            ResultSet result = executeSelectQuery(query, connection.get(), userId);
            if (!result.next()) {
                log.info(String.format("User with id %d does not exist", userId));
                return false;
            }
            String foundToken = result.getString("registrationToken");
            if (!foundToken.equals(registrationToken)) {
                log.info(String.format(
                        "Tokens '%s' and '%s' do not match",
                        foundToken,
                        registrationToken));
                return false;
            }
            result.close();
        }
        return true;
    }

    public void markUserAsVerified(int userId) throws Exception {
        String query = "UPDATE users SET isVerified = true WHERE userId = ?";
        executeOtherQuery(query, userId);
    }
}