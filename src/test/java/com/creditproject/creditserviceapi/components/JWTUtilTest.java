package com.creditproject.creditserviceapi.components;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.util.ReflectionTestUtils.setField;

import com.creditproject.creditserviceapi.helpers.UserConstantsEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JWTUtilTest {
  JWTUtil jwtUtil;
  private static final String FIELD_SECRET = "secret";
  private static final String FIELD_EXPIRATION = "expiration";

  @BeforeEach
  void setUp() {
    jwtUtil = new JWTUtil();
    setField(jwtUtil, FIELD_SECRET, "supersecretjojotinaandprojectcredit");
    setField(jwtUtil, FIELD_EXPIRATION, 86400000L);
  }

  @Test
  void shouldReturnToken() {
    final var result = jwtUtil.getToken(UserConstantsEnum.USERNAME.getValueString());
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
        jwtUtil.isValid(jwtUtil.getToken(UserConstantsEnum.USERNAME.getValueString()).getToken());
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
}
