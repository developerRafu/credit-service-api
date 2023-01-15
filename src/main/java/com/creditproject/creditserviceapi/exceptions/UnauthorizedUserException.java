package com.creditproject.creditserviceapi.exceptions;

import org.springframework.security.core.AuthenticationException;

public class UnauthorizedUserException extends RuntimeException {
  public UnauthorizedUserException(final AuthenticationException exception) {
    super(exception);
  }

  public UnauthorizedUserException(final RuntimeException ex){
    super(ex);
  }
}
