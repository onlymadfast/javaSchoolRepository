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
    return CustomerInfo.class.equals(aClass);
  }

  @Override
  public void validate(Object target, Errors errors) {

    CustomerInfo customerInfo = (CustomerInfo) target;
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerFirstName", "NotEmpty.customerForm.firstName");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerLastName", "NotEmpty.customerForm.lastName");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerBirthday", "NotEmpty.customerForm.birthday");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerEmail", "NotEmpty.customerForm.email");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerCountry", "NotEmpty.customerForm.country");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerCity", "NotEmpty.customerForm.city");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerZip", "NotEmpty.customerForm.zip");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerStreet", "NotEmpty.customerForm.street");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerHouse", "NotEmpty.customerForm.house");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerApartment", "NotEmpty.customerForm.apartment");

    if(!emailValidator.isValid(customerInfo.getCustomerEmail())){
      errors.rejectValue("email", "Pattern.customerForm.email");
    }

  }
}
