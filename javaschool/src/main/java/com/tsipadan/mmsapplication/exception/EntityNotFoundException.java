package com.tsipadan.mmsapplication.exception;

public class EntityNotFoundException extends Exception {

  public EntityNotFoundException(String message) {
    super(message);
  }

  public EntityNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

}
