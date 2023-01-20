package com.creditproject.creditserviceapi.utils;

import lombok.Getter;

@Getter
public enum MessageConstantsEnum {
  EMAIL_ALREADY_IN_USE("CS-001"),
  DEFAULT_DATA_INTEGRITY("CS-002"),
  ADMIN_NOT_AVAILABLE("CS-003"),
  USER_NOT_FOUND("CS-004"),
  INVALID_PROFILE("CS-005"),
  INVALID_TOKEN("CS-006"),
  INVALID_REQUEST("CS-007"),
  BAD_CREDENTIALS("CS-008");
  private final String code;

  MessageConstantsEnum(final String code) {
    this.code = code;
  }
}
