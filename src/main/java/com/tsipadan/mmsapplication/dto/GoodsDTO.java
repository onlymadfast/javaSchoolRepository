package com.tsipadan.mmsapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsDTO {

  private long id;
  private String itemName;
  private int itemPrice;
  private int itemQuantity;
  private MultipartFile image;
  private List<ItemCategoryDTO> itemCategoryDto;
  private SizeTableDTO sizeTableDto;

}
