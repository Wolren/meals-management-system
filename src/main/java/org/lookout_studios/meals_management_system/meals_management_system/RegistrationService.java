package org.lookout_studios.meals_management_system.meals_management_system;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class RegistrationService {

    /**
     * This method handles registration requests and sends confirmation emails.
     * 
     * @throws RegisteredUserException
     */
    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity registerUser(@RequestBody User user) throws InvalidEmailException, RegisteredUserException {
        if (!emailCheck(user.getEmail())) {
            throw new InvalidEmailException();
        }
        if (!newUserCheck(user.getEmail())) {
            throw new RegisteredUserException();
        }
        String registrationToken = user.generateRegistrationToken();
        user.register();
        // TO-DO: Send email with registration link containting the token
        return ResponseEntity.ok().build();
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

    /**
     * Checks whether the provided email address is present in the database.
     * 
     * @param email Email address of the user.
     * @return True if the email address is new, false if it belongs to an existing
     *         user.
     */
    public boolean newUserCheck(String email) {
        // TO-DO: Execute a query to check whether the email is in the database.
        // If so, return false
        return true;
    }
}
