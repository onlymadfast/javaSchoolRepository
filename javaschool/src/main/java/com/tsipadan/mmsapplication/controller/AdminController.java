package com.tsipadan.mmsapplication.controller;

import com.tsipadan.dto.GoodsDTO;
import com.tsipadan.dto.UserOrderDTO;
import com.tsipadan.mmsapplication.exception.ResourceCreationException;
import com.tsipadan.mmsapplication.exception.ResourceUpdateException;
import com.tsipadan.mmsapplication.service.api.CategoryService;
import com.tsipadan.mmsapplication.service.api.OrderService;
import com.tsipadan.mmsapplication.service.api.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {

  private final ProductService productService;
  private final CategoryService categoryService;
  private final OrderService orderService;

//  @ExceptionHandler(ResourceCreationException.class)
//  public String handleProductCreationException(ResourceCreationException ex, Model model) {
//    model.addAttribute("message", ex.getMessage());
//    model.addAttribute("createProduct", new Goods());
//    return "createProduct";
//  }

  @InitBinder
  public void myInitBinder(WebDataBinder dataBinder) {
    dataBinder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
  }

  @GetMapping(value = "/admin")
  public String viewAdminAcc(Model model) {
    return "adminPage";
  }

  @GetMapping(value = "/createProduct")
  public String viewCreateProductPage(Model model) {
    model.addAttribute("createProduct", new GoodsDTO());
    model.addAttribute("listOfCategory", categoryService.all());
    return "createProduct";
  }

  @PostMapping(value = "/createProduct")
  public String createProduct(@ModelAttribute("createProduct") @Valid GoodsDTO goods) {

    try {
      productService.createProduct(goods);
    } catch (Exception e) {
      throw new ResourceCreationException("Exception in creation new Goods" + e);
    }
    return "redirect:/admin";
  }

  @GetMapping(value = "/updateProduct")
  public String viewUpdateProductPage(@RequestParam("id") long id, Model model) {
    GoodsDTO goodsDTO = productService.findById(id);
    model.addAttribute("updateProduct", goodsDTO);
    model.addAttribute("listOfCategory", categoryService.all());
    return "updateProduct";
  }

  @PostMapping(value = "/updateProduct")
  public String updateProduct(@ModelAttribute("updateProduct") GoodsDTO goodsDTO) {
    try {
      productService.updateProduct(goodsDTO);
    } catch (Exception e) {
      throw new ResourceUpdateException("exception in updateProduct(109) " + e);
    }
    return "redirect:/store";
  }

  @RequestMapping(value = "/orderList")
  public String viewOrderList(Model model) {
    return listOrderForAdmin(model, 1);
  }

  @GetMapping(value = "/orderList/{pageNumber}")
  public String listOrderForAdmin(Model model, @PathVariable("pageNumber") int currentPage) {

    Page<UserOrderDTO> page = orderService.getAllPages(currentPage);
    List<UserOrderDTO> list = page.getContent();
    int totalPages = page.getTotalPages();
    long totalItems = page.getTotalElements();

    model.addAttribute("currentPage", currentPage);
    model.addAttribute("totalItems", totalItems);
    model.addAttribute("totalPages", totalPages);
    model.addAttribute("orderInfo", list);
    return "orderListAdmin";
  }

  @GetMapping(value = "/viewOrderInfo")
  public String viewOrderInfoById(Model model, @RequestParam("id") long id) {
    UserOrderDTO userOrderDTO = orderService.findById(id);
    model.addAttribute("orderInfo", userOrderDTO);
    return "orderListAdminDetails";
  }

}
