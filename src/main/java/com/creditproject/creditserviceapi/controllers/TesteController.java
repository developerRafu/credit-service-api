package com.creditproject.creditserviceapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
public class TesteController {
    ResponseEntity<String> get() {
        return ResponseEntity.ok("<h1>oii teste</h1>");
    }
}
