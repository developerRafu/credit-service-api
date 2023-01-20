package com.creditproject.creditserviceapi.integrations.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import com.creditproject.creditserviceapi.helpers.AuthenticationRequestMockBuilder;
import com.creditproject.creditserviceapi.helpers.ClientRegisterRequestMockBuilder;
import com.creditproject.creditserviceapi.rest.responses.TokenResponse;
import com.creditproject.creditserviceapi.vo.enums.RequestEnums;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import java.io.IOException;
import java.util.Map;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration-test")
public class ClientTests {
  @LocalServerPort private int port;
  private String token;

  @BeforeEach
  void setup() {
    RestAssured.port = port;
    RestAssured.basePath = "/v1";
    setToken();
  }

  @Test
  void shouldSaveClients() {
    final var request = ClientRegisterRequestMockBuilder.builder().defaultValues().build();
    given()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .headers(getHeaders())
        .body(request)
        .when()
        .post("/clients")
        .then()
        .statusCode(HttpStatus.CREATED.value());
  }

  @Test
  void shouldReturnClients() {
    final var request = ClientRegisterRequestMockBuilder.builder().defaultValues().build();
    given()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .headers(getHeaders())
        .body(request)
        .when()
        .get("/clients")
        .then()
        .statusCode(HttpStatus.OK.value())
        .body("content", Matchers.notNullValue());
  }

  private Map<String, ?> getHeaders() {
    return Map.of(RequestEnums.AUTHORIZATION_HEADER.getValue(), token);
  }

  private void setToken() {
    final var request = AuthenticationRequestMockBuilder.builder().defaultValues().build();
    final var response =
        given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(request)
            .when()
            .post("/auth/authenticate");
    try {
      final var token =
          new ObjectMapper().readValue(response.getBody().asInputStream(), TokenResponse.class);
      this.token = token.getType() + token.getToken();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
