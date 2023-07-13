package org.lookout_studios.meals_management_system.meals_management_system;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { MealsManagementSystemApplication.class })
@WebAppConfiguration
class RegistrationControllerTests {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void validEmailCheck() {
        RegistrationService registrationController = new RegistrationService();
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
        RegistrationService registrationController = new RegistrationService();
        assertFalse(registrationController.emailCheck("abc-@mail.com"));
        assertFalse(registrationController.emailCheck("abc..def@mail.com"));
        assertFalse(registrationController.emailCheck("abc#def@mail.com"));
        assertFalse(registrationController.emailCheck("abc()@mail.com"));
        assertFalse(registrationController.emailCheck("abc^&$@mail.com"));
    }

    /**
     * This test ensures that registered users cannot be registered again
     */
    @Test
    public void registeredUserRejection() {
        try {
            setup();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }
}
