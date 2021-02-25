package com.tsipadan.service.api;

import com.tsipadan.dto.GoodsCreationDTO;
import com.tsipadan.dto.GoodsDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

  void createProduct(GoodsCreationDTO goodsDTO);

  void updateProduct(GoodsCreationDTO goodsDTO);

  void deleteGoods(long id);

  GoodsCreationDTO findById(long id);

  GoodsDTO findByIdGoodsDto(long id);

  Page<GoodsDTO> getAllPages(int pageNumber);

  List<GoodsDTO> getAll(String keyword);

  List<GoodsDTO> getGoodsByItemCategory(String itemCategory);

  List<GoodsDTO> getTop();

}
