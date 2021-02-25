package com.tsipadan.validation;

import com.tsipadan.dto.GoodsCreationDTO;
import com.tsipadan.dto.GoodsDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class GoodsValidator implements Validator {

  @Override
  public boolean supports(Class<?> aClass) {
    return GoodsDTO.class.equals(aClass) || GoodsCreationDTO.class.equals(aClass);
  }

  @Override
  public void validate(Object o, Errors errors) {

    if (o instanceof GoodsDTO) {
      ValidationUtils.rejectIfEmpty(errors, "itemName", "name.empty");
      ValidationUtils.rejectIfEmpty(errors, "itemPrice", "price.empty");
      ValidationUtils.rejectIfEmpty(errors, "itemQuantity", "quantity.empty");
      ValidationUtils.rejectIfEmpty(errors, "category", "category.empty");
      ValidationUtils.rejectIfEmpty(errors, "itemSize", "size.empty");
      GoodsDTO g = (GoodsDTO) o;
      if (g.getItemPrice() < 0) {
        errors.rejectValue("itemPrice", "negativePrice");
      } else if (g.getItemPrice() > 10000) {
        errors.rejectValue("itemPrice", "tooMuchPrice");
      }

      if (g.getId() < 0) {
        errors.rejectValue("id", "negativeId");
      } else if (g.getId() > 10000) {
        errors.rejectValue("id", "tooMuchId");
      }
    } else if (o instanceof GoodsCreationDTO) {
      ValidationUtils.rejectIfEmpty(errors, "itemName", "n.empty");
      ValidationUtils.rejectIfEmpty(errors, "itemPrice", "p.empty");
      ValidationUtils.rejectIfEmpty(errors, "itemQuantity", "q.empty");
      ValidationUtils.rejectIfEmpty(errors, "categoryId", "c.empty");
      ValidationUtils.rejectIfEmpty(errors, "itemSize", "s.empty");
      GoodsCreationDTO g = (GoodsCreationDTO) o;
      if (g.getItemPrice() < 0) {
        errors.rejectValue("itemPrice", "minPrice");
      } else if (g.getItemPrice() > 10000) {
        errors.rejectValue("itemPrice", "maxPrice");
      }

      if (g.getItemQuantity() < 0) {
        errors.rejectValue("id", "minQuantity");
      } else if (g.getItemQuantity() > 10000) {
        errors.rejectValue("id", "maxQuantity");
      }
    }

  }
}
