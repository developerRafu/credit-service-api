package com.creditproject.creditserviceapi.exceptions;

import lombok.Getter;

@Getter
public class UserNotFound extends RuntimeException {
  private final String username;

  public UserNotFound(final String username) {
    this.username = username;
  }
}
