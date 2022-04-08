package com.dreampro.ws.dtos.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * This class is required for storing the data we receive from the client.
 * 
 * @author Victorien KONGNUY
 * 
 */

@Data
@ApiModel(description = "all details about create or update user request parameters")
public class UserIn {

  @NotNull(message = "User's password cannot be null")
  @NotBlank(message = "User's password is required")
  @ApiModelProperty(notes = "user's password", required = true)
  String password;

  @NotNull(message = "User's email cannot be null")
  @NotBlank(message = "User's email is required")
  @ApiModelProperty(notes = "user's email", required = true)
  String email;
}
