//package com.tsipadan.mmsapplication.validator;
//
//import com.tsipadan.mmsapplication.model.Client;
//import com.tsipadan.mmsapplication.service.ClientService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//import org.springframework.validation.Errors;
//import org.springframework.validation.Validator;
//
//@Component
//@RequiredArgsConstructor
//public class ClientValidator implements Validator {
//
//  private final ClientService clientService;
//
//  @Override
//  public boolean supports(Class<?> aClass) {
//    return Client.class.equals(aClass);
//  }
//
//  @Override
//  public void validate(Object o, Errors errors) {
//    Client client = (Client) o;
//
//    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName","NotEmpty");
//    if(clients.getFirstName().length()< 4 || clients.getFirstName().length()>10){
//      errors.rejectValue("firstName","Size.clientForm.firstName");
//    }
//
//    if (clientService.findByFirstName(clients.getFirstName()) != null) {
//      errors.rejectValue("firstName", "Duplicate.clientForm.firstName");
//    }
//
//    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
//    if (clients.getPassword().length() < 4 || clients.getPassword().length() > 20) {
//      errors.rejectValue("password", "Size.userForm.password");
//    }
//
//    if (!clients.getPasswordConfirm().equals(clients.getPassword())) {
//      errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
//    }
//
//  }
//}
