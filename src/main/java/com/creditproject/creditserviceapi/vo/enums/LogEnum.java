package com.creditproject.creditserviceapi.vo.enums;

import lombok.Getter;

@Getter
public enum LogEnum {
  LOGIN_ERROR("Error in login with account: s%");
  private final String value;

  LogEnum(final String value) {
    this.value = value;
  }

  public String formattedMessage(Object... params) {
    return String.format(getValue(), params);
  }
}
