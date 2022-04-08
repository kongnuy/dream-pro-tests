package com.dreampro.ws.dtos.errors;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Victorien KONGNUY
 * 
 *         This is class is required for creating a response containing the
 *         errors to be returned to the client
 *
 */
@Data
public class ApiError implements Serializable {

  private static final long serialVersionUID = 1L;

  private final String userMessage;
  private final String internalMessage;
  private final int code;
}