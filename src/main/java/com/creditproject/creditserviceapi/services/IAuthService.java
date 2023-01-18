package com.creditproject.creditserviceapi.services;

import com.creditproject.creditserviceapi.rest.requests.AuthenticationRequest;
import com.creditproject.creditserviceapi.rest.requests.RegisterRequest;
import com.creditproject.creditserviceapi.rest.responses.TokenResponse;

public interface IAuthService {
  TokenResponse register(final RegisterRequest request);

  TokenResponse authenticate(AuthenticationRequest request);
}
