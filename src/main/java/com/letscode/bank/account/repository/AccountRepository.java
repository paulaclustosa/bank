package com.letscode.bank.account.repository;

import com.letscode.bank.account.model.Account;
import com.letscode.bank.account.model.Type;
import com.letscode.bank.account.projection.AccountView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
  List<Account> findByTypeEquals(Type type);
  Page<Account> findByBalanceBetween(BigDecimal min, BigDecimal max, Pageable pageable);

  @Query("SELECT a FROM Account a ORDER BY a.createdDate DESC")
  List<Account> findByCreatedDateDesc();
  @Query("SELECT a FROM Account a WHERE LOWER(a.user.name) LIKE LOWER(CONCAT('%', ?1, '%'))")
  List<Account> findByNameCaseInsensitive(String userAccountName);

  List<AccountView> findAllByType(Type type);
}
