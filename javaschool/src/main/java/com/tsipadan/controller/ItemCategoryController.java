package com.tsipadan.controller;

import com.tsipadan.dto.ItemCategoryDTO;
import com.tsipadan.service.api.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class ItemCategoryController {

  private final CategoryService categoryService;

  /**
   * Get list of categories
   *
   * @param model -model
   * @return itemCategory.jsp
   */
  @GetMapping(value = "/itemCategory")
  public String getItemCategories(Model model) {
    model.addAttribute("categories", categoryService.all());
    return "itemCategory";
  }

  /**
   * Get page for new category
   *
   * @param model - model
   * @return newItemCategory.jsp
   */
  @GetMapping(value = "/newItemCategory")
  public String newItemCategory(Model model) {
    model.addAttribute("newCategory", new ItemCategoryDTO());
    return "newItemCategory";
  }

  /**
   * Save new category
   *
   * @param itemCategoryDTO - itemCategory
   * @return itemCategory.jsp
   */
  @RequestMapping(value = "/save")
  public String saveCategory(@ModelAttribute("newCategory") @Valid ItemCategoryDTO itemCategoryDTO) {
    categoryService.createCategory(itemCategoryDTO);
    return "redirect:/itemCategory";
  }

  /**
   * Edit category
   *
   * @param model - model
   * @param itemCategory - itemCategory
   * @return editItemCategory.jsp
   */
  @RequestMapping(value = "/edit")
  public String editCategory(Model model, @RequestParam("name") String itemCategory) {
    ItemCategoryDTO itemCategoryDTO = categoryService.findCategoryByName(itemCategory);
    model.addAttribute("itemCategory", itemCategoryDTO);
    return "editItemCategory";
  }

  /**
   * Delete category
   *
   * @param itemCategory - itemCategory
   * @return itemCategory.jsp
   */
  @RequestMapping(value = "/deleteCategory")
  public String deleteCategory(@RequestParam("name") long itemCategory) {
    categoryService.deleteCategory(itemCategory);
    return "redirect:/itemCategory";
  }

}
