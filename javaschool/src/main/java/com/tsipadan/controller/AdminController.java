package com.tsipadan.controller;

import com.tsipadan.dto.GoodsCreationDTO;
import com.tsipadan.dto.UserOrderDTO;
import com.tsipadan.exception.ResourceCreationException;
import com.tsipadan.exception.ResourceUpdateException;
import com.tsipadan.service.api.CategoryService;
import com.tsipadan.service.api.OrderService;
import com.tsipadan.service.api.ProductService;
import com.tsipadan.validation.GoodsValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AdminController {

  private final ProductService productService;
  private final CategoryService categoryService;
  private final OrderService orderService;
  private final GoodsValidator goodsValidator;

  @InitBinder
  public void myInitBinder(WebDataBinder dataBinder) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    dataBinder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
    dataBinder.setValidator(goodsValidator);
  }

  /**
   * Get admin cabinet
   *
   * @param model     - model
   * @return admin.jsp
   */
  @GetMapping(value = "/admin")
  public String viewAdminAcc(Model model) {
    return "adminPage";
  }

  /**
   * Get page for create new product
   *
   * @param model - model
   * @return createProduct.jsp
   */
  @GetMapping(value = "/createProduct")
  public String viewCreateProductPage(Model model) {
    model.addAttribute("goodsCreationDTO", new GoodsCreationDTO());
    model.addAttribute("listOfCategory", categoryService.all());
    return "createProduct";
  }

  /**
   * Create product
   *
   * @param goods - goods
   * @return admin.jsp
   */
  @PostMapping(value = "/createProduct")
  public String createProduct(@ModelAttribute("goodsCreationDTO")@Valid GoodsCreationDTO goods, BindingResult result) {

    goodsValidator.validate(goods, result);
    if (result.hasErrors()){
      log.info("some invalid from");
      return "redirect:/createProduct";
    }
    try {
      productService.createProduct(goods);
    } catch (Exception e) {
      throw new ResourceCreationException("Exception in creation new Goods" + e);
    }
    return "redirect:/admin";
  }

  /**
   * Get page for update
   *
   * @param id - product id
   * @param model     - model
   * @return updateProduct.jsp
   */
  @GetMapping(value = "/updateProduct")
  public String viewUpdateProductPage(@RequestParam("id") long id, Model model) {
    GoodsCreationDTO goodsCreationDTO = productService.findById(id);
    model.addAttribute("editGoods", goodsCreationDTO);
    model.addAttribute("listOfCategory", categoryService.all());
    return "updateProduct";
  }

  /**
   * Update product
   *
   * @param goodsDTO - goods
   * @return store.jsp
   */
  @PostMapping(value = "/updateProduct")
  public String updateProduct(@ModelAttribute("editGoods") @Valid GoodsCreationDTO goodsDTO) {
    try {
      productService.updateProduct(goodsDTO);
    } catch (Exception e) {
      throw new ResourceUpdateException("exception in updateProduct " + e);
    }
    return "redirect:/store";
  }

  /**
   * Delete product
   *
   * @param id - id
   * @return store.jsp
   */
  @RequestMapping(value = "/deleteProduct")
  public String deleteProduct(@RequestParam(value = "id")long id){
    productService.deleteGoods(id);
    return "redirect:/store";
  }

  /**
   * Get list orders for admin
   *
   * @param model     - model
   * @return orderListAdmin.jsp with currentPage
   */
  @RequestMapping(value = "/orderList")
  public String viewOrderList(Model model) {
    return listOrderForAdmin(model, 1);
  }


  /**
   * Get list orders for admin with number page
   *
   * @param currentPage - current number page
   * @param model     - model
   * @return orderListAdmin.jsp with currentPage
   */
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

  /**
   * Get detail of current order
   *
   * @param id - order id
   * @param model     - model
   * @return orderListAdmin.jsp with currentPage
   */
  @GetMapping(value = "/viewOrderInfo")
  public String viewOrderInfoById(Model model, @RequestParam("id") long id) {
    UserOrderDTO userOrderDTO = orderService.findById(id);
    model.addAttribute("orderInfo", userOrderDTO);
    return "orderListAdminDetails";
  }

  @GetMapping(value = "/changeStatus")
  public String getChangeStatusPage(Model model){
    List<UserOrderDTO> orderDTO = orderService.getAllResults();
    model.addAttribute("allOrder", orderDTO);
    return "changeStatus";
  }

  @PostMapping(value = "/changeStatus")
  public String changeStatus(Model model){
    //
    return "redirect:/changeStatus";
  }

}
