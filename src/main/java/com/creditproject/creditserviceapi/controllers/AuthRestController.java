package com.creditproject.creditserviceapi.controllers;

import com.creditproject.creditserviceapi.rest.requests.AuthenticationRequest;
import com.creditproject.creditserviceapi.rest.requests.RegisterRequest;
import com.creditproject.creditserviceapi.rest.responses.TokenResponse;
import com.creditproject.creditserviceapi.services.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthRestController {
    private final IAuthService service;

    @PostMapping("/register")
    public ResponseEntity<TokenResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<TokenResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
