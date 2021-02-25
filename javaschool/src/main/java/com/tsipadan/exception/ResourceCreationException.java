package com.tsipadan.exception;

public class ResourceCreationException extends DefaultException {

  public ResourceCreationException(String message) {
    super(message);
  }

  public ResourceCreationException(String message, Throwable cause) {
    super(message, cause);
  }
}
