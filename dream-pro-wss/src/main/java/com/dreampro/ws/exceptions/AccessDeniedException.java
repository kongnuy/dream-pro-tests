/**
 * 
 */
package com.dreampro.ws.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Victorien KONGNUY
 *
 *         return this exception when request return an access denied
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AccessDeniedException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public AccessDeniedException(String message) {
    super(message);
  }

}
