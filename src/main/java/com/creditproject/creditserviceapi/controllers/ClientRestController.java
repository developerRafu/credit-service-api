package com.creditproject.creditserviceapi.controllers;

import com.creditproject.creditserviceapi.mappers.ClientMapper;
import com.creditproject.creditserviceapi.rest.requests.ClientRegisterRequest;
import com.creditproject.creditserviceapi.rest.responses.ClientResponse;
import com.creditproject.creditserviceapi.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/v1/clients")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClientRestController {
  private final ClientService service;
  private final ClientMapper mapper;

  @PostMapping
  public ResponseEntity<Void> post(@RequestBody final ClientRegisterRequest request) {
    final var client = service.save(mapper.toClient(request));
    final var uri =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(client.getId())
            .toUri();
    return ResponseEntity.created(uri).build();
  }

  @GetMapping
  public ResponseEntity<Page<ClientResponse>> getByUser(
      @RequestParam(defaultValue = "0") final int page) {
    final var clients = service.findAllByUserLogged(PageRequest.of(page, 10));
    return ResponseEntity.ok().body(clients.map(mapper::toResponse));
  }
}
