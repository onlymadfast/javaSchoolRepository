package com.tsipadan.mmsapplication.validator;

import com.tsipadan.mmsapplication.model.CustomerInfo;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CustomerInfoValidator implements Validator {

  private final EmailValidator emailValidator = EmailValidator.getInstance();

  @Override
  public boolean supports(Class<?> aClass) {
    return aClass == CustomerInfo.class;
  }

  @Override
  public void validate(Object o, Errors errors) {
    CustomerInfo customerInfo = (CustomerInfo) o;

    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty.customerForm.firstName");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty.customerForm.lastName");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthday", "NotEmpty.customerForm.birthday");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.customerForm.password");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "country", "NotEmpty.customerForm.country");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "NotEmpty.customerForm.city");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "zip", "NotEmpty.customerForm.zip");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.customerForm.email");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "street", "NotEmpty.customerForm.street");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "house", "NotEmpty.customerForm.house");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "apartment", "NotEmpty.customerForm.apartment");

    if(!emailValidator.isValid(customerInfo.getEmail())){
      errors.rejectValue("email", "Pattern.customerForm.email");
    }

  }
}
