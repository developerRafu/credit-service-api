package com.creditproject.creditserviceapi.configs;

import com.creditproject.creditserviceapi.rest.requests.RegisterRequest;
import com.creditproject.creditserviceapi.rest.responses.TokenResponse;
import com.creditproject.creditserviceapi.services.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DbConfig {
  private final IAuthService authService;

  @Bean
  public TokenResponse saveFirstUser() {
    final var request =
        RegisterRequest.builder().name("first user").email("user@mail.com").password("123").build();
    return authService.register(request);
  }
}
