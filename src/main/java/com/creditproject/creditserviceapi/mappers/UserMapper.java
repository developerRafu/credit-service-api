package com.creditproject.creditserviceapi.mappers;

import com.creditproject.creditserviceapi.domain.User;
import com.creditproject.creditserviceapi.rest.requests.ClientRegisterRequest;
import com.creditproject.creditserviceapi.rest.requests.RegisterRequest;
import com.creditproject.creditserviceapi.vo.UserAuth;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
  User toUser(final RegisterRequest request);

  User toClient(final ClientRegisterRequest request);

  UserAuth toUserAuth(final User user);
}
