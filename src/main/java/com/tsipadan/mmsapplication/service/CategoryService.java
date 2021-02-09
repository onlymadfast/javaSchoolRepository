package com.tsipadan.mmsapplication.service;

import com.tsipadan.mmsapplication.entity.ItemCategory;
import com.tsipadan.mmsapplication.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

  @Autowired
  private CategoryRepository categoryRepository;

  @Transactional
  public List<ItemCategory> all(){
    return categoryRepository.findAll();
  }

  @Transactional
  public ItemCategory findCategoryById(long id){
    return categoryRepository.getOne(id);
  }

  @Transactional
  public ItemCategory createCategory(String itemCategory){
    ItemCategory newCategories = new ItemCategory();
    newCategories.setName(itemCategory);
    return categoryRepository.save(newCategories);
  }

  @Transactional
  public void deleteCategory(ItemCategory itemCategory){
    categoryRepository.delete(itemCategory);
  }

}
