package com.tsipadan.controller;

import com.tsipadan.dto.GoodsDTO;
import com.tsipadan.service.api.CategoryService;
import com.tsipadan.service.api.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class StoreController {

  private final ProductService productService;
  private final CategoryService categoryService;

  /**
   * Return store
   *
   * @param model - model
   * @return store.jsp
   */
  @RequestMapping(value = "/store")
  public String viewStorePage(Model model) {
    return listByPage(model, 1);
  }

  /**
   * Return store with page number
   *
   * @param model - model
   * @param currentPage - current page
   * @return store.jsp with number of page
   */
  @GetMapping(value = "/store/{pageNumber}")
  public String listByPage(Model model, @PathVariable("pageNumber") int currentPage) {

    Page<GoodsDTO> page = productService.getAllPages(currentPage);
    List<GoodsDTO> list = page.getContent();
    int totalPages = page.getTotalPages();
    long totalItems = page.getTotalElements();

    model.addAttribute("currentPage", currentPage);
    model.addAttribute("totalItems", totalItems);
    model.addAttribute("totalPages", totalPages);
    model.addAttribute("allGoods", list);
    model.addAttribute("listCategory", categoryService.all());
    return "store";
  }

  @GetMapping(value = "/search")
  public String search(Model model, @Param("keyword")String keyword){
    List<GoodsDTO> goodsDTOS = productService.getAll(keyword);
    model.addAttribute("search", goodsDTOS);
    model.addAttribute("keyword", keyword);
    model.addAttribute("listCategory", categoryService.all());
    return "search";
  }

  /**
   * Get filter store by categories
   *
   * @param model - model
   * @param itemCategory - itemCategory
   * @return storeByItemCategory.jsp
   */
  @GetMapping(value = "/showResult/{itemCategory}")
  public String listByCat(Model model, @PathVariable("itemCategory") String itemCategory) {

    if (itemCategory.isBlank()) return "store";

    model.addAttribute("thisCategory", itemCategory);
    model.addAttribute("listCategory", categoryService.all());
    model.addAttribute("goodsByCat", productService.getGoodsByItemCategory(itemCategory));
    return "storeByItemCategory";
  }

  @GetMapping(value = "/top")
  public String topItems(Model model){
    List<GoodsDTO> dto = productService.getTop();
    model.addAttribute("top", dto);
    return "top";
  }

}
