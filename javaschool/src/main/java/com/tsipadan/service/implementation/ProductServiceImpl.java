package com.tsipadan.service.implementation;

import com.tsipadan.dto.GoodsCreationDTO;
import com.tsipadan.dto.GoodsDTO;
import com.tsipadan.entity.Goods;
import com.tsipadan.entity.ItemCategory;
import com.tsipadan.exception.EntityNotFoundException;
import com.tsipadan.exception.ResourceCreationException;
import com.tsipadan.exception.ResourceUpdateException;
import com.tsipadan.mapper.ProductMapper;
import com.tsipadan.repository.CategoryRepository;
import com.tsipadan.repository.ProductRepository;
import com.tsipadan.service.api.JMSSenderService;
import com.tsipadan.service.api.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;
  private final ProductMapper mapper;
  private final CategoryRepository categoryRepository;
  private final JMSSenderService jmsSenderService;
  private final ModelMapper modelMapper;

  /**
   * Create goods
   *
   * @param goodsDTO - goodsDTO
   */
  @Override
  @SneakyThrows
  @Transactional
  public void createProduct(GoodsCreationDTO goodsDTO) {
    final ItemCategory category = categoryRepository.findById(goodsDTO.getCategoryId())
        .orElseThrow(() -> new ResourceCreationException("Category with given id not exist"));
    final byte[] bytes = goodsDTO.getImage() == null ? null : goodsDTO.getImage().getBytes();
    final Goods goods = new Goods();
    goods.setItemName(goodsDTO.getItemName());
    goods.setItemPrice(goodsDTO.getItemPrice());
    goods.setItemQuantity(goodsDTO.getItemQuantity());
    goods.setImage(bytes);
    goods.setItemCategory(category);
    goods.setItemSize(goodsDTO.getItemSize());

//    jmsSenderService.sendMessage();
    log.info("Goods: " + goodsDTO.getItemName() + " saved in Db");
    mapper.toDto(productRepository.save(goods));
  }

  /**
   * Update goods
   *
   * @param goodsDTO - goodsDTO
   */
  @SneakyThrows
  @Override
  @Transactional
  public void updateProduct(GoodsCreationDTO goodsDTO) {
    final Goods goods = productRepository.findById(goodsDTO.getId())
        .orElseThrow(() -> new ResourceUpdateException("Goods with given id not exist"));
    final ItemCategory category = categoryRepository.findById(goodsDTO.getCategoryId())
        .orElseThrow(() -> new ResourceUpdateException("Category with given id not exist"));
    final byte[] bytes = goodsDTO.getImage() == null ? null : goodsDTO.getImage().getBytes();
    goods.setItemName(goodsDTO.getItemName());
    goods.setItemCategory(category);
    goods.setItemSize(goodsDTO.getItemSize());
    goods.setItemPrice(goodsDTO.getItemPrice());
    goods.setItemQuantity(goodsDTO.getItemQuantity());
    goods.setImage(bytes);
    log.info("Goods with name: " + goods.getItemName() + " updated now");
  }

  /**
   * Delete goods
   *
   * @param id - specific id
   */
  @Override
  @Transactional
  public void deleteGoods(long id) {
    productRepository.findById(id)
        .ifPresentOrElse(productRepository::delete, () -> {
          throw new EntityNotFoundException("Entity not found");
        });
    log.info("Deleted goods with id: " + id);
  }

  /**
   * Find goodsDTO by id
   *
   * @param id - specific id
   * @return specific goodsDTO
   */
  @Override
  @Transactional(readOnly = true)
  public GoodsCreationDTO findById(long id) {
    return modelMapper.map(findGoodsById(id), GoodsCreationDTO.class);
  }

  @Override
  @Transactional(readOnly = true)
  public GoodsDTO findByIdGoodsDto(long id) {
    return mapper.toDto(findGoodsById(id));
  }

  /**
   * Find goods by id
   *
   * @param id - specific id
   * @return specific goods
   */
  private Goods findGoodsById(long id) {
    return productRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Goods with given id not exist"));
  }

  /**
   * Get all goods
   *
   * @param pageNumber - number of page
   * @return Page<GoodsDTO>
   */
  @Override
  @Transactional
  public Page<GoodsDTO> getAllPages(int pageNumber) {
    Pageable pageable = PageRequest.of(pageNumber - 1, 3);
    return productRepository.findAll(pageable).map(mapper::toDto);
  }

  /**
   * Get goods by category
   *
   * @param itemCategory - specific category
   * @return List<GoodsDTO> with specific category
   */
  @Override
  @Transactional
  public List<GoodsDTO> getGoodsByItemCategory(String itemCategory) {
    return productRepository.findGoodsByItemCategory_ItemCategory(itemCategory).stream()
        .map(mapper::toDto).collect(Collectors.toList());
  }

}
