package com.letscode.bank.account.service;

import com.letscode.bank.account.model.Account;
import com.letscode.bank.account.model.Type;
import com.letscode.bank.account.projection.AccountView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
  Account create(Integer userId, Account account);
  Account getById(Integer id);
  List<Account> getAll();
  Account update(Integer id, Account account);
  void delete(Integer id);
  Boolean existsById(Integer id);

  List<Account> findByTypeEquals(Type type);

  Page<Account> findByBalanceBetween(BigDecimal min, BigDecimal max, Pageable pageable);

  List<Account> findByCreatedDateDesc();
  List<Account> findByNameCaseInsensitive(String userAccountName);

  List<AccountView> findAllViewByType(Type type);
}
