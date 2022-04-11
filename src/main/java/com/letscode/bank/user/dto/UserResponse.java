package com.letscode.bank.user.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class UserResponse {
  private Integer id;
  private String name;
  private String cpf;
  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;
}
