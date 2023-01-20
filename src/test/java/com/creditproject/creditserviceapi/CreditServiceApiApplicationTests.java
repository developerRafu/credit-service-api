package com.creditproject.creditserviceapi;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

class CreditServiceApiApplicationTests {

  @BeforeAll
  static void setUp() {
    RestAssured.port = 8080;
  }
}
