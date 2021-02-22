package com.tsipadan.mmsapplication.service.implementation;

import com.tsipadan.dto.GoodsDTO;
import com.tsipadan.mmsapplication.entity.Goods;
import com.tsipadan.mmsapplication.entity.UserAddress;
import com.tsipadan.mmsapplication.entity.UserOrder;
import com.tsipadan.enumaration.StatusOrder;
import com.tsipadan.enumaration.StatusPay;
import com.tsipadan.mmsapplication.mapper.UserOrderMapper;
import com.tsipadan.mmsapplication.mapper.ItemCategoryMapper;
import com.tsipadan.mmsapplication.mapper.ProductMapper;
import com.tsipadan.mmsapplication.mapper.UserAddressMapper;
import com.tsipadan.dto.UserOrderDTO;
import com.tsipadan.mmsapplication.repository.api.OrderRepository;
import com.tsipadan.mmsapplication.repository.api.ProductRepository;
import com.tsipadan.mmsapplication.service.api.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;
  private final ProductRepository productRepository;
  private final UserOrderMapper userOrderMapper;
  private final UserAddressMapper addressMapper;
  private final ProductMapper productMapper;
  private final ItemCategoryMapper itemCategoryMapper;

  @SneakyThrows
  @Override
  public void saveOrder(UserOrderDTO userOrderDTO) {
    int random = (int) (Math.random()*1000);
    int orderNum = random + 1;

    UserOrder userOrder = userOrderMapper.toEntity(userOrderDTO);
//    userOrder.setOrderNum(orderNum);
    userOrder.setOrderDate(new Date());
    userOrder.setHowPay(userOrderDTO.getHowPay());
    userOrder.setHowDeliver(userOrderDTO.getHowDeliver());
    userOrder.setStatusPay(StatusPay.PAID);
    userOrder.setStatusOrder(StatusOrder.ON_THE_WAY);
    userOrder.setAmount(userOrderDTO.getAmountTotal());



    UserAddress userAddress = addressMapper.toEntity(userOrderDTO.getUserAddressDTO());
    userAddress.setUserCountry(userOrderDTO.getUserAddressDTO().getUserCountry());
    userAddress.setUserCity(userOrderDTO.getUserAddressDTO().getUserCity());
    userAddress.setUserZip(userOrderDTO.getUserAddressDTO().getUserZip());
    userAddress.setUserStreet(userOrderDTO.getUserAddressDTO().getUserStreet());
    userAddress.setUserHouse(userOrderDTO.getUserAddressDTO().getUserHouse());
    userAddress.setUserApartment(userOrderDTO.getUserAddressDTO().getUserApartment());

    userOrder.setUserAddress(userAddress);

    // TODO: сохранить юзера в заказ,    cartCustomerForm.jsp -> должен все это получать в форму!!!

    List<GoodsDTO> goodsDTOList = userOrderDTO.getGoodsDTOList();
    for (GoodsDTO line: goodsDTOList){
      Goods goods = productMapper.toEntity(line);
      goods.setItemName(line.getItemName());
      goods.setItemPrice(line.getItemPrice());
      goods.setItemQuantity(line.getItemQuantity());
      goods.setImage(line.getImage().getBytes());
      goods.setItemSize(line.getItemSize());
      goods.setUserOrder(userOrder);
      goods.setItemCategory(itemCategoryMapper.toEntity(line.getCategory()));



    }

//    userOrder.setListOfGoods();   //TODO: сохранить как то инфу о goods в заказ

  }

  @Override
  public UserOrderDTO findById(long id) {
    return userOrderMapper.toDto(orderRepository.findById(id));
  }

  @Override
  public UserOrderDTO getOrderInformation(String orderNum) {
    return userOrderMapper.toDto(orderRepository.findByOrderNum(orderNum));
  }

  @Override
  public Page<UserOrderDTO> getAllPages(int pageNumber) {
    Pageable pageable = PageRequest.of(pageNumber-1,3);
    return orderRepository.findAll(pageable).map(userOrderMapper::toDto);
  }
}
