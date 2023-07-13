package org.lookout_studios.meals_management_system.meals_management_system;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    /**
     * This method handles registration requests and sends confirmation emails.
     */
    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity registerUser(@RequestBody User user) throws InvalidEmailException {
        if (!emailCheck(user.getEmail())) {
            throw new InvalidEmailException();
        }
        user.registerInDatabase();
        new EmailSender().sendRegistrationEmail(user);

        JsonResponse response = new JsonResponse("status", 200);
        return ResponseEntity.ok(response.toString());
    }

    /*
     * This method checks whether an email address has a correct format
     */
    public boolean emailCheck(String email) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
