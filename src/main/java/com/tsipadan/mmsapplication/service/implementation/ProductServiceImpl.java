package com.tsipadan.mmsapplication.service.implementation;

import com.tsipadan.mmsapplication.dto.GoodsDTO;
import com.tsipadan.mmsapplication.entity.Goods;
import com.tsipadan.mmsapplication.repository.ProductRepository;
import com.tsipadan.mmsapplication.service.api.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;
  private final ModelMapper modelMapper;

  @Override
  public List<GoodsDTO> getAllGoods() {
    List<GoodsDTO> goodsDTOList = new ArrayList<>();
    List<Goods> goodsList = productRepository.findAll();
    for (Goods goods: goodsList){
      goodsDTOList.add(modelMapper.map(goods, GoodsDTO.class));
    }
    return goodsDTOList;
  }

  @Override
  public Goods addGoods(Goods goods) {
    return null;
  }

  @Override
  public void deleteGoods(long id) {

  }

}
