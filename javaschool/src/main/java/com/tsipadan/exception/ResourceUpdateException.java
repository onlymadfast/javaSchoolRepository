package com.tsipadan.exception;

public class ResourceUpdateException extends DefaultException {

  public ResourceUpdateException(String message) {
    super(message);
  }

  public ResourceUpdateException(String message, Throwable cause) {
    super(message, cause);
  }
}
