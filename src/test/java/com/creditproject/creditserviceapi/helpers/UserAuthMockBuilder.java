package com.creditproject.creditserviceapi.helpers;

import com.creditproject.creditserviceapi.helpers.enums.UserConstantsEnum;
import com.creditproject.creditserviceapi.vo.UserAuth;

public class UserAuthMockBuilder {
  UserAuth.UserAuthBuilder userAuth;

  private UserAuthMockBuilder() {
    userAuth = UserAuth.builder();
  }

  public static UserAuthMockBuilder builder() {
    return new UserAuthMockBuilder();
  }

  public UserAuth build() {
    return userAuth.build();
  }

  public UserAuthMockBuilder defaultValues() {
    userAuth.id(UserConstantsEnum.ID.getLongValue());
    userAuth.email(UserConstantsEnum.USERNAME.getValueString());
    userAuth.role(UserConstantsEnum.USER_ROLE.getValueString());
    return this;
  }
}
