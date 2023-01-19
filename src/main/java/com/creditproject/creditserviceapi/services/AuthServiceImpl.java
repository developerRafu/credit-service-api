package com.creditproject.creditserviceapi.services;

import com.creditproject.creditserviceapi.components.JWTUtil;
import com.creditproject.creditserviceapi.domain.User;
import com.creditproject.creditserviceapi.domain.enums.Profile;
import com.creditproject.creditserviceapi.exceptions.UserNotFound;
import com.creditproject.creditserviceapi.mappers.UserMapper;
import com.creditproject.creditserviceapi.rest.requests.AuthenticationRequest;
import com.creditproject.creditserviceapi.rest.requests.RegisterRequest;
import com.creditproject.creditserviceapi.rest.responses.TokenResponse;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthServiceImpl implements IAuthService {
  private final IUserService userService;
  private final UserMapper userMapper;
  private final JWTUtil jwtUtil;
  private final AuthenticationManager authenticationManager;
  private final PasswordEncoder passwordEncoder;

  @Override
  public TokenResponse register(final RegisterRequest request) {
    final var user = userMapper.toUser(request);
    user.setProfiles(Set.of(Profile.USER));
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    userService.save(user);
    return jwtUtil.getToken(user.getEmail());
  }

  @Override
  public TokenResponse authenticate(final AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
    final var user = userService.findByEmail(request.getEmail());
    return user.map(User::getEmail)
        .map(jwtUtil::getToken)
        .orElseThrow(() -> new UserNotFound(request.getEmail()));
  }
}
