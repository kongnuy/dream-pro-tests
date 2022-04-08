package com.dreampro.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class DreamProWSApplication {

  public static void main(String[] args) {
    SpringApplication.run(DreamProWSApplication.class, args);
  }

}
