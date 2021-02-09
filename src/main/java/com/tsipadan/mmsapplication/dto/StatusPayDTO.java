package com.tsipadan.mmsapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusPayDTO {

  private long id;

  private String statusPayOrder;

//  public StatusPayDTO(String statusPayOrder) {
//    this.statusPayOrder = statusPayOrder;
//  }

}
