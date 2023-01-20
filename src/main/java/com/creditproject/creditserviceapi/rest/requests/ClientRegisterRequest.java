package com.creditproject.creditserviceapi.rest.requests;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@NotNull
public class ClientRegisterRequest {
  private String name;
  private String email;
  private String documento;
}
