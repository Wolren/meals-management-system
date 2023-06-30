package org.lookout_studios.meals_management_system.meals_management_system;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationService {

    /*
     * This method handles registration requests and sends confirmation emails.
     */
    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    public String registerUser(@RequestBody User user) {
        if (!emailCheck(user.getEmail())) {
            // TO-DO: Return a nice JSON
            return "Invalid email address";
        }
        // TO-DO: Send an email with confirmation link
        // TO-DO: Return a nice JSON
        return "200";
    }

    /*
     * This method checks whether an email address has a correct format
     */
    public boolean emailCheck(String email) {
        throw new UnsupportedOperationException("Not implemented");
    }

}
