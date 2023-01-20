package com.creditproject.creditserviceapi.controllers;

import com.creditproject.creditserviceapi.mappers.ClientMapper;
import com.creditproject.creditserviceapi.rest.requests.ClientRegisterRequest;
import com.creditproject.creditserviceapi.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/clients")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClientRestController {
  private final ClientService service;
  private final ClientMapper mapper;

  @PostMapping
  public ResponseEntity<Void> post(@RequestBody final ClientRegisterRequest request) {
    final var client = service.save(mapper.toClient(request));
    return ResponseEntity.noContent().build();
  }
}
