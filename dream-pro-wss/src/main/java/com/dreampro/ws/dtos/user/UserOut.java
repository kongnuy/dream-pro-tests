package com.dreampro.ws.dtos.user;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

/*
* @author Victorien KONGNUY 
*
*/
@Data
@NoArgsConstructor
public class UserOut {

  Long id;
  String uuid;
  String email;
  String password;
  Date createdAt;
  Date updatedAt;
}
