package com.dreampro.ws.controllers;

import javax.validation.Valid;

import com.dreampro.ws.dtos.GenericResponse;
import com.dreampro.ws.dtos.user.UserIn;
import com.dreampro.ws.dtos.user.UserOut;
import com.dreampro.ws.services.ifc.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * Manage all actions on user entities objects
 * 
 * @author Victorien KONGNUY
 *
 */

@RestController
@RequestMapping("/v1/users")
@Api("manage all user actions")
public class UserController {

  @Autowired
  UserService userService;

  @PostMapping("")
  @ApiOperation(value = "add a new user", response = UserOut.class)
  public ResponseEntity<?> add(@Valid @RequestBody final UserIn userIn) {
    return ResponseEntity.ok(new GenericResponse<>(userService.create(userIn)));
  }

  @GetMapping("")
  @ApiOperation(value = "get all users", response = UserOut.class)
  public ResponseEntity<?> all() {
    return ResponseEntity.ok(new GenericResponse<>(userService.findAll(true)));
  }

  @GetMapping("/{id}")
  @ApiOperation(value = "get one user", response = UserOut.class)
  public ResponseEntity<?> one(@PathVariable final Long id) {
    return ResponseEntity.ok(new GenericResponse<>(userService.findOne(id)));
  }

  @PutMapping("/{id}")
  @ApiOperation(value = "update a new user", response = UserOut.class)
  public ResponseEntity<?> update(@Valid @RequestBody final UserIn userUpdateIn, @PathVariable final Long id) {
    return ResponseEntity.ok(new GenericResponse<>(userService.update(id, userUpdateIn, null)));
  }

}
