package com.creditproject.creditserviceapi.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.creditproject.creditserviceapi.components.JWTUtil;
import com.creditproject.creditserviceapi.domain.Client;
import com.creditproject.creditserviceapi.exceptions.InvalidTokenException;
import com.creditproject.creditserviceapi.helpers.ClientMockBuilder;
import com.creditproject.creditserviceapi.helpers.UserAuthMockBuilder;
import com.creditproject.creditserviceapi.repositories.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClientServiceTest {

  JWTUtil jwtUtil;
  ClientRepository repository;
  ClientService service;

  @BeforeEach
  void setUp() {
    jwtUtil = mock(JWTUtil.class);
    repository = mock(ClientRepository.class);
    service = new ClientService(jwtUtil, repository);
  }

  @Test
  void shouldReturnClient() {
    final var userAuth = UserAuthMockBuilder.builder().defaultValues().build();
    final var client = spy(ClientMockBuilder.builder().defaultValues().build());
    when(jwtUtil.getAuth()).thenReturn(userAuth);
    when(repository.save(any())).thenReturn(client);
    final var result = service.save(client);
    assertNotNull(result);
    assertNotNull(result.getUserId());
  }

  @Test
  void shouldThrowsExceptionWhenJwtUtilDoIt() {
    when(jwtUtil.getAuth()).thenThrow(InvalidTokenException.class);
    assertThrows(InvalidTokenException.class, () -> service.save(new Client()));
  }
}
