package com.creditproject.creditserviceapi.services;

import com.creditproject.creditserviceapi.components.JWTUtil;
import com.creditproject.creditserviceapi.domain.Client;
import com.creditproject.creditserviceapi.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClientService {
  private final JWTUtil jwtUtil;
  private final ClientRepository repository;

  public Client save(final Client client) {
    final var user = jwtUtil.getAuth();
    client.setUserId(user.getId());
    return repository.save(client);
  }

  public Page<Client> findAllByUserLogged(final PageRequest page) {
    final var user = jwtUtil.getAuth();
    return repository.findAllByUserId(user.getId(), page);
  }
}
