package com.letscode.bank.account.dto;

import com.letscode.bank.account.model.Type;
import com.letscode.bank.user.dto.UserResponse;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
public class AccountResponse {
  private Integer id;
  private Integer branchNumber;
  private Integer number;
  private Type type;
  private BigDecimal balance;
  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;
  private UserResponse user;
}
