package com.tsipadan.mmsapplication.service.api;

import com.tsipadan.dto.ItemCategoryDTO;

import java.util.List;

public interface CategoryService {

  List<ItemCategoryDTO>all();

  ItemCategoryDTO findCategoryByName(String name);

  ItemCategoryDTO createCategory(ItemCategoryDTO itemCategoryDTO);

  void deleteCategory(long itemCategoryName);

}
