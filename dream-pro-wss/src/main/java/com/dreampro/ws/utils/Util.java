package com.dreampro.ws.utils;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class Util {

  public String uuid() {
    return UUID.randomUUID().toString().replace("-", "");
  }

}
