package com.dreampro.ws.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

import com.dreampro.ws.utils.Messages;

/**
 * @author Victorien KONGNUY
 * 
 *         This is class is required for creating a response containing the
 *         result to be returned to the client
 *
 */
@Data
@AllArgsConstructor
public class GenericResponse<T> implements Serializable {

  private static final long serialVersionUID = 1L;

  private String status;
  private T data;

  public GenericResponse(T data) {
    setStatus(Messages.SUCCESS);
    setData(data);
  }
}