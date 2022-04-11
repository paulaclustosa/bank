package com.letscode.bank.account.service.impl;

import com.letscode.bank.account.model.Account;
import com.letscode.bank.account.model.Type;
import com.letscode.bank.account.projection.AccountView;
import com.letscode.bank.account.repository.AccountRepository;
import com.letscode.bank.account.service.AccountService;
import com.letscode.bank.user.model.User;
import com.letscode.bank.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

  private final AccountRepository accountRepository;
  private final UserRepository userRepository;

  @Autowired
  public AccountServiceImpl(AccountRepository accountRepository, UserRepository userRepository) {
    this.accountRepository = accountRepository;
    this.userRepository = userRepository;
  }

  @Override
  public Account create(Integer userId, Account account) {
    User user = userRepository.getById(userId);
    account.setUser(user);
    return accountRepository.save(account);
  }

  @Override
  public Account getById(Integer id) {
    return accountRepository.getById(id);
  }

  @Override
  public List<Account> getAll() {
    return accountRepository.findAll();
  }

  @Override
  public Account update(Integer id, Account account) {
    Account foundAccount = accountRepository.getById(id);
    foundAccount.setBranchNumber(account.getBranchNumber());
    foundAccount.setNumber(account.getNumber());
    foundAccount.setType(account.getType());
    foundAccount.setBalance(account.getBalance());
    return accountRepository.save(foundAccount);
  }

  @Override
  public void delete(Integer id) {
    accountRepository.deleteById(id);
  }

  @Override
  public Boolean existsById(Integer id) {
    return accountRepository.existsById(id);
  }

  @Override
  public List<Account> findByTypeEquals(Type type) {
    return accountRepository.findByTypeEquals(type);
  }

  @Override
  public Page<Account> findByBalanceBetween(BigDecimal min, BigDecimal max, Pageable pageable) {
    return accountRepository.findByBalanceBetween(min, max, pageable);
  }

  @Override
  public List<Account> findByCreatedDateDesc() {
    return accountRepository.findByCreatedDateDesc();
  }

  @Override
  public List<Account> findByNameCaseInsensitive(String name) {
    return accountRepository.findByNameCaseInsensitive(name);
  }

  @Override
  public List<AccountView> findAllViewByType(Type type) {
    return accountRepository.findAllByType(type);
  }

}
