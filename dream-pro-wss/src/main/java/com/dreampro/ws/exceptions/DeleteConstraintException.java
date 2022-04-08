/**
 * 
 */
package com.dreampro.ws.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Victorien KONGNUY
 *
 *         return this exception when there is a delete constraint from the
 *         database
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class DeleteConstraintException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public DeleteConstraintException(String message) {
    super(message);
  }

}
