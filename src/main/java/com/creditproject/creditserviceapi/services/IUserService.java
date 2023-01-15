package com.creditproject.creditserviceapi.services;

import com.creditproject.creditserviceapi.domain.User;
import java.util.Optional;

public interface IUserService {
  User save(final User user);

  Optional<User> findByEmail(String username);
}
