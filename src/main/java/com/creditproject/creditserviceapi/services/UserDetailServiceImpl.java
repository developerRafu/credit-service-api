package com.creditproject.creditserviceapi.services;

import com.creditproject.creditserviceapi.domain.UserDetailsImpl;
import com.creditproject.creditserviceapi.exceptions.UserNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired, @Lazy}))
public class UserDetailServiceImpl implements UserDetailsService {
  private final IUserService userService;

  @Override
  public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
    return userService
        .findByEmail(username)
        .map(UserDetailsImpl::new)
        .orElseThrow(() -> new UserNotFound(username));
  }
}
