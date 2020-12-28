package com.vkim.norfia.util;

public enum ErrorMessages {

  ENTITY_NOT_FOUND("entity not found by id: %d");

  private String message;

  ErrorMessages(String message) {
    this.message = message;
  }

  public String getMessage(Object... parameters) {
    return String.format(message, parameters);
  }

}
