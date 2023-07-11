package org.lookout_studios.meals_management_system.meals_management_system;

import org.apache.catalina.connector.Response;
import org.apache.logging.log4j.status.StatusLogger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationService {

    /*
     * This method handles registration requests and sends confirmation emails.
     */
    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    public JsonResponse registerUser(@RequestBody User user) throws Exception {
        if (!emailCheck(user.getEmail())) {
            ResponseStatus status = ResponseStatus.BAD_REQUEST;
            return new JsonResponse(status, status.getCode());
        }
        // TO-DO: Send an email with confirmation link
        ResponseStatus status = ResponseStatus.OK;
        return new JsonResponse(status);
    }

    /*
     * This method checks whether an email address has a correct format
     */
    public boolean emailCheck(String email) {
        throw new UnsupportedOperationException("Not implemented");
    }

}
