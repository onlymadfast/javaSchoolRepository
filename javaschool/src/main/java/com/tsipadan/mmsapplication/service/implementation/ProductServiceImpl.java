package com.tsipadan.mmsapplication.service.implementation;

import com.tsipadan.dto.GoodsDTO;
import com.tsipadan.mmsapplication.entity.Goods;
import com.tsipadan.mmsapplication.exception.ResourceCreationException;
import com.tsipadan.mmsapplication.mapper.ItemCategoryMapper;
import com.tsipadan.mmsapplication.mapper.ProductMapper;
import com.tsipadan.mmsapplication.repository.api.CategoryRepository;
import com.tsipadan.mmsapplication.repository.api.ProductRepository;
import com.tsipadan.mmsapplication.service.api.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;
  private final ProductMapper mapper;
  private final ItemCategoryMapper categoryMapper;
  private final CategoryRepository categoryRepository;

  @Override
  public List<GoodsDTO> getAllGoods() {
    return productRepository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
  }

  @Override
  public GoodsDTO createProduct(GoodsDTO goodsDTO) {
    final Optional<Goods> existingProduct = Optional.ofNullable(productRepository.findById(goodsDTO.getId()));
    if (existingProduct.isPresent()){
      throw new ResourceCreationException("Product already created with this id");
    }
    return mapper.toDto(productRepository.save(mapper.toEntity(goodsDTO)));
  }

  @Override
  public void updateProduct(GoodsDTO goodsDTO) {
//    return productRepository
//        .findById(goodsDTO.getId())
//        .map(existingProduct -> {
//          existingProduct.setItemName(goodsDTO.getItemName());
//          existingProduct.setItemCategory(categoryMapper.toEntity(goodsDTO.getCategory()));
//          existingProduct.setItemSize(goodsDTO.getItemSize());
//          existingProduct.setItemPrice(goodsDTO.getItemPrice());
//          existingProduct.setItemQuantity(goodsDTO.getItemQuantity());
//          try {
//            existingProduct.setImage(goodsDTO.getImage().getBytes());
//          } catch (IOException e) {
//            e.printStackTrace();
//          }
//          return existingProduct;
//        })
//        .map(productRepository::save)
//        .map(mapper::toDto)
//        .orElseThrow(()->new ResourceUpdateException("Product with this" + goodsDTO.getId() + " does not exist"));

    Goods goods = productRepository.findById(goodsDTO.getId());
    goods.setItemName(goodsDTO.getItemName());
    goods.setItemCategory(categoryMapper.toEntity(goodsDTO.getCategory()));
    goods.setItemSize(goodsDTO.getItemSize());
    goods.setItemPrice(goodsDTO.getItemPrice());
    goods.setItemQuantity(goodsDTO.getItemQuantity());
    try {
      goods.setImage(goodsDTO.getImage().getBytes());
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  @Override
  public void deleteGoods(long id) {
    productRepository.deleteById(id);
  }

  @Override
  public GoodsDTO findById(long id) {
    return mapper.toDto(findGoodsById(id));
  }

  private Goods findGoodsById(long id) {
    return productRepository.findById(id);
//        .orElseThrow(() -> new DefaultException("exception/productServiceImpl(82)"))

  }

  @Override
  public Page<GoodsDTO> getAllPages(int pageNumber){
    Pageable pageable = PageRequest.of(pageNumber - 1,3);
    return productRepository.findAll(pageable).map(mapper::toDto);
  }

  @Override
  public List<GoodsDTO> getGoodsByItemCategory(String itemCategory){
    return productRepository.findGoodsByItemCategory_ItemCategory(itemCategory).stream()
        .map(mapper::toDto).collect(Collectors.toList());
  }


}
