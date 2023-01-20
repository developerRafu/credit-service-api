package com.creditproject.creditserviceapi.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import com.creditproject.creditserviceapi.components.JWTUtil;
import com.creditproject.creditserviceapi.domain.enums.Profile;
import com.creditproject.creditserviceapi.exceptions.UserNotAvailableException;
import com.creditproject.creditserviceapi.exceptions.UserNotFound;
import com.creditproject.creditserviceapi.helpers.AuthenticationRequestMockBuilder;
import com.creditproject.creditserviceapi.helpers.RegisterRequestMockBuilder;
import com.creditproject.creditserviceapi.helpers.UserMockBuilder;
import com.creditproject.creditserviceapi.mappers.UserMapper;
import com.creditproject.creditserviceapi.repositories.UserRepository;
import com.creditproject.creditserviceapi.rest.responses.TokenResponse;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

class UserServiceImplTest {
  UserRepository repository;
  UserMapper mapper;
  AuthenticationManager authenticationManager;
  JWTUtil jwtUtil;
  PasswordEncoder passwordEncoder;
  IUserService service;

  @BeforeEach
  void setUp() {
    repository = mock(UserRepository.class);
    mapper = Mappers.getMapper(UserMapper.class);
    authenticationManager = mock(AuthenticationManager.class);
    jwtUtil = mock(JWTUtil.class);
    passwordEncoder = mock(PasswordEncoder.class);
    service =
        new UserServiceImpl(repository, mapper, authenticationManager, jwtUtil, passwordEncoder);
  }

  @Test
  void shouldSave() {
    final var user = UserMockBuilder.builder().defaultValues().build();
    when(repository.save(any())).thenReturn(user);
    final var result = service.save(user);
    assertNotNull(result);
    assertEquals(user, result);
  }

  @Test
  void shouldNotAdminUsers() {
    final var user = UserMockBuilder.builder().defaultValues().withProfile(Profile.ADMIN).build();
    assertThrows(UserNotAvailableException.class, () -> service.save(user));
  }

  @Test
  void shouldRegisterUser() {
    final var request = RegisterRequestMockBuilder.builder().defaultValues().build();
    final var user = UserMockBuilder.builder().defaultValues().build();
    when(passwordEncoder.encode(any())).thenReturn(UUID.randomUUID().toString());
    when(repository.save(any())).thenReturn(user);
    when(jwtUtil.getToken(any())).thenReturn(new TokenResponse());
    final var result = service.register(request);
    assertNotNull(result);
  }

  @Test
  void shouldAuthenticateUser() {
    final var request = AuthenticationRequestMockBuilder.builder().defaultValues().build();
    when(repository.findByEmail(anyString()))
        .thenReturn(Optional.of(UserMockBuilder.builder().defaultValues().build()));
    when(jwtUtil.getToken(any())).thenReturn(new TokenResponse());
    final var result = service.authenticate(request);
    assertNotNull(result);
    verify(authenticationManager).authenticate(any());
  }

  @Test
  void shouldThrowsExceptioNWhenEmailNoFound() {
    final var request = AuthenticationRequestMockBuilder.builder().defaultValues().build();
    when(repository.findByEmail(anyString())).thenReturn(Optional.empty());
    assertThrows(UserNotFound.class, () -> service.authenticate(request));
  }
}
