//package com.tsipadan.controller;
//
//import com.tsipadan.exception.ResourceCreationException;
//import com.tsipadan.exception.ResourceUpdateException;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//import javax.servlet.http.HttpServletRequest;
//
//@Slf4j
//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//  /**
//   * Default handler for all exceptions
//   *
//   * @param request - request
//   * @param e - exception
//   * @return error.jsp
//   */
//  @ExceptionHandler(value = Exception.class)
//  public String getAllException(HttpServletRequest request, Exception e){
//    log.warn("Request: " + request.getRequestURI() + " catch something like -> " + e);
//    return "error";
//  }
//
//  /**
//   * Handler for Create exception
//   *
//   * @param request - request
//   * @param e - exception
//   * @return error.jsp
//   */
//  @ExceptionHandler(value = ResourceCreationException.class)
//  public String getResourceCreationException(HttpServletRequest request, Exception e){
//    log.warn("Request: " + request.getRequestURI() + " catch something like -> " + e);
//    return "error";
//  }
//
//  /**
//   * Handler for Update exception
//   *
//   * @param request - request
//   * @param e - exception
//   * @return error.jsp
//   */
//  @ExceptionHandler(value = ResourceUpdateException.class)
//  public String getResourceUpdateException(HttpServletRequest request, Exception e){
//    log.warn("Request: " + request.getRequestURI() + " catch something like -> " + e);
//    return "error";
//  }
//
//}
