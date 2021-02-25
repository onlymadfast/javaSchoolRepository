package com.tsipadan.service.implementation;

import com.tsipadan.dto.ItemCategoryDTO;
import com.tsipadan.mapper.ItemCategoryMapper;
import com.tsipadan.repository.CategoryRepository;
import com.tsipadan.service.api.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;
  private final ItemCategoryMapper categoryMapper;

  /**
   * Return all categories from Db
   *
   * @return List<ItemCategoryDTO>
   */
  @Override
  @Transactional
  public List<ItemCategoryDTO> all() {
    return categoryRepository.findAll().stream().map(categoryMapper::toDto).collect(Collectors.toList());
  }

  /**
   * Get specific category
   *
   * @param name - itemCategory name
   * @return itemCategory
   */
  @Override
  @Transactional(readOnly = true)
  public ItemCategoryDTO findCategoryByName(String name) {
    return categoryMapper.toDto(categoryRepository.findByItemCategory(name));
  }

  /**
   * Create category
   *
   * @param itemCategoryDTO - itemCategoryDTO
   * @return new itemCategory
   */
  @Override
  @Transactional
  public ItemCategoryDTO createCategory(ItemCategoryDTO itemCategoryDTO) {
    log.info("ItemCategory with name: " + itemCategoryDTO.getItemCategory() + " is created");
    return categoryMapper.toDto(categoryRepository.save(categoryMapper.toEntity(itemCategoryDTO)));
  }

  /**
   * Delete category by id
   *
   * @param category - specific id category
   */
  @Override
  @Transactional
  public void deleteCategory(long category) {
    log.info("ItemCategory with id: " + category + " deleted now");
    categoryRepository.deleteById(category);
  }

}
