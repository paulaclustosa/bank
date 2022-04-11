package com.letscode.bank.user.dto;

import lombok.Getter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
public class UserRequest {
  @NotEmpty
  private String name;
  @CPF
  private String cpf;
  @NotEmpty
  @Size(min = 4, max = 8, message = "Password must be between 4 and 8 characters long.")
  private String password;
}
