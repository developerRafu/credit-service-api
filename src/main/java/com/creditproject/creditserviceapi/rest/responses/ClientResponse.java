package com.creditproject.creditserviceapi.rest.responses;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientResponse {
  private Long id;
  private String name;
  private String email;
  private String document;
}
