package com.creditproject.creditserviceapi.helpers;

import com.creditproject.creditserviceapi.helpers.enums.UserConstantsEnum;
import com.creditproject.creditserviceapi.rest.responses.TokenResponse;
import com.creditproject.creditserviceapi.vo.enums.TokenEnum;

public class TokenResponseMockBuilder {
  TokenResponse.TokenResponseBuilder token;

  private TokenResponseMockBuilder() {
    token = TokenResponse.builder();
  }

  public static TokenResponseMockBuilder builder() {
    return new TokenResponseMockBuilder();
  }

  public TokenResponse build() {
    return token.build();
  }

  public TokenResponseMockBuilder defaultValues() {
    token.type(TokenEnum.BEARER.getValue());
    token.duration(String.valueOf(Long.MAX_VALUE));
    token.token(UserConstantsEnum.TOKEN_2.getValueString());
    return this;
  }
}
