package com.letscode.bank.user.controller;

import com.letscode.bank.user.dto.*;
import com.letscode.bank.user.model.User;
import com.letscode.bank.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

  private static final String USER_NOT_FOUND = "User not found!";
  private static final String USER_DELETED = "User has been successfully deleted!";

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  public ResponseEntity<UserResponse> create(
      @Valid @RequestBody UserRequest userRequest,
      UriComponentsBuilder uriComponentBuilder
  ) {
    User newUser = userService.create(UserMapper.toUser(userRequest));
    URI uri = uriComponentBuilder.path("/user/{id}").buildAndExpand(newUser.getId()).toUri();
    return ResponseEntity.created(uri).body(UserMapper.toUserResponse(newUser));
  }

  @GetMapping
  public ResponseEntity<List<UserResponse>> getAll() {
    List<User> users = userService.getAll();
    return ResponseEntity.ok().body(users.stream().map(UserMapper::toUserResponse).toList());
  }

  @GetMapping("/{userId}")
  public ResponseEntity<Object> getById(
      @PathVariable(value = "userId") Integer id
  ) {
    if (Boolean.FALSE.equals(userService.existsById(id)))
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(USER_NOT_FOUND);
    else {
      User userFound = userService.getById(id);
      return ResponseEntity.ok().body(UserMapper.toUserResponse(userFound));
    }
  }

  @PutMapping("/{userId}")
  public ResponseEntity<Object> update(
      @PathVariable(value = "userId") Integer id,
      @RequestBody UserRequest userRequest
  ) {
    if (Boolean.FALSE.equals(userService.existsById(id)))
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(USER_NOT_FOUND);
    else {
      User updateUser = userService.update(id, UserMapper.toUser(userRequest));
      return ResponseEntity.ok().body(UserMapper.toUserResponse(updateUser));
    }
  }

  @DeleteMapping("/{userId}")
  public ResponseEntity<Object> delete(
      @PathVariable(value = "userId") Integer id
  ) {
    if (Boolean.FALSE.equals(userService.existsById(id)))
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(USER_NOT_FOUND);
    else {
      userService.delete(id);
      return ResponseEntity.ok().body(USER_DELETED);
    }
  }

}
