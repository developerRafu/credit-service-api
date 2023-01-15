package com.creditproject.creditserviceapi.exceptions;

public class BadCredentialsException extends RuntimeException {
  public BadCredentialsException(final Exception ex) {
    super(ex);
  }
}
