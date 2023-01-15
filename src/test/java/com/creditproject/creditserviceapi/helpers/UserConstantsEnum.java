package com.creditproject.creditserviceapi.helpers;

import lombok.Getter;

@Getter
public enum UserConstantsEnum {
  USERNAME("user@mail.com", null),
  PASSWORD("password", null),
  NAME("first user", null),
  TOKEN(
      "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyQG1haWwuY29tIiwiZXhwIjoxNjczODA3MjUwfQ.787C9bLiA5Zy1SNDwlxOwo31KsxjStVf0_bkx8wy4deWl6vcMnEeZd9Keoz_XkRcQlBIJluQknzFEpYmLgqqEQ",
      null),
  TOKEN_2(
      "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyQG1haWwuY29tIiwiZXhwIjotOTIyMzM3MDM2MzA0NzA5Mn0.JHDMPCHnoaOol3HsGmzic-AfHGat7BOChpr1kFmJm-3T6k1wkiTzD4B_nogT6BZxNM-IRb6K1vy1xA83l44UzA",
      null);
  private final String valueString;
  private final Integer valueInt;

  UserConstantsEnum(final String valueString, final Integer valueInt) {
    this.valueString = valueString;
    this.valueInt = valueInt;
  }
}
