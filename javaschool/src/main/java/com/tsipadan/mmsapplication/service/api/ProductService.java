package com.tsipadan.mmsapplication.service.api;

import com.tsipadan.dto.GoodsDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

  List<GoodsDTO> getAllGoods();

  GoodsDTO createProduct(GoodsDTO goodsDTO);

  void updateProduct(GoodsDTO goodsDTO);

  void deleteGoods(long id);

  GoodsDTO findById(long id);

  Page<GoodsDTO> getAllPages(int pageNumber);

  List<GoodsDTO> getGoodsByItemCategory(String itemCategory);

}
