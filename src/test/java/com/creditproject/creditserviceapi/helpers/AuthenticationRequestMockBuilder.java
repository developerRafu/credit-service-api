package com.creditproject.creditserviceapi.helpers;

import com.creditproject.creditserviceapi.helpers.enums.UserConstantsEnum;
import com.creditproject.creditserviceapi.rest.requests.AuthenticationRequest;

public class AuthenticationRequestMockBuilder {
  private final AuthenticationRequest request;

  private AuthenticationRequestMockBuilder() {
    request = new AuthenticationRequest();
  }

  public static AuthenticationRequestMockBuilder builder() {
    return new AuthenticationRequestMockBuilder();
  }

  public AuthenticationRequestMockBuilder defaultValues() {
    request.setEmail(UserConstantsEnum.USERNAME.getValueString());
    request.setPassword(UserConstantsEnum.PASSWORD.getValueString());
    return this;
  }

  public AuthenticationRequest build() {
    return request;
  }
}
