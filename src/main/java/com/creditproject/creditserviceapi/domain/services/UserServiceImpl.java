package com.creditproject.creditserviceapi.domain.services;

import com.creditproject.creditserviceapi.domain.User;
import com.creditproject.creditserviceapi.domain.enums.Profile;
import com.creditproject.creditserviceapi.domain.repositories.UserRepository;
import com.creditproject.creditserviceapi.exceptions.UserNotAvailableException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements IUserService {
  private final UserRepository repository;

  @Override
  public User save(final User user) {
    if(Profile.isAdmin(user.getProfiles())){
      throw new UserNotAvailableException();
    }
    return repository.save(user);
  }
}
