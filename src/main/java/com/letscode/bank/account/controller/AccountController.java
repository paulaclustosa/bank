package com.letscode.bank.account.controller;

import com.letscode.bank.account.dto.*;
import com.letscode.bank.account.model.Account;
import com.letscode.bank.account.model.Type;
import com.letscode.bank.account.projection.AccountView;
import com.letscode.bank.account.service.AccountService;
import com.letscode.bank.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/accounts")
public class AccountController {

  private static final String ACCOUNT_NOT_FOUND = "Account not found!";
  private static final String USER_NOT_FOUND = "User not found!";
  private static final String ACCOUNT_DELETED = "Account has been successfully deleted!";
  private static final String NO_USER_WITH_KEYWORD = "There aren't users registered with the entered keyword!";

  private final AccountService accountService;
  private final UserService userService;

  @Autowired
  public AccountController(AccountService accountService, UserService userService) {
    this.accountService = accountService;
    this.userService = userService;
  }

  @PostMapping("/{userId}")
  public ResponseEntity<Object> create(
      @PathVariable(value = "userId") Integer userId,
      @Valid @RequestBody AccountRequest accountRequest,
      UriComponentsBuilder uriComponentsBuilder
      ) {
    if (Boolean.FALSE.equals(userService.existsById(userId)))
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(USER_NOT_FOUND);
    else {
      Account newAccount = accountService.create(userId, AccountMapper.toAccount(accountRequest));
      URI uri = uriComponentsBuilder.path("/accounts/{userId}/{accountId}").buildAndExpand(userId, newAccount.getId()).toUri();
      return ResponseEntity.created(uri).body(AccountMapper.toAccountResponse(newAccount));
    }
  }

  @GetMapping()
  public ResponseEntity<List<AccountResponse>> getAll() {
    List<Account> accounts = accountService.getAll();
    return ResponseEntity.ok().body(accounts.stream().map(AccountMapper::toAccountResponse).toList());
  }

  @GetMapping("/{accountId}")
  public ResponseEntity<Object> getById(
      @PathVariable(value = "accountId") Integer accountId
  ) {
    if (Boolean.FALSE.equals(accountService.existsById(accountId)))
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ACCOUNT_NOT_FOUND);
    else {
      Account accountFound = accountService.getById(accountId);
      return ResponseEntity.ok().body(AccountMapper.toAccountResponse(accountFound));
    }
  }

  @PutMapping("/{accountId}")
  public ResponseEntity<Object> update(
      @PathVariable Integer accountId,
      @Valid @RequestBody AccountRequest accountRequest
  ) {
    if (Boolean.FALSE.equals(accountService.existsById(accountId)))
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ACCOUNT_NOT_FOUND);
    else {
      Account accountUpdated = accountService.update(accountId, AccountMapper.toAccount(accountRequest));
      return ResponseEntity.ok().body(AccountMapper.toAccountResponse(accountUpdated));
    }
  }

  @DeleteMapping("{accountId}")
  public ResponseEntity<Object> delete(
      @PathVariable Integer accountId
  ) {
    if (Boolean.FALSE.equals(accountService.existsById(accountId)))
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ACCOUNT_NOT_FOUND);
    else {
      accountService.delete(accountId);
      return ResponseEntity.ok().body(ACCOUNT_DELETED);
    }
  }

  @GetMapping("/of-type")
  public ResponseEntity<Object> getByTypeEquals(
      @RequestParam Type type
  ) {
      List<Account> accountsFoundByType = accountService.findByTypeEquals(type);
      if (accountsFoundByType.isEmpty()) return ResponseEntity.ok().body("List of " + type.name() + "'s accounts is empty.");
      return ResponseEntity.ok().body(accountsFoundByType.stream().map(AccountMapper::toAccountResponse).toList());
  }

  @GetMapping("/balance-between")
  public ResponseEntity<Map<String, Object>> getByBalanceBetween(
      @RequestParam(required = false) BigDecimal min,
      @RequestParam(required = false)BigDecimal max,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "3") int size
  ) {
    List<Account> accounts;
    Pageable paging = PageRequest.of(page, size);
    Page<Account> pageAccounts = accountService.findByBalanceBetween(min, max, paging);
    accounts = pageAccounts.getContent();

    Map<String, Object> response = new HashMap<>();
    response.put("accounts", accounts);
    response.put("currentPage", pageAccounts.getNumber());
    response.put("totalItems", pageAccounts.getTotalElements());
    response.put("totalPages", pageAccounts.getTotalPages());

    return ResponseEntity.ok().body(response);
  }

  @GetMapping("/created-date")
  ResponseEntity<List<AccountResponse>> getByCreatedDateDesc() {
    List<Account> accountsSortedByDateDesc = accountService.findByCreatedDateDesc();
    return ResponseEntity.ok().body(accountsSortedByDateDesc.stream().map(AccountMapper::toAccountResponse).toList());
  }

  @GetMapping("/name-case-insensitive")
  ResponseEntity<Object> getByNameCaseInsensitive(
      @RequestParam String userAccountName
  ) {
    List<Account> accountsFound = accountService.findByNameCaseInsensitive(userAccountName);
    if (accountsFound.isEmpty()) return ResponseEntity.ok().body(NO_USER_WITH_KEYWORD);
    else return ResponseEntity.ok().body(accountsFound.stream().map(AccountMapper::toAccountResponse).toList());
  }

  @GetMapping("/view")
  ResponseEntity<Object> getAccountViewByAccountType(
      @RequestParam Type type
  ) {
    List<AccountView> accountsView = accountService.findAllViewByType(type);
    if (accountsView.isEmpty()) return ResponseEntity.ok().body("List of " + type.name() + "'s accounts is empty.");
    return ResponseEntity.ok().body(accountsView);
  }

}
