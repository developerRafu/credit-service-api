package com.creditproject.creditserviceapi.controllers;

import com.creditproject.creditserviceapi.rest.requests.AuthenticationRequest;
import com.creditproject.creditserviceapi.rest.requests.RegisterRequest;
import com.creditproject.creditserviceapi.rest.responses.TokenResponse;
import com.creditproject.creditserviceapi.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthRestController {
  private final IUserService userService;

  @PostMapping(value = "/register")
  public ResponseEntity<TokenResponse> register(@RequestBody RegisterRequest request) {
    return ResponseEntity.ok(userService.register(request));
  }

  @PostMapping(value = "/authenticate")
  public ResponseEntity<TokenResponse> authenticate(@RequestBody AuthenticationRequest request) {
    return ResponseEntity.ok(userService.authenticate(request));
  }
}
