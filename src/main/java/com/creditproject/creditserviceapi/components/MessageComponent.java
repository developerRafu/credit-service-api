package com.creditproject.creditserviceapi.components;

import com.creditproject.creditserviceapi.rest.responses.Message;
import com.creditproject.creditserviceapi.rest.responses.enums.MessageEnum;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MessageComponent {
  private final MessageSource messageSource;
  private static final Locale DEFAULT_LOCATION_BRAZIL = new Locale("pt", "BR");

  public Message get(String key, MessageEnum messageEnum, Integer code) {
    final var messageText = messageSource.getMessage(key, null, DEFAULT_LOCATION_BRAZIL);
    final var message = new Message();
    message.setText(messageText);
    message.setType(messageEnum);
    message.setCode(code);
    return message;
  }

  public Message get(String key, MessageEnum messageEnum, Integer code, Object... values) {
    final var messageText = messageSource.getMessage(key, values, DEFAULT_LOCATION_BRAZIL);
    final var message = new Message();
    message.setText(messageText);
    message.setType(messageEnum);
    message.setCode(code);
    return message;
  }
}
