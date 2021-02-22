package com.tsipadan.mmsapplication.service.implementation;

import com.tsipadan.dto.ItemCategoryDTO;
import com.tsipadan.mmsapplication.mapper.ItemCategoryMapper;
import com.tsipadan.mmsapplication.repository.api.CategoryRepository;
import com.tsipadan.mmsapplication.service.api.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;
  private final ItemCategoryMapper categoryMapper;

  @Override
  public List<ItemCategoryDTO> all() {
    return categoryRepository.findAll().stream().map(categoryMapper::toDto).collect(Collectors.toList());
  }

  @Override
  public ItemCategoryDTO findCategoryByName(String name) {
    return categoryMapper.toDto(categoryRepository.findByItemCategory(name));
  }

  @Override
  public ItemCategoryDTO createCategory(ItemCategoryDTO itemCategoryDTO) {
    return categoryMapper.toDto(categoryRepository.save(categoryMapper.toEntity(itemCategoryDTO)));
  }

  @Override
  public void deleteCategory(long category) {
    categoryRepository.deleteById(category);
  }

}
