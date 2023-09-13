package org.lookout_studios.meals_management_system.meals_management_system;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;

import javax.annotation.Nullable;

public class VerificationServiceTest {

    private RestTemplate restTemplate = ignoreHttpErrors();

    /**
     * This method ensures that rest template does not throw exceptions when http
     * responses are other than 200 (OK)
     * 
     * @return A RestTemplate object with an error handler which does not throw
     *         exceptions when encountering errors
     */
    private RestTemplate ignoreHttpErrors() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new ResponseErrorHandler() {
            @Override
            public boolean hasError(@Nullable ClientHttpResponse response) throws IOException {
                return false;
            }

            @Override
            public void handleError(@Nullable ClientHttpResponse response) throws IOException {
            }
        });
        return restTemplate;
    }

    @InjectMocks
    private DatabaseService database;

    @Before
    public void before() {
        MockitoAnnotations.openMocks(this);
        database = mock(DatabaseService.class);
    }

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(options().port(8080));

    @Test
    /**
     * Mocks a scenario when credentials of a user being verified are invalid.
     * Ensures that such requests return 403 (Forbidden)
     * 
     * @throws Exception
     */
    public void verifyInvalidUserFail() throws Exception {
        ResponseEntity<String> response = null;
        int userId = 0;
        String userIdString = "0";
        String invalidRegistrationToken = "invalid";
        String expectedRequestUrl = "/verify?userId=0&registrationToken=invalid";
        String actualRequestUrl = "http://localhost:8080/verify?userId={userId}&registrationToken={registrationToken}";
        String responseBody = new ResponseBody(HttpStatus.FORBIDDEN).getResponseBody();
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("userId", userIdString);
        queryParams.put("registrationToken", invalidRegistrationToken);
        Mockito.when(database.verifyRegistrationToken(userId, invalidRegistrationToken)).thenReturn(false);
        boolean registered = database.verifyRegistrationToken(userId, invalidRegistrationToken);
        assertFalse(registered);
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo(expectedRequestUrl))
                .withQueryParam("registrationToken",
                        WireMock.equalTo(invalidRegistrationToken))
                .withQueryParam("userId", WireMock.equalTo(userIdString))
                .willReturn(WireMock.aResponse().withStatus(HttpStatus.FORBIDDEN.value()).withBody(responseBody)));
        response = restTemplate.getForEntity(actualRequestUrl, String.class, queryParams);
        assertEquals(HttpStatus.FORBIDDEN.value(), response.getStatusCode().value());
    }

    @Test
    /**
     * Mocks a scenario when user's parameters are valid. Ensures that such requests
     * return 200 (OK)
     * 
     * @throws Exception
     */
    public void verifyValidUserSuccess() throws Exception {
        ResponseEntity<String> response;
        int userId = 2137;
        String userIdString = "2137";
        String validRegistrationToken = "valid";
        String expectedRequestUrl = "/verify/?userId=2137&registrationToken=valid";
        String actualRequestUrl = "http://localhost:8080/verify/?userId={userId}&registrationToken={registrationToken}";
        String responseBody = new ResponseBody(HttpStatus.OK).getResponseBody();
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("userId", userIdString);
        queryParams.put("registrationToken", validRegistrationToken);
        Mockito.when(database.verifyRegistrationToken(userId, validRegistrationToken)).thenReturn(true);
        boolean registered = database.verifyRegistrationToken(userId, validRegistrationToken);
        assertTrue(registered);
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo(expectedRequestUrl))
                .withQueryParam("registrationToken",
                        WireMock.equalTo(validRegistrationToken))
                .withQueryParam("userId", WireMock.equalTo(userIdString))
                .willReturn(WireMock.aResponse().withStatus(HttpStatus.OK.value()).withBody(responseBody)));
        response = restTemplate.getForEntity(actualRequestUrl, String.class, queryParams);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    }
}
