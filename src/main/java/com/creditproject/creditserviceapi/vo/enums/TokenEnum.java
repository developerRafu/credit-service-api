package com.creditproject.creditserviceapi.vo.enums;

import lombok.Getter;

@Getter
public enum TokenEnum {
  BEARER("Bearer ");
  private final String value;

  TokenEnum(final String value) {
    this.value = value;
  }
}
