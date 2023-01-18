package com.creditproject.creditserviceapi.rest.responses;

import com.creditproject.creditserviceapi.rest.responses.enums.MessageEnum;
import java.util.List;
import lombok.*;

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
