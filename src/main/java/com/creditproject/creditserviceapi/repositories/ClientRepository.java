package com.creditproject.creditserviceapi.repositories;

import com.creditproject.creditserviceapi.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {}
