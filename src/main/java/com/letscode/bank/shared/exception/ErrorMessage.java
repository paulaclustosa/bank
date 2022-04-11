package com.letscode.bank.shared.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ErrorMessage {
  private int statusCode;
  private LocalDateTime date;
  private String message;
  private String description;
}
