package com.tsipadan.mmsapplication.controller;

import com.tsipadan.dto.ItemCategoryDTO;
import com.tsipadan.mmsapplication.service.api.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ItemCategoryController {

  private final CategoryService categoryService;

  @GetMapping(value = "/itemCategory")
  public String getItemCategories(Model model) {
    model.addAttribute("categories", categoryService.all());
    return "itemCategory";
  }

  @GetMapping(value = "/newItemCategory")
  public String newItemCategory(Model model) {
    model.addAttribute("newCategory", new ItemCategoryDTO());
    return "newItemCategory";
  }

  @RequestMapping(value = "/save")
  public String saveCategory(@ModelAttribute("newCategory") ItemCategoryDTO itemCategoryDTO) {
    categoryService.createCategory(itemCategoryDTO);
    return "redirect:/itemCategory";
  }

  @RequestMapping(value = "/edit")
  public String editCategory(Model model, @RequestParam("name") String itemCategory) {
    ItemCategoryDTO itemCategoryDTO = categoryService.findCategoryByName(itemCategory);
    model.addAttribute("itemCategory", itemCategoryDTO);
    return "editItemCategory";
  }

  @RequestMapping(value = "/delete")
  public String deleteCategory(@RequestParam("name") long itemCategory) {
    categoryService.deleteCategory(itemCategory);
    return "redirect:/itemCategory";
  }

}
