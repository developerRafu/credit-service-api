package com.creditproject.creditserviceapi.configs;

import com.creditproject.creditserviceapi.rest.requests.RegisterRequest;
import com.creditproject.creditserviceapi.rest.responses.TokenResponse;
import com.creditproject.creditserviceapi.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DbConfig {
  private final IUserService userService;

  @Bean
  public TokenResponse saveFirstUser() {
    final var request =
        RegisterRequest.builder()
            .name("first user")
            .email("user@mail.com")
            .password("password")
            .build();
    return userService.register(request);
  }
}
