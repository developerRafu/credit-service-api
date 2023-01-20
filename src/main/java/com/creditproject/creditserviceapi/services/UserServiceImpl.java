package com.creditproject.creditserviceapi.services;

import com.creditproject.creditserviceapi.components.JWTUtil;
import com.creditproject.creditserviceapi.domain.User;
import com.creditproject.creditserviceapi.domain.enums.Profile;
import com.creditproject.creditserviceapi.exceptions.UserNotAvailableException;
import com.creditproject.creditserviceapi.exceptions.UserNotFound;
import com.creditproject.creditserviceapi.mappers.UserMapper;
import com.creditproject.creditserviceapi.repositories.UserRepository;
import com.creditproject.creditserviceapi.rest.requests.AuthenticationRequest;
import com.creditproject.creditserviceapi.rest.requests.RegisterRequest;
import com.creditproject.creditserviceapi.rest.responses.TokenResponse;
import java.util.Optional;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements IUserService {
  private final UserRepository repository;
  private final UserMapper mapper;
  private final AuthenticationManager authenticationManager;
  private final JWTUtil jwtUtil;
  private final PasswordEncoder passwordEncoder;

  @Override
  public User save(final User user) {
    if (Profile.isAdmin(user.getProfiles())) {
      throw new UserNotAvailableException();
    }
    return repository.save(user);
  }

  @Override
  public Optional<User> findByEmail(final String email) {
    return repository.findByEmail(email);
  }

  @Override
  public TokenResponse register(final RegisterRequest request) {
    final var user = mapper.toUser(request);
    user.setProfiles(Set.of(Profile.USER));
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    save(user);
    return jwtUtil.getToken(user);
  }

  @Override
  public TokenResponse authenticate(final AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
    final var user = this.findByEmail(request.getEmail());
    return user.map(jwtUtil::getToken).orElseThrow(() -> new UserNotFound(request.getEmail()));
  }
}
