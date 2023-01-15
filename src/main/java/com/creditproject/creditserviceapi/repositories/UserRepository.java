package com.creditproject.creditserviceapi.repositories;

import com.creditproject.creditserviceapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}
