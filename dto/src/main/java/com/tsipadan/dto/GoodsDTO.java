package com.tsipadan.dto;

import com.tsipadan.enumaration.ItemSize;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class GoodsDTO {

  @Min(1)
  @Max(10000)
  private Long id;

  @NotNull
  private String itemName;

  @NotNull
  @Min(1)
  private Integer itemPrice;

  @NotNull
  @Min(1)
  private Integer itemQuantity;

  private MultipartFile image;

  @NotNull
  private ItemCategoryDTO category;

  @NotNull
  private ItemSize itemSize;

  @Override
  public String toString() {
    return "{" +
        "id=" + id +
        ", itemName='" + itemName + '\'' +
        ", itemPrice=" + itemPrice +
        ", itemQuantity=" + itemQuantity +
        ", image=" + image +
        ", category=" + category +
        ", itemSize=" + itemSize +
        '}';
  }
}
