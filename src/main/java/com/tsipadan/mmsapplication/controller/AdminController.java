package com.tsipadan.mmsapplication.controller;

import com.tsipadan.mmsapplication.entity.Goods;
import com.tsipadan.mmsapplication.exception.ResourceCreationException;
import com.tsipadan.mmsapplication.service.CategoryService;
import com.tsipadan.mmsapplication.service.api.ProductService;
import com.tsipadan.mmsapplication.service.SizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

@Controller
@RequiredArgsConstructor
public class AdminController {

//  private final OrderDAO orderDAO;
//  private final ProductInfoValidator productInfoValidator;
//
//  @InitBinder("productInfoValidator")
//  public void myInitBinder(WebDataBinder dataBinder) {
//    dataBinder.setValidator(productInfoValidator);
//    dataBinder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
//  }
//
//  @GetMapping(value = "/orderList")
//  public String orderList(Model model,
//                          @RequestParam(value = "page", defaultValue = "1") String pageStr) {
//    int page = 1;
//    try {
//      page = Integer.parseInt(pageStr);
//    } catch (Exception e) {
//      String message = e.getMessage();
//      model.addAttribute("message", message);
//    }
//    final int MAX_RESULT = 5;
//    final int MAX_NAVIGATION_PAGE = 10;
//    PaginationResult<OrderInfo> paginationResult = orderDAO.listOrderInfo(page, MAX_RESULT, MAX_NAVIGATION_PAGE);
//    model.addAttribute("paginationResult", paginationResult);
//    return "orderListSearch";
//  }
//
//  @GetMapping(value = "/order")
//  public String viewOrder(Model model, @RequestParam(value = "orderId") String orderId) {
//
//    OrderInfo orderInfo = null;
//    if (orderId != null) {
//      orderInfo = this.orderDAO.getOrderInfo(orderId);
//    }
//    if (orderInfo == null) {
//      return "redirect:/orderList";
//    }
//    List<OrderDetailInfo> details = this.orderDAO.listOrderDetailInfos(orderId);
//    orderInfo.setDetails(details);
//    model.addAttribute("orderInfo", orderInfo);
//    return "order";
//  }


  private final ProductService productService;
  private final CategoryService categoryService;
  private final SizeService sizeService;

  @InitBinder
  public void myInitBinder(WebDataBinder dataBinder){
    dataBinder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
  }

  @ExceptionHandler(ResourceCreationException.class)
  public String handleProductCreationException(ResourceCreationException ex, Model model) {
    model.addAttribute("message", ex.getMessage());
    model.addAttribute("createProduct", new Goods());
    return "createProduct";
  }

  @GetMapping(value = "/admin")
  public String viewAdminAcc(Model model) {
    return "adminPage";
  }

  @GetMapping(value = "/createProduct")
  public String viewCreatePage(Model model) {
    model.addAttribute("message", null);
    model.addAttribute("createProduct", new Goods());
    model.addAttribute("listOfCategory", categoryService.all());
    model.addAttribute("listOfSize", sizeService.all());
    return "createProduct";
  }

//  @PostMapping(value = "/createProduct")
//  public String createProduct(@ModelAttribute("createProduct") GoodsDTO goods, Model model) {
//
//
//
//    try {
//      productServiceImpl.createProduct(goods);
//    } catch (Exception e) {
//      throw new ResourceCreationException("Exception in creation new Goods" + e);
//    }
//    return "redirect:/admin";
//  }


}
