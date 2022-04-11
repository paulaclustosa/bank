package com.letscode.bank.account.dto;

import com.letscode.bank.account.model.Account;
import com.letscode.bank.user.dto.UserMapper;

public class AccountMapper {

  private AccountMapper() { }

  public static Account toAccount(AccountRequest accountRequest) {
    Account account = new Account();
    account.setBranchNumber(accountRequest.getBranchNumber());
    account.setNumber(accountRequest.getNumber());
    account.setType(accountRequest.getType());
    account.setBalance(accountRequest.getBalance());
    return account;
  }

  public static AccountResponse toAccountResponse(Account account) {
    return AccountResponse.builder()
        .id(account.getId())
        .branchNumber(account.getBranchNumber())
        .number(account.getNumber())
        .type(account.getType())
        .balance(account.getBalance())
        .createdDate(account.getCreatedDate())
        .updatedDate(account.getUpdatedDate())
        .user(UserMapper.toUserResponse(account.getUser()))
        .build();
  }
}
