package com.creditproject.creditserviceapi.helpers.enums;

import lombok.Getter;

@Getter
public enum RestConstantsEnums {
  DEFAULT_PORT("", 8080);
  private final String stringValue;
  private final Integer intValue;

  RestConstantsEnums(final String stringValue, final Integer intValue) {
    this.stringValue = stringValue;
    this.intValue = intValue;
  }
}
