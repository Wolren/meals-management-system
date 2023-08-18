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

public class VerificationServiceTest {

    private static RestTemplate restTemplate = new RestTemplate();

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
    public void verifyInvalidUserFail() throws Exception {
        restTemplate.setErrorHandler(new ResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse response) throws IOException {
                return false;
            }

            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
            }
        });
        ResponseEntity<String> response = null;
        int userId = 0;
        String userIdString = "0";
        String invalidRegistrationToken = "abc";
        String requestUrl = "/verify";
        String fullRequestUrl = "http://localhost:8080/verify";
        String responseBody = new ResponseBody(HttpStatus.FORBIDDEN).getResponseBody();
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("userId", userIdString);
        queryParams.put("registrationToken", invalidRegistrationToken);
        Mockito.when(database.verifyRegistrationToken(userId, invalidRegistrationToken)).thenReturn(false);
        boolean registered = database.verifyRegistrationToken(userId, invalidRegistrationToken);
        assertFalse(registered);
        if (!registered) {
            WireMock.stubFor(WireMock.get(WireMock.urlEqualTo(requestUrl))
                    .withQueryParam("registrationToken",
                            WireMock.equalTo(invalidRegistrationToken))
                    .withQueryParam("userId", WireMock.equalTo(userIdString))
                    .willReturn(WireMock.aResponse().withStatus(403).withBody(responseBody)));
            response = restTemplate.getForEntity(fullRequestUrl, String.class, queryParams);
        }
        assertEquals(HttpStatus.FORBIDDEN.value(), response.getStatusCode().value());
    }

    @Test
    public void verifyValidUserSuccess() throws Exception {
        restTemplate.setErrorHandler(new ResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse response) throws IOException {
                return false;
            }

            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
            }
        });
        ResponseEntity<String> response = null;
        int userId = 2137;
        String userIdString = "2137";
        String validRegistrationToken = "valid";
        String requestUrl = "/verify";
        String fullRequestUrl = "http://localhost:8080/verify";
        String responseBody = new ResponseBody(HttpStatus.OK).getResponseBody();
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("userId", userIdString);
        queryParams.put("registrationToken", validRegistrationToken);
        Mockito.when(database.verifyRegistrationToken(userId, validRegistrationToken)).thenReturn(true);
        boolean registered = database.verifyRegistrationToken(userId, validRegistrationToken);
        assertTrue(registered);
        if (registered) {
            WireMock.stubFor(WireMock.get(WireMock.urlEqualTo(requestUrl))
                    .withQueryParam("registrationToken",
                            WireMock.equalTo(validRegistrationToken))
                    .withQueryParam("userId", WireMock.equalTo(userIdString))
                    .willReturn(WireMock.aResponse().withStatus(200).withBody(responseBody)));
            response = restTemplate.getForEntity(fullRequestUrl, String.class, queryParams);
        }
        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    }
}