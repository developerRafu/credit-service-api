package com.creditproject.creditserviceapi.rest.responses.enums;

import java.util.Arrays;
import lombok.Getter;

@Getter
public enum MessageEnum {
  SUCCESS(0, "SUCCESS"),
  ERROR(1, "ERROR");

  private int id;
  private String description;

  MessageEnum(int id, String description) {
    this.id = id;
    this.description = description;
  }

  MessageEnum toEnum(int id) {
    return Arrays.stream(MessageEnum.values())
        .filter(messageEnum -> messageEnum.getId() == id)
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Invalid MessageEnum"));
  }

  MessageEnum toEnum(String role) {
    return Arrays.stream(MessageEnum.values())
        .filter(messageEnum -> messageEnum.getDescription().equals(role))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Invalid MessageEnum"));
  }
}
