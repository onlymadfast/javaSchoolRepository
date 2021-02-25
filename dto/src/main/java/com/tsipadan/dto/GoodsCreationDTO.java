package com.tsipadan.dto;

import com.tsipadan.enumaration.ItemSize;
import lombok.Data;
import lombok.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class GoodsCreationDTO {

  Long id;

  @NotNull
  String itemName;

  @NotNull
  @Min(1)
  Integer itemPrice;

  @NotNull
  @Min(1)
  Integer itemQuantity;

  MultipartFile image;

  @NotNull
  Long categoryId;

  @NotNull
  ItemSize itemSize;

}
