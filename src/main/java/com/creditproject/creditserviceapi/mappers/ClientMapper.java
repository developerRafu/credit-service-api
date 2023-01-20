package com.creditproject.creditserviceapi.mappers;

import com.creditproject.creditserviceapi.domain.Client;
import com.creditproject.creditserviceapi.rest.requests.ClientRegisterRequest;
import com.creditproject.creditserviceapi.rest.responses.ClientResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {
  Client toClient(final ClientRegisterRequest request);

  ClientResponse toResponse(final Client clients);
}
