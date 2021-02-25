package com.tsipadan.dto;

import com.tsipadan.enumaration.HowDeliver;
import com.tsipadan.enumaration.HowPay;
import com.tsipadan.enumaration.StatusOrder;
import com.tsipadan.enumaration.StatusPay;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOrderDTO {

  private Long id;
  private String orderNum;
  private Date orderDate;

  private HowPay howPay;
  private HowDeliver howDeliver;
  private StatusPay statusPay;
  private StatusOrder statusOrder;

  private double amount;

  private UserAddressDTO userAddressDTO;
  private UserDTO userDto;

  private final List<GoodsDTO> goodsDTOList = new ArrayList<>();


  private GoodsDTO findLineById(long id) {
    for (GoodsDTO dto : goodsDTOList) {
      if (dto.getId() == id) {
        return dto;
      }
    }
    return null;
  }

  public void addGoodsInOrderInGoodsList(GoodsDTO goodsDTO, int quantityInOrder) {

    for (GoodsDTO dto : goodsDTOList) {
      if (dto.getId().equals(goodsDTO.getId())) {
        dto.setItemQuantity(dto.getItemQuantity() + quantityInOrder);
        return;
      }
    }
    goodsDTO.setItemQuantity(quantityInOrder);
    goodsDTOList.add(goodsDTO);
  }

  public void removeProduct(GoodsDTO goodsDTO) {
    GoodsDTO line = findLineById(goodsDTO.getId());
    if (line != null) {
      goodsDTOList.remove(line);
    }
  }

  public void updateQuantity(UserOrderDTO orderDTO) {
    if (orderDTO != null) {
      List<GoodsDTO> DTOs = orderDTO.getGoodsDTOList();
      for (GoodsDTO dto : DTOs) {
        this.updateGoods(dto.getId(), dto.getItemQuantity());
      }
    }
  }

  public void updateGoods(long id, int quantity) {
    GoodsDTO line = findLineById(id);
    if (line != null) {
      if (quantity <= 0) {
        goodsDTOList.remove(line);
      } else {
        line.setItemQuantity(quantity);
      }
    }
  }

  public double getAmountTotal() {
    double total = 0;
    for (GoodsDTO dto : goodsDTOList) {
      total += dto.getItemPrice() * dto.getItemQuantity();
    }
    return total;
  }

  public int getQuantityTotal() {
    int quantity = 0;
    for (GoodsDTO dto : goodsDTOList) {
      quantity += dto.getItemQuantity();
    }
    return quantity;
  }

  public boolean isEmpty() {
    return this.goodsDTOList.isEmpty();
  }

  public boolean isValidCustomer() {
    return this.userAddressDTO == null || !this.userAddressDTO.isValid();
  }

}
