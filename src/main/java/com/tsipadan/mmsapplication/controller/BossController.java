//package com.tsipadan.mmsapplication.controller;
//
//import com.tsipadan.mmsapplication.dao.OrderDAO;
//import com.tsipadan.mmsapplication.dao.ProductDAO;
//import com.tsipadan.mmsapplication.model.CartInfo;
//import com.tsipadan.mmsapplication.model.CustomerInfo;
//import com.tsipadan.mmsapplication.model.PaginationResult;
//import com.tsipadan.mmsapplication.entity.Product;
//import com.tsipadan.mmsapplication.model.ProductInfo;
//import com.tsipadan.mmsapplication.util.Utils;
//import com.tsipadan.mmsapplication.validator.CustomerInfoValidator;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.WebDataBinder;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Controller
//@Transactional
//@RequiredArgsConstructor
//@EnableWebMvc
//public class BossController {
//
//  private final OrderDAO orderDAO;
//  private final ProductDAO productDAO;
//  private final CustomerInfoValidator customerInfoValidator;
//
//  @InitBinder("customerInfoValidator")
//  public void myInitBinder(WebDataBinder dataBinder) {
//    dataBinder.setValidator(customerInfoValidator);
//  }
//
//  @RequestMapping(value = "/home")
//  public String home() {
//    return "index";
//  }
//
//  @RequestMapping(value = "/403")
//  public String accessDenied() {
//    return "403";
//  }
//
//  @RequestMapping(value = "/productList")
//  public String listProductHandler(Model model,
//                                   @RequestParam(value = "name", defaultValue = "") String likeName,
//                                   @RequestParam(value = "page", defaultValue = "1") int page) {
//    final int maxResult = 5;
//    final int maxResultForFilter = 20;
//    final int maxNavigationPage = 10;
//
//    PaginationResult<ProductInfo> result = productDAO.queryProducts(page, maxResult, maxNavigationPage, likeName);
//    model.addAttribute("paginationProducts", result);
//
//    PaginationResult<ProductInfo> filterOne = productDAO.filterOne(page, maxResultForFilter, maxNavigationPage);
//    model.addAttribute("paginationProductFilterOne", filterOne);
//
//    PaginationResult<ProductInfo> filterTwo = productDAO.filterTwo(page, maxResultForFilter, maxNavigationPage);
//    model.addAttribute("paginationProductFilterTwo", filterTwo);
//
//    return "productList";
//  }
//
//  @RequestMapping(value = "/buyProduct")
//  public String listProductHandler(HttpServletRequest request, @RequestParam(value = "code") String code) {
//
//    Product product = null;
//    if (code != null && code.length() > 0) {
//      product = productDAO.findProduct(code).orElse(null);
//    }
//    if (product != null) {
//      CartInfo cartInfo = Utils.getCartInSession(request);
//      ProductInfo productInfo = new ProductInfo(product);
//      cartInfo.addProduct(productInfo, 1);
//    }
//    return "redirect:/shoppingCart";
//  }
//
//  @GetMapping(value = "/shoppingCart")
//  public String shoppingCartHandler(HttpServletRequest request, Model model) {
//    CartInfo myCart = Utils.getCartInSession(request);
//    model.addAttribute("cartForm", myCart);
//    return "shoppingCart";
//  }
//
//  @PostMapping(value = "/shoppingCart")
//  public String shoppingCartUpdateQuantityHandler(HttpServletRequest request,
//                                                  @ModelAttribute("cartForm") CartInfo cartForm) {
//    CartInfo cartInfo = Utils.getCartInSession(request);
//    cartInfo.updateQuantity(cartForm);
//    return "redirect:/shoppingCart";
//  }
//
//  @GetMapping(value = "/shoppingCartRemoveProduct")
//  public String removeProductHandler(HttpServletRequest request,
//                                     @RequestParam(value = "code", defaultValue = "") String code) {
//    Product product = null;
//    if (code != null && code.length() > 0) {
//      product = productDAO.findProduct(code).orElse(null);
//    }
//    if (product != null) {
//      CartInfo cartInfo = Utils.getCartInSession(request);
//      ProductInfo productInfo = new ProductInfo(product);
//      cartInfo.removeProduct(productInfo);
//    }
//    return "redirect:/shoppingCart";
//  }
//
//  @GetMapping(value = "/shoppingCartCustomer")
//  public String shoppingCartCustomerForm(HttpServletRequest request, Model model) {
//    CartInfo cartInfo = Utils.getCartInSession(request);
//    if (cartInfo.isEmpty()) {
//      return "redirect:/shoppingCart";
//    }
//    CustomerInfo customerInfo = cartInfo.getCustomerInfo();
//    if (customerInfo == null) {
//      customerInfo = new CustomerInfo();
//    }
//    model.addAttribute("customerForm", customerInfo);
//    return "shoppingCartCustomer";
//  }
//
//  @PostMapping(value = "/shoppingCartCustomer")
//  public String shoppingCartCustomerSave(HttpServletRequest request,
//                                         @ModelAttribute("customerForm")
//                                         @Validated CustomerInfo customerForm,
//                                         BindingResult bindingresult) {
//    if (bindingresult.hasErrors()) {
//      customerForm.setValid(false);
//      return "shoppingCartCustomer";
//    }
//    customerForm.setValid(true);
//    CartInfo cartInfo = Utils.getCartInSession(request);
//    cartInfo.setCustomerInfo(customerForm);
//    return "redirect:/shoppingCartConfirmation";
//  }
//
//  @GetMapping(value = "/shoppingCartConfirmation")
//  public String shoppingCartConfirmationReview(HttpServletRequest request, Model model) {
//    CartInfo cartInfo = Utils.getCartInSession(request);
//    if (cartInfo.isEmpty()) {
//      return "redirect:/shoppingCart";
//    } else if (!cartInfo.isValidCustomer()) {
//      return "redirect:/shoppingCartCustomer";
//    }
//    model.addAttribute("myCart", cartInfo);
//    return "shoppingCartConfirmation";
//  }
//
//  @PostMapping(value = "/shoppingCartConfirmation")
//  @Transactional(propagation = Propagation.NEVER)
//  public String shoppingCartConfirmationSend(HttpServletRequest request) {
//    CartInfo cartInfo = Utils.getCartInSession(request);
//    if (cartInfo.isEmpty()) {
//      return "redirect:/shoppingCart";
//    } else if (!cartInfo.isValidCustomer()) {
//      return "redirect:/shoppingCartCustomer";
//    }
//
//    try {
//      orderDAO.saveOrder(cartInfo);
//    } catch (Exception e) {
//      return "shoppingCartConfirmation";
//    }
//
//    Utils.removeCartInSession(request);
//    Utils.storeLastOrderedCartInSession(request, cartInfo);
//    return "redirect:/shoppingCartFinalize";
//  }
//
//  @GetMapping(value = "/shoppingCartFinalize")
//  public String shoppingCartFinalize(HttpServletRequest request) {
//    CartInfo lastOrderedCart = Utils.getLastOrderedCartInSession(request);
//    if (lastOrderedCart == null) {
//      return "redirect:/shoppingCart";
//    }
//    return "shoppingCartFinalize";
//  }
//
//  @GetMapping(value = "/productImage")
//  public void productImage(HttpServletResponse response,
//                           @RequestParam("code") String code) throws IOException {
//    Product product = null;
//    if (code != null) {
//      product = this.productDAO.findProduct(code).orElse(null);
//    }
//    if (product != null && product.getImage() != null) {
//      response.setContentType("image/jpeg, image/jpg, image/png");
//      response.getOutputStream().write(product.getImage());
//    }
//    response.getOutputStream().close();
//  }
//
//}
