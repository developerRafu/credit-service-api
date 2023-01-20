package com.creditproject.creditserviceapi.vo;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAuth {
  private Long id;
  private String email;
  private String role;
}
