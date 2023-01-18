package com.creditproject.creditserviceapi.mappers;

import com.creditproject.creditserviceapi.domain.User;
import com.creditproject.creditserviceapi.rest.requests.RegisterRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
  User toUser(final RegisterRequest request);
}
