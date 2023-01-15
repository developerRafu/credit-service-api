package com.creditproject.creditserviceapi.helpers;

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
        .email("user@mail.com")
        .password("password")
        .name("first user")
        .profiles(Set.of(Profile.USER));
    return this;
  }

  public UserMockBuilder withProfile(final Profile profile) {
    this.user.profiles(Set.of(profile));
    return this;
  }
}
