package com.creditproject.creditserviceapi.domain.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.creditproject.creditserviceapi.domain.enums.Profile;
import com.creditproject.creditserviceapi.domain.repositories.UserRepository;
import com.creditproject.creditserviceapi.exceptions.UserNotAvailableException;
import com.creditproject.creditserviceapi.helpers.UserMockBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserServiceImplTest {
    UserRepository repository;
    IUserService service;

    @BeforeEach
    void setUp() {
        repository = mock(UserRepository.class);
        service = new UserServiceImpl(repository);
    }

    @Test
    void shouldSave() {
        final var user = UserMockBuilder.builder().defaultValues().build();
        when(repository.save(any())).thenReturn(user);
        final var result = service.save(user);
        assertNotNull(result);
        assertEquals(user, result);
    }

    @Test
    void shouldNotAdminUsers() {
        final var user = UserMockBuilder.builder().defaultValues().withProfile(Profile.ADMIN).build();
        assertThrows(UserNotAvailableException.class, () -> service.save(user));
    }
}
