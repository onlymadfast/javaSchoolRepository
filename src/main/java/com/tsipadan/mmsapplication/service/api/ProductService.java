package com.tsipadan.mmsapplication.service.api;

import com.tsipadan.mmsapplication.dto.GoodsDTO;
import com.tsipadan.mmsapplication.entity.Goods;

import java.util.List;

public interface ProductService {

  List<GoodsDTO> getAllGoods();

  Goods addGoods(Goods goods);

  void deleteGoods(long id);

}
