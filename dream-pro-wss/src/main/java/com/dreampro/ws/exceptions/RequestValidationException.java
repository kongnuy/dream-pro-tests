package com.dreampro.ws.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Victorien KONGNUY
 *
 *         use to notifiy client side that missing mandatory or malformed
 *         parameter
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequestValidationException extends RuntimeException {

  private static final long serialVersionUID = 6634474581466229738L;

  public RequestValidationException(String message) {
    super(message);
  }

}