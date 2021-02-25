package com.tsipadan.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ItemCategoryDTO {

  private Long id;

  @NotBlank
  private String itemCategory;

  @Override
  public String toString() {
    return "{" +
        "id=" + id +
        ", itemCategory='" + itemCategory + '\'' +
        '}';
  }

}
