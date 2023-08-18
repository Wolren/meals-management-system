package org.lookout_studios.meals_management_system.meals_management_system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerificationService {
    private String verificationUnsuccessfulMessage = "The registration token does not belong to user with this ID";
    private String verificationSuccessfulMessage = "Verification successful";
    Logger log = LoggerFactory.getLogger(VerificationService.class);

    @GetMapping(value = "/verify", produces = "application/json")
    public ResponseEntity<String> verifyUser(
            @RequestParam int userId,
            @RequestParam String registrationToken)
            throws Exception {
        log.info(String.format(
                "New registration request for user with id %d and registration token '%s'",
                userId, registrationToken));
        DatabaseService databaseService = new DatabaseService();
        if (!databaseService.verifyRegistrationToken(userId, registrationToken)) {
            log.info(String.format(
                    "Token '%s' does not belong to user with id %d, verification unsuccessful",
                    registrationToken,
                    userId));
            return new ResponseEntity<String>(
                    new ResponseBody(
                            HttpStatus.FORBIDDEN,
                            verificationUnsuccessfulMessage).getResponseBody(),
                    HttpStatus.FORBIDDEN);
        }
        databaseService.markUserAsVerified(userId);
        log.info(String.format(
                "User with id %d verified successfully",
                userId));
        return new ResponseEntity<String>(
                new ResponseBody(
                        HttpStatus.OK,
                        verificationSuccessfulMessage)
                        .getResponseBody(),
                HttpStatus.OK);
    }
}
