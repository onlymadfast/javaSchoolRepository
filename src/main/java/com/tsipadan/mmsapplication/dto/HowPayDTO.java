package com.tsipadan.mmsapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HowPayDTO {

  private long id;

  private String howPayOrder;

//  public HowPayDTO(String howPayOrder) {
//    this.howPayOrder = howPayOrder;
//  }

}
