package com.creditproject.creditserviceapi.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.creditproject.creditserviceapi.helpers.AuthenticationRequestMockBuilder;
import com.creditproject.creditserviceapi.helpers.RegisterRequestMockBuilder;
import com.creditproject.creditserviceapi.rest.responses.TokenResponse;
import com.creditproject.creditserviceapi.services.IUserService;
import com.creditproject.creditserviceapi.services.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class AuthRestControllerTest {

  IUserService service;
  AuthRestController controller;

  @BeforeEach
  void setUp() {
    service = mock(UserServiceImpl.class);
    controller = new AuthRestController(service);
  }

  @Test
  void shouldReturnRegister() {
    when(service.register(any())).thenReturn(new TokenResponse());
    final var result =
        controller.register(RegisterRequestMockBuilder.builder().defaultValues().build());
    assertNotNull(result);
    assertNotNull(result.getBody());
    assertEquals(HttpStatus.OK, result.getStatusCode());
  }

  @Test
  void shouldReturnAuthentitcate() {
    when(service.authenticate(any())).thenReturn(new TokenResponse());
    final var result =
        controller.authenticate(AuthenticationRequestMockBuilder.builder().defaultValues().build());
    assertNotNull(result);
    assertNotNull(result.getBody());
    assertEquals(HttpStatus.OK, result.getStatusCode());
  }
}
