package org.lookout_studios.meals_management_system.meals_management_system;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class RegistrationServiceTests {
    @Test
    void validEmailCheck() {
        RegistrationController registrationController = new RegistrationController();
        assertTrue(registrationController.emailCheck("example@gmail.com"));
        assertTrue(registrationController.emailCheck("example1234@gmail.com"));
        assertTrue(registrationController.emailCheck("abc-d@mail.com"));
        assertTrue(registrationController.emailCheck("abc.def@mail.com"));
        assertTrue(registrationController.emailCheck("abc@mail.com"));
        assertTrue(registrationController.emailCheck("abc-def@mail.com"));
        assertTrue(registrationController.emailCheck("abc123@mail.com"));
    }

    @Test
    void invalidEmailCheck() {
        RegistrationController registrationController = new RegistrationController();
        assertFalse(registrationController.emailCheck("abc-@mail.com"));
        assertFalse(registrationController.emailCheck("abc..def@mail.com"));
        assertFalse(registrationController.emailCheck("abc#def@mail.com"));
        assertFalse(registrationController.emailCheck("abc()@mail.com"));
        assertFalse(registrationController.emailCheck("abc^&$@mail.com"));
    }
}
