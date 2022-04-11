package com.letscode.bank.account.projection;

import com.letscode.bank.account.model.Type;
import com.letscode.bank.user.projection.UserView;

import java.math.BigDecimal;

public interface AccountView {
  Integer getBranchNumber();
  Integer getNumber();
  Type getType();
  BigDecimal getBalance();
  UserView getUser();
}
