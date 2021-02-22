package com.tsipadan.dto;

import com.tsipadan.enumaration.ItemSize;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class GoodsDTO {

  private long id;

  @NotBlank(message = "Enter itemName")
  private String itemName;

  @NotNull(message = "price must be >0")
  private int itemPrice;

  @NotNull(message = "quantity must be >0")
  @Min(value = 1)
  private int itemQuantity;

  private MultipartFile image;

  private ItemCategoryDTO category;

  private ItemSize itemSize;


  public GoodsDTO(){
    this.itemQuantity=0;
  }

  public double getAmount() {
    return this.getItemPrice() * this.itemQuantity;
  }

}
