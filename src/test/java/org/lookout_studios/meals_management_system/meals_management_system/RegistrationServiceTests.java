package org.lookout_studios.meals_management_system.meals_management_system;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class RegistrationServiceTests {
    @Test
    void validEmailCheck() {
        RegistrationService registrationService = new RegistrationService();
        assertTrue(registrationService.emailCheck("example@gmail.com"));
        assertTrue(registrationService.emailCheck("example1234@gmail.com"));
        assertTrue(registrationService.emailCheck("abc-d@mail.com"));
        assertTrue(registrationService.emailCheck("abc.def@mail.com"));
        assertTrue(registrationService.emailCheck("abc@mail.com"));
        assertTrue(registrationService.emailCheck("abc-def@mail.com"));
        assertTrue(registrationService.emailCheck("abc123@mail.com"));
    }

    @Test
    void invalidEmailCheck() {
        RegistrationService registrationService = new RegistrationService();
        assertFalse(registrationService.emailCheck("abc-@mail.com"));
        assertFalse(registrationService.emailCheck("abc..def@mail.com"));
        assertFalse(registrationService.emailCheck("abc#def@mail.com"));
        assertFalse(registrationService.emailCheck("abc()@mail.com"));
        assertFalse(registrationService.emailCheck("abc^&$@mail.com"));
    }
}
