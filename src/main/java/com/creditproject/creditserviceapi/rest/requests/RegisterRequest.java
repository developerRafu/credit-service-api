package com.creditproject.creditserviceapi.rest.requests;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@NotNull
public class RegisterRequest {
  private String name;
  private String email;
  private String password;
  private String documento;
}
