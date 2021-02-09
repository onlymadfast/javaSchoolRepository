package com.tsipadan.mmsapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusOrderDTO {

  private long id;

  private String statusOrder;

//  public StatusOrderDTO(String statusOrder) {
//    this.statusOrder = statusOrder;
//  }

}
