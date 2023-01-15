package com.creditproject.creditserviceapi.exceptions;

import com.creditproject.creditserviceapi.rest.responses.Message;
import com.creditproject.creditserviceapi.rest.responses.enums.MessageEnum;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;

@RestControllerAdvice
public class Handler {
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Message> handleDataIntegrityViolationException(final DataIntegrityViolationException ex, HttpServletRequest request) {
        final var message = Message.builder().text(getMessageDataIntegrity(ex)).code(HttpStatus.BAD_REQUEST.value()).details(Collections.emptyList()).type(MessageEnum.ERROR).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    private String getMessageDataIntegrity(final DataIntegrityViolationException ex) {
        if (ex.getMessage().contains("email")) {
            return "Email repetido";
        }
        return "";
    }
}
