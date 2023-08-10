package org.lookout_studios.meals_management_system.meals_management_system;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RegistrationServiceTests {
    @Mock
    RegistrationService registrationService = new RegistrationService();

    @Autowired
    private MockMvc mockMvc;

    /**
     * Ensures that emailCheck returns true with valid emails
     */
    @Test
    void validEmailCheck() {
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
    void invalidEmailCheck() {
        assertFalse(registrationService.emailCheck("abc-@mail.com"));
        assertFalse(registrationService.emailCheck("abc..def@mail.com"));
        assertFalse(registrationService.emailCheck("abc#def@mail.com"));
        assertFalse(registrationService.emailCheck("abc()@mail.com"));
        assertFalse(registrationService.emailCheck("abc^&$@mail.com"));
    }

    /**
     * Ensures that /register requests with invalid emails generate
     * InvalidEmailException
     * 
     * @throws Exception
     */
    @Test
    public void registerInvalidEmailUserFail() throws Exception {
        User invalidEmailUser = new User("abc-@mail.com", null);
        Mockito
                .when(registrationService.registerUser(invalidEmailUser))
                .thenThrow(new InvalidEmailException());
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(invalidEmailUser.toString());
        mockMvc.perform(mockRequest)
                .andExpect(status().isBadRequest());
    }

    /**
     * Ensures that /register requests with invalid passwords generate
     * InvalidPasswordException
     * 
     * @throws Exception
     */
    @Test
    public void registerInvalidPasswordUserFail() throws Exception {
        User invalidEmailUser = new User("abc@mail.com", null);
        Mockito
                .when(registrationService.registerUser(invalidEmailUser))
                .thenThrow(new InvalidPasswordException());
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(invalidEmailUser.toString());
        mockMvc.perform(mockRequest)
                .andExpect(status().isBadRequest());
    }

    /**
     * Ensures that /register requests with emails of existing users generate
     * ExistingUserException
     * 
     * @throws Exception
     */
    @Test
    public void registerExistingUserFail() throws Exception {
        User invalidEmailUser = new User("abc@mail.com", null);
        Mockito
                .when(registrationService.registerUser(invalidEmailUser))
                .thenThrow(new RegisteredUserException());
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(invalidEmailUser.toString());
        mockMvc.perform(mockRequest)
                .andExpect(status().isBadRequest());
    }
}
