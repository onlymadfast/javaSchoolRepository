package com.tsipadan.mmsapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOrderDTO {

  private long id;
  private HowPayDTO howPayDto;
  private HowDeliverDTO howDeliverDto;
  private List<GoodsDTO> goodsDtoList;
  private StatusPayDTO statusPayDto;
  private StatusOrderDTO statusOrderDto;
  private UserAddressDTO userAddressDto;
  private UserDTO userDto;

}
