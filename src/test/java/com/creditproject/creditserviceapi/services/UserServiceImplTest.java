package com.creditproject.creditserviceapi.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.creditproject.creditserviceapi.components.JWTUtil;
import com.creditproject.creditserviceapi.domain.enums.Profile;
import com.creditproject.creditserviceapi.exceptions.UserNotAvailableException;
import com.creditproject.creditserviceapi.helpers.UserMockBuilder;
import com.creditproject.creditserviceapi.mappers.UserMapper;
import com.creditproject.creditserviceapi.repositories.UserRepository;
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
}
