package com.creditproject.creditserviceapi.services;

import static com.creditproject.creditserviceapi.helpers.UserConstantsEnum.USERNAME;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.creditproject.creditserviceapi.domain.User;
import com.creditproject.creditserviceapi.exceptions.UserNotFound;
import com.creditproject.creditserviceapi.helpers.UserMockBuilder;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

class UserDetailServiceImplTest {
  IUserService userService;
  UserDetailsService service;

  @BeforeEach
  void setUp() {
    userService = mock(UserServiceImpl.class);
    service = new UserDetailServiceImpl(userService);
  }

  @Test
  void shouldReturnAUserDetail() {
    final var user = UserMockBuilder.builder().defaultValues().build();
    when(userService.findByEmail(anyString())).thenReturn(Optional.of(user));
    final var result = service.loadUserByUsername(USERNAME.getValueString());
    assertNotNull(result);
    assertUser(user, result);
  }

  @Test
  void shouldThrowsExceptionWhenNotFoundUser() {
    when(userService.findByEmail(anyString())).thenReturn(Optional.empty());
    assertThrows(UserNotFound.class, () -> service.loadUserByUsername(USERNAME.getValueString()));
  }

  private void assertUser(final User user, final UserDetails result) {
    assertEquals(user.getEmail(), result.getUsername());
    assertEquals(user.getPassword(), result.getPassword());
    assertEquals(1, result.getAuthorities().size());
  }
}
