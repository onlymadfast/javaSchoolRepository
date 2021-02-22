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

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOrderDTO {

  private long id;
  private String orderNum;
  private Date orderDate;

  private HowPay howPay;
  private HowDeliver howDeliver;
  private StatusPay statusPay;
  private StatusOrder statusOrder;

  private UserAddressDTO userAddressDTO;
  private UserDTO userDto;

  private final List<GoodsDTO> goodsDTOList = new ArrayList<>();

  private double amount;


  private GoodsDTO findLineById(long id) {      ////////////TODO: почему то выводит null!!!!!!!!!
    for (GoodsDTO dto : this.goodsDTOList) {
      if (dto.getId() == id) {
        return dto;
      }
    }
    return null;
  }

  public void addGoods(GoodsDTO goodsDTO, int quantity) {
    GoodsDTO dto = this.findLineById(goodsDTO.getId());
    if (dto == null) {
      dto = new GoodsDTO();
      dto.setItemQuantity(0);
      this.goodsDTOList.add(dto);
    }
    int newQuantity = dto.getItemQuantity() + quantity;
    if (newQuantity <= 0) {
      this.goodsDTOList.remove(dto);
    } else {
      dto.setItemQuantity(newQuantity);
    }
  }

  public void updateGoods(long id, int quantity) {
    GoodsDTO line = this.findLineById(id);
    if (line != null) {
      if (quantity <= 0) {
        this.goodsDTOList.remove(line);
      } else {
        line.setItemQuantity(quantity);
      }
    }
  }

  public void removeProduct(GoodsDTO goodsDTO) {
    GoodsDTO line = this.findLineById(goodsDTO.getId());
    if (line != null) {
      this.goodsDTOList.remove(line);
    }
  }

  public boolean isEmpty() {
    return this.goodsDTOList.isEmpty();
  }

  public boolean isValidCustomer() {
    return this.userAddressDTO == null || !this.userAddressDTO.isValid();
  }

  public int getQuantityTotal() {
    int quantity = 0;
    for (GoodsDTO line : this.goodsDTOList) {
      quantity += line.getItemQuantity();
    }
    return quantity;
  }

  public void updateQuantity(UserOrderDTO cartForm) {
    if (cartForm != null) {
      List<GoodsDTO> lines = cartForm.getGoodsDTOList();
      for (GoodsDTO line : lines) {
        this.updateGoods(line.getId(), line.getItemQuantity());
      }
    }
  }

  public double getAmountTotal() {
    double total = 0;
    for (GoodsDTO line : this.goodsDTOList) {
      total += line.getAmount();
    }
    return total;
  }

}
