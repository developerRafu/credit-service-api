package com.creditproject.creditserviceapi.integrations.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import com.creditproject.creditserviceapi.helpers.AuthenticationRequestMockBuilder;
import com.creditproject.creditserviceapi.helpers.RegisterRequestMockBuilder;
import com.creditproject.creditserviceapi.helpers.enums.UserConstantsEnum;
import com.creditproject.creditserviceapi.vo.enums.TokenEnum;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration-test")
public class AuthTests {

  @BeforeEach
  void setup() {
    RestAssured.port = port;
    RestAssured.basePath = "/v1/auth";
  }

  @LocalServerPort private int port;

  @Test
  void shouldRegisterWithSuccessful() {
    final var request = RegisterRequestMockBuilder.builder().defaultValues().build();
    given()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(request)
        .when()
        .post("/register")
        .then()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .statusCode(HttpStatus.OK.value())
        .body(
            "type", equalTo(TokenEnum.BEARER.getValue()),
            "token", notNullValue(),
            "duration", notNullValue());
  }

  @Test
  void shouldAuthenticateWithSuccessful() {
    final var request = AuthenticationRequestMockBuilder.builder().defaultValues().build();
    given()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(request)
        .when()
        .post("/authenticate")
        .then()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .statusCode(HttpStatus.OK.value())
        .body(
            "type", equalTo(TokenEnum.BEARER.getValue()),
            "token", notNullValue(),
            "duration", notNullValue());
  }

  @Test
  void shouldReturnErrorWhenEmailExists() {
    final var request =
        RegisterRequestMockBuilder.builder()
            .defaultValues()
            .withEmail(UserConstantsEnum.USERNAME.getValueString())
            .build();
    given()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(request)
        .when()
        .post("/register")
        .then()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .statusCode(HttpStatus.BAD_REQUEST.value())
        .body(
            "text", equalTo("Email já está em uso."),
            "type", equalTo("ERROR"),
            "code", equalTo(HttpStatus.BAD_REQUEST.value()),
            "details", notNullValue());
  }

  @Test
  void shouldReturnsErrorWhenPasswordIsWrong() {
    final var request =
        AuthenticationRequestMockBuilder.builder().defaultValues().withPassword("12345").build();
    given()
        .contentType(ContentType.JSON)
        .body(request)
        .when()
        .post("/authenticate")
        .then()
        .statusCode(HttpStatus.FORBIDDEN.value())
        .body(
            "text",
            equalTo("Senha inválida."),
            "type",
            equalTo("ERROR"),
            "code",
            equalTo(HttpStatus.FORBIDDEN.value()));
  }
}
