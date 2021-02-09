//package com.tsipadan.mmsapplication.validator;
//
//import com.tsipadan.mmsapplication.dao.ProductDAO;
//import com.tsipadan.mmsapplication.entity.Product;
//import com.tsipadan.mmsapplication.model.ProductInfo;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//import org.springframework.validation.Errors;
//import org.springframework.validation.ValidationUtils;
//import org.springframework.validation.Validator;
//
//@Component
//@RequiredArgsConstructor
//public class ProductInfoValidator implements Validator {
//
//  private final ProductDAO productDAO;
//
//  @Override
//  public boolean supports(Class<?> aClass) {
//    return ProductInfo.class.equals(aClass);
//  }
//
//  @Override
//  public void validate(Object target, Errors errors) {
//
//    ProductInfo productInfo = (ProductInfo) target;
//    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code", "NotEmpty.productForm.code");
//    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.productForm.name");
//    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "NotEmpty.productForm.price");
//    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "category", "NotEmpty.productForm.category");
//    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "size", "NotEmpty.productForm.size");
//
//    String code = productInfo.getCode();
//    if (code != null && code.length() > 0) {
//      if (code.matches("^[a-zA-Z0-9_.-]*$")) {
//        errors.rejectValue("code", "Pattern.productForm.code");
//      } else if (productInfo.isNewProduct()) {
//        productDAO.findProduct(code)
//            .ifPresent(product -> errors.rejectValue("code", "Duplicate.productForm.code"));
//      }
//    }
//  }
//
//}
