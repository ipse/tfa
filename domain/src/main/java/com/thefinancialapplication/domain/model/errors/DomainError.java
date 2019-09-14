package com.thefinancialapplication.domain.model.errors;

public abstract class DomainError extends RuntimeException {

  public DomainError(String message) {
    super(message);
  }
}
