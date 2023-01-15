package com.creditproject.creditserviceapi.rest.responses;

import com.creditproject.creditserviceapi.vo.enums.TokenEnum;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenResponse {
    private String type;
    private String token;
    private String duration;
}
