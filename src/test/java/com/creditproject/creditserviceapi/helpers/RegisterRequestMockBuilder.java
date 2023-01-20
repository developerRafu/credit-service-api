package com.creditproject.creditserviceapi.helpers;

import com.creditproject.creditserviceapi.helpers.enums.UserConstantsEnum;
import com.creditproject.creditserviceapi.rest.requests.RegisterRequest;

public class RegisterRequestMockBuilder {
  private final RegisterRequest request;

  private RegisterRequestMockBuilder() {
    request = new RegisterRequest();
  }

  public static RegisterRequestMockBuilder builder() {
    return new RegisterRequestMockBuilder();
  }

  public RegisterRequestMockBuilder defaultValues() {
    request.setEmail(UserConstantsEnum.USERNAME.getValueString());
    request.setPassword(UserConstantsEnum.PASSWORD.getValueString());
    request.setDocumento(UserConstantsEnum.DOCUMENT.getValueString());
    request.setName(UserConstantsEnum.NAME.name());
    return this;
  }

  public RegisterRequest build() {
    return request;
  }
}
