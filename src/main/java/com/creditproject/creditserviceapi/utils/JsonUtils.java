package com.creditproject.creditserviceapi.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.ObjectUtils;

public class JsonUtils {
  private JsonUtils(){}
  public static String convertToString(final ObjectMapper objectMapper, final Object obj) {
    if (ObjectUtils.isEmpty(obj)) {
      return null;
    }
    try {
      return objectMapper.writeValueAsString(obj);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      return null;
    }
  }
}
