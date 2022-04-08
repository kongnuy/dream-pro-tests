/**
 * 
 */
package com.dreampro.ws.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author willer
 *
 *         return this exception when request like findById given no data
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntitiesNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public EntitiesNotFoundException(String message) {
    super(message);
  }

}
