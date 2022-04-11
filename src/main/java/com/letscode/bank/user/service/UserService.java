package com.letscode.bank.user.service;

import com.letscode.bank.user.model.User;

import java.util.List;

public interface UserService {
  User create(User user);
  User getById(Integer id);
  List<User> getAll();
  User update(Integer id, User user);
  void delete(Integer id);
  Boolean existsById(Integer id);
}
