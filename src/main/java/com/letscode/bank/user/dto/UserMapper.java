package com.letscode.bank.user.dto;

import com.letscode.bank.user.model.User;

public class UserMapper {

  private UserMapper() { }

  public static User toUser(UserRequest userRequest) {
    User user = new User();
    user.setName(userRequest.getName());
    user.setCpf(userRequest.getCpf());
    user.setPassword(userRequest.getPassword());
    return user;
  }

  public static UserResponse toUserResponse(User user) {
    return UserResponse.builder()
        .id(user.getId())
        .name(user.getName())
        .cpf(user.getCpf())
        .createdDate(user.getCreatedDate())
        .updatedDate(user.getUpdatedDate())
        .build();
  }

}
