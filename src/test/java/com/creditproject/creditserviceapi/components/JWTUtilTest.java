package com.creditproject.creditserviceapi.components;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.ReflectionTestUtils.setField;

import com.creditproject.creditserviceapi.exceptions.InvalidProfileException;
import com.creditproject.creditserviceapi.exceptions.InvalidTokenException;
import com.creditproject.creditserviceapi.helpers.UserMockBuilder;
import com.creditproject.creditserviceapi.helpers.enums.UserConstantsEnum;
import com.creditproject.creditserviceapi.vo.enums.RequestEnums;
import com.creditproject.creditserviceapi.vo.enums.TokenEnum;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JWTUtilTest {
  JWTUtil jwtUtil;
  HttpServletRequest request;
  private static final String FIELD_SECRET = "secret";
  private static final String FIELD_EXPIRATION = "expiration";

  @BeforeEach
  void setUp() {
    request = mock(HttpServletRequest.class);
    jwtUtil = new JWTUtil(request);
    setField(jwtUtil, FIELD_SECRET, "supersecretjojotinaandprojectcredit");
    setField(jwtUtil, FIELD_EXPIRATION, 86400000L);
  }

  @Test
  void shouldReturnToken() {
    final var result = jwtUtil.getToken(UserMockBuilder.builder().defaultValues().build());
    assertNotNull(result);
    assertNotNull(result.getToken());
  }

  @Test
  void shouldFalseReturnIfTokenItIsExpired() {
    final var result = jwtUtil.isValid(UserConstantsEnum.TOKEN.getValueString());
    assertFalse(result);
  }

  @Test
  void shouldTrueReturnIfTokenItIsNotExpired() {
    final var result =
        jwtUtil.isValid(
            jwtUtil.getToken(UserMockBuilder.builder().defaultValues().build()).getToken());
    assertTrue(result);
  }

  @Test
  void shouldFalseWhenTokenIsEmpty() {
    final var result = jwtUtil.isValid("");
    assertFalse(result);
  }

  @Test
  void shouldFalseWhenTokenIsNull() {
    final var result = jwtUtil.isValid(null);
    assertFalse(result);
  }

  @Test
  void shouldThrowExceptionWhenProfilesIsNull() {
    final var user = UserMockBuilder.builder().defaultValues().withProfileEmpty().build();
    assertThrows(InvalidProfileException.class, () -> jwtUtil.getToken(user));
  }

  @Test
  void shouldReturnUserAuth() {
    final var token = jwtUtil.getToken(UserMockBuilder.builder().defaultValues().build());
    when(request.getHeader(RequestEnums.AUTHORIZATION_HEADER.getValue()))
        .thenReturn(token.getType() + token.getToken());
    final var result = jwtUtil.getAuth();
    assertNotNull(result);
    assertNotNull(result.getEmail());
    assertNotNull(result.getId());
    assertNotNull(result.getRole());
  }

  @Test
  void shouldThrowsExceptionWhenHeaderIsNull() {
    assertThrows(InvalidTokenException.class, () -> jwtUtil.getAuth());
  }

  @Test
  void shouldThrowsExceptionWhenClaimsIsNull() {
    when(request.getHeader(anyString()))
        .thenReturn(TokenEnum.BEARER.getValue() + UserConstantsEnum.TOKEN);
    assertThrows(InvalidTokenException.class, () -> jwtUtil.getAuth());
  }

  @Test
  void shouldReturnNullWhenClaimsIsNull() {
    final var result = jwtUtil.getUsername("");
    assertNull(result);
  }
}
