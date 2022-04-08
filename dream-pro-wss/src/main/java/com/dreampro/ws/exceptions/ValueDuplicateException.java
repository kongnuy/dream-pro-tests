/**
 * 
 */
package com.dreampro.ws.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Victorien KONGNUY
 *
 *         return this exception when there is an entity with the same data
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValueDuplicateException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public ValueDuplicateException(String message) {
    super(message);
  }

}
