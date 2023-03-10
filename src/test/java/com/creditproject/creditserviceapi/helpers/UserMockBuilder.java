package com.creditproject.creditserviceapi.helpers;

import static com.creditproject.creditserviceapi.helpers.enums.UserConstantsEnum.*;

import com.creditproject.creditserviceapi.domain.User;
import com.creditproject.creditserviceapi.domain.enums.Profile;
import java.util.Set;

public class UserMockBuilder {
  private final User.UserBuilder user;

  private UserMockBuilder() {
    this.user = User.builder();
  }

  public static UserMockBuilder builder() {
    return new UserMockBuilder();
  }

  public User build() {
    return user.build();
  }

  public UserMockBuilder defaultValues() {
    this.user
        .id(1L)
        .email(USERNAME.getValueString())
        .password(PASSWORD.getValueString())
        .name(NAME.getValueString())
        .profiles(Set.of(Profile.USER));
    return this;
  }

  public UserMockBuilder withProfile(final Profile profile) {
    this.user.profiles(Set.of(profile));
    return this;
  }

  public UserMockBuilder withProfileEmpty() {
    this.user.profiles(Set.of());
    return this;
  }
}
