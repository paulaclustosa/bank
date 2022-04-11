package com.letscode.bank.user.service.impl;

import com.letscode.bank.user.model.User;
import com.letscode.bank.user.repository.UserRepository;
import com.letscode.bank.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public User create(User user) {
    return userRepository.save(user);
  }

  @Override
  public User getById(Integer id) {
    return userRepository.getById(id);
  }

  @Override
  public List<User> getAll() {
    return userRepository.findAll();
  }

  @Override
  public User update(Integer id, User user) {
    User userFound = userRepository.getById(id);
    userFound.setName(user.getName());
    userFound.setCpf(user.getCpf());
    userFound.setPassword(user.getPassword());
    return userRepository.save(userFound);
  }

  @Override
  public void delete(Integer id) {
    userRepository.deleteById(id);
  }

  @Override
  public Boolean existsById(Integer id) {
    return userRepository.existsById(id);
  }

}
