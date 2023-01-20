package com.creditproject.creditserviceapi.utils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import com.creditproject.creditserviceapi.helpers.UserAuthMockBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JsonUtilsTest {
  ObjectMapper mapper;

  @BeforeEach
  void setUp() {
    mapper = spy(new ObjectMapper());
  }

  @Test
  void shouldReturnAJSon() {
    final var result =
        JsonUtils.convertToString(
            mapper, UserAuthMockBuilder.builder().defaultValues().defaultValues());
    assertNotNull(result);
  }

  @Test
  void shouldReturnNullWhenStringIsEmpty() {
    final var result = JsonUtils.convertToString(mapper, "");
    assertNull(result);
  }

  @Test
  void shouldReturnNullWhenGetsJsonProcessingException() throws JsonProcessingException {
    when(mapper.writeValueAsString(any())).thenThrow(JsonProcessingException.class);
    assertNull(JsonUtils.convertToString(mapper, "dfgdg"));
  }
}
