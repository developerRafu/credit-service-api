package com.creditproject.creditserviceapi.services;

import com.creditproject.creditserviceapi.domain.User;
import com.creditproject.creditserviceapi.rest.requests.AuthenticationRequest;
import com.creditproject.creditserviceapi.rest.requests.RegisterRequest;
import com.creditproject.creditserviceapi.rest.responses.TokenResponse;
import java.util.Optional;

public interface IUserService {
  User save(final User user);

  Optional<User> findByEmail(String username);

  TokenResponse register(final RegisterRequest request);

  TokenResponse authenticate(AuthenticationRequest request);
}
