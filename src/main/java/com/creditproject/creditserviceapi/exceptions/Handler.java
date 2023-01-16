package com.creditproject.creditserviceapi.exceptions;

import com.creditproject.creditserviceapi.components.MessageComponent;
import com.creditproject.creditserviceapi.rest.responses.Message;
import com.creditproject.creditserviceapi.rest.responses.enums.MessageEnum;
import com.creditproject.creditserviceapi.utils.MessageConstantsEnum;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Handler {
  private final MessageComponent messageComponent;

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<Message> handleDataIntegrityViolationException(
      final DataIntegrityViolationException ex, HttpServletRequest request) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getMessageDataIntegrity(ex));
  }

  @ExceptionHandler(UserNotAvailableException.class)
  public ResponseEntity<Message> handleUserNotAvailableException(
      final UserNotAvailableException ex, HttpServletRequest request) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(
            messageComponent.get(
                MessageConstantsEnum.ADMIN_NOT_AVAILABLE.getCode(),
                MessageEnum.ERROR,
                HttpStatus.FORBIDDEN.value()));
  }

  @ExceptionHandler(UserNotFound.class)
  public ResponseEntity<Message> handleUserNotFound(
      final UserNotFound ex, HttpServletRequest request) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(
            messageComponent.get(
                MessageConstantsEnum.USER_NOT_FOUND.getCode(),
                MessageEnum.ERROR,
                HttpStatus.NOT_FOUND.value(),
                ex.getUsername()));
  }

  private Message getMessageDataIntegrity(final DataIntegrityViolationException ex) {
    if (ex.getMessage().contains("email")) {
      return messageComponent.get(
          MessageConstantsEnum.EMAIL_ALREADY_IN_USE.getCode(),
          MessageEnum.ERROR,
          HttpStatus.BAD_REQUEST.value());
    }
    final var message =
        messageComponent.get(
            MessageConstantsEnum.DEFAULT_DATA_INTEGRITY.getCode(),
            MessageEnum.ERROR,
            HttpStatus.INTERNAL_SERVER_ERROR.value());
    message.setDetails(List.of(ex.getLocalizedMessage()));
    return message;
  }
}
