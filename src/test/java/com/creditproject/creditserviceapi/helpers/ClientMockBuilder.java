package com.creditproject.creditserviceapi.helpers;

import com.creditproject.creditserviceapi.domain.Client;
import com.creditproject.creditserviceapi.helpers.enums.ClientContanstsEnum;

public class ClientMockBuilder {
  Client.ClientBuilder clientBuilder;

  private ClientMockBuilder() {
    clientBuilder = Client.builder();
  }

  public static ClientMockBuilder builder() {
    return new ClientMockBuilder();
  }

  public Client build() {
    return clientBuilder.build();
  }

  public ClientMockBuilder defaultValues() {
    clientBuilder.id(ClientContanstsEnum.ID.getLongValue());
    clientBuilder.email(ClientContanstsEnum.USERNAME.getValueString());
    clientBuilder.document(ClientContanstsEnum.DOCUMENT.getValueString());
    clientBuilder.name(ClientContanstsEnum.NAME.getValueString());
    return this;
  }
}
