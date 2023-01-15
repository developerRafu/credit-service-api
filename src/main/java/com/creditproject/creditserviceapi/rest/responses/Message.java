package com.creditproject.creditserviceapi.rest.responses;

import com.creditproject.creditserviceapi.rest.responses.enums.MessageEnum;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {
    private String text;
    private MessageEnum type;
    private Integer code;
    private List<String> details;
}

