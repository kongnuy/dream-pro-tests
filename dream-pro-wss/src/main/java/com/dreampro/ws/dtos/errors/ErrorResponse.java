package com.dreampro.ws.dtos.errors;

import lombok.Data;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @author Victorien KONGNUY
 * 
 *         This is class is required for creating a response containing the
 *         errors to be returned to the client
 *
 */
@Data
public class ErrorResponse implements Serializable {

  private static final long serialVersionUID = 1L;

  private final String status;
  private final Iterable<ApiError> errors;

  public ErrorResponse(String status, List<ApiError> errors) {
    super();
    this.status = status;
    this.errors = errors;
  }

  public ErrorResponse(String status, ApiError error) {
    super();
    this.status = status;
    errors = Arrays.asList(error);
  }
}