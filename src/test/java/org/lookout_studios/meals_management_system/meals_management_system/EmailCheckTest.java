package org.lookout_studios.meals_management_system.meals_management_system;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

public class EmailCheckTest {
    private RegistrationService registrationService = new RegistrationService();

    /**
     * Ensures that emailCheck returns true with valid emails
     */
    @Test
    public void validEmailCheck() {
        assertTrue(registrationService.emailCheck("example@gmail.com"));
        assertTrue(registrationService.emailCheck("example1234@gmail.com"));
        assertTrue(registrationService.emailCheck("abc-d@mail.com"));
        assertTrue(registrationService.emailCheck("abc.def@mail.com"));
        assertTrue(registrationService.emailCheck("abc@mail.com"));
        assertTrue(registrationService.emailCheck("abc-def@mail.com"));
        assertTrue(registrationService.emailCheck("abc123@mail.com"));
    }

    /**
     * Ensures that emailCheck returns false with invalid emails
     */
    @Test
    public void invalidEmailCheck() {
        assertFalse(registrationService.emailCheck("abc-@mail.com"));
        assertFalse(registrationService.emailCheck("abc..def@mail.com"));
        assertFalse(registrationService.emailCheck("abc#def@mail.com"));
        assertFalse(registrationService.emailCheck("abc()@mail.com"));
        assertFalse(registrationService.emailCheck("abc^&$@mail.com"));
        assertFalse(registrationService.emailCheck(null));
    }
}
