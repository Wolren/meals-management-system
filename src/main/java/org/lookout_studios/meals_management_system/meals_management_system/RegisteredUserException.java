package org.lookout_studios.meals_management_system.meals_management_system;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "This user is already registered")
public class RegisteredUserException extends Exception {
    public RegisteredUserException() {
    }
}
