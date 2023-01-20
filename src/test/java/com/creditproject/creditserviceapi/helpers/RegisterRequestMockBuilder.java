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
    request.setEmail(UserConstantsEnum.USERNAME_2.getValueString());
    request.setPassword(UserConstantsEnum.PASSWORD.getValueString());
    request.setDocumento(UserConstantsEnum.DOCUMENT.getValueString());
    request.setName(UserConstantsEnum.NAME.getValueString());
    return this;
  }

  public RegisterRequest build() {
    return request;
  }

  public RegisterRequestMockBuilder withEmail(final String email) {
    request.setEmail(email);
    return this;
  }
}
