package org.lookout_studios.meals_management_system.meals_management_system;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PasswordCheckTest {
    private RegistrationService registrationService = new RegistrationService();

    @Test
    public void validPasswordCheck() {
        assertTrue(registrationService.passwordCheck("12345678"));
        assertTrue(registrationService.passwordCheck("abcdefgh"));
        assertTrue(registrationService.passwordCheck("1@qwerty"));
    }

    @Test
    public void invalidPasswordCheck() {
        assertFalse(registrationService.passwordCheck(""));
        assertFalse(registrationService.passwordCheck(null));
        assertFalse(registrationService.passwordCheck("abc"));
        assertFalse(registrationService.passwordCheck("123"));
        assertFalse(registrationService.passwordCheck("1234567"));
    }
}
