package com.creditproject.creditserviceapi.helpers;

import com.creditproject.creditserviceapi.helpers.enums.ClientContanstsEnum;
import com.creditproject.creditserviceapi.rest.requests.ClientRegisterRequest;

public class ClientRegisterRequestMockBuilder {
  private final ClientRegisterRequest request;

  private ClientRegisterRequestMockBuilder() {
    request = new ClientRegisterRequest();
  }

  public static ClientRegisterRequestMockBuilder builder() {
    return new ClientRegisterRequestMockBuilder();
  }

  public ClientRegisterRequestMockBuilder defaultValues() {
    request.setEmail(ClientContanstsEnum.USERNAME.getValueString());
    request.setName(ClientContanstsEnum.NAME.getValueString());
    request.setDocumento(ClientContanstsEnum.DOCUMENT.getValueString());
    return this;
  }

  public ClientRegisterRequest build() {
    return request;
  }
}
