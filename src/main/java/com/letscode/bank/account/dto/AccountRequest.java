package com.letscode.bank.account.dto;

import com.letscode.bank.account.model.Type;
import lombok.Getter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
public class AccountRequest {
  @Min(1)
  private Integer branchNumber;
  @NotNull
  private Integer number;
  @NotNull
  private Type type;
  @DecimalMin(value = "0.0", inclusive = true)
  private BigDecimal balance;
}
