package org.lookout_studios.meals_management_system.meals_management_system;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationService {
    private String invalidEmailMessage = "Invalid email address";
    private String invalidPasswordMessage = "Invalid password";
    private String alreadyRegisteredMessage = "This user already exists";
    private String validPasswordPattern = ".{8,}";

    Logger log = LoggerFactory.getLogger(RegistrationService.class);

    /**
     * Handles /register requests by checking validity of provided data, registering
     * new users in the database and sending confirmation emails.
     * 
     * @param user User object defined in a request body
     * @return JSON response with registration token
     * @throws Exception
     */
    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> registerUser(@RequestBody User user) throws Exception {
        String userEmail = user.getEmail();
        log.info(String.format(
                "New registration request for user with email '%s'",
                userEmail));
        log.info(String.format(
                "Checking if '%s' is a valid email",
                userEmail));
        if (!emailCheck(userEmail)) {
            log.info(String.format("Email '%s' is invalid", userEmail));
            return new ResponseEntity<String>(
                    new ApiResponse(HttpStatus.BAD_REQUEST, invalidEmailMessage).getResponse(),
                    HttpStatus.BAD_REQUEST);
        }
        if (!passwordCheck(user.getPassword())) {
            log.info(String.format("Password provided by %s is invalid", userEmail));
            return new ResponseEntity<String>(
                    new ApiResponse(HttpStatus.BAD_REQUEST, invalidPasswordMessage).getResponse(),
                    HttpStatus.BAD_REQUEST);
        }
        DatabaseService databaseService = new DatabaseService();
        try {
            log.info(String.format(
                    "Checking if %s is already registered",
                    userEmail));
            boolean isRegistered = databaseService.isUserRegistered(userEmail);
            if (isRegistered) {
                log.info(String.format(
                        "User with email %s is already registered",
                        userEmail));
                return new ResponseEntity<String>(
                        new ApiResponse(
                                HttpStatus.BAD_REQUEST,
                                alreadyRegisteredMessage).getResponse(),
                        HttpStatus.BAD_REQUEST);
            }
            log.info(String.format(
                    "%s is not registered",
                    userEmail));
        } catch (Exception exception) {
            throw exception;
        }
        log.info(String.format(
                "Registering new user with email '%s'",
                userEmail));
        databaseService.registerNewUser(user);
        // TO-DO: Send email with registration link containing the token
        log.info(String.format(
                "User with email %s registered successfully",
                userEmail));
        return new ResponseEntity<String>(
                new ApiResponse(HttpStatus.OK).getResponse(), HttpStatus.OK);
    }

    /**
     * This method checks whether an email address has a correct format
     */
    public boolean emailCheck(String email) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$");
        Matcher matcher = pattern.matcher(email);
        boolean answer = matcher.find();
        return answer;
    }

    public boolean passwordCheck(String password) {
        boolean match;
        try {
            Pattern pattern = Pattern.compile(validPasswordPattern);
            Matcher matcher = pattern.matcher(password);
            match = matcher.find();
        } catch (NullPointerException exception) {
            return false;
        } catch (Exception exception) {
            throw exception;
        }
        return match;
    }
}
