package com.creditproject.creditserviceapi.helpers.enums;

import lombok.Getter;

@Getter
public enum ClientContanstsEnum {
  ID(null, null, 1L),
  USERNAME("user@mail.com", null, null),
  DOCUMENT("16808211213", null, null),
  NAME("name", null, null);
  private final String valueString;
  private final Integer valueInt;
  private final Long longValue;

  ClientContanstsEnum(final String valueString, final Integer valueInt, final Long longValue) {
    this.valueString = valueString;
    this.valueInt = valueInt;
    this.longValue = longValue;
  }
}
