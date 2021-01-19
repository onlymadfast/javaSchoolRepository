package com.tsipadan.mmsapplication.controller;

import com.tsipadan.mmsapplication.dao.OrderDAO;
import com.tsipadan.mmsapplication.dao.ProductDAO;
import com.tsipadan.mmsapplication.model.CartInfo;
import com.tsipadan.mmsapplication.model.CustomerInfo;
import com.tsipadan.mmsapplication.model.PaginationResult;
import com.tsipadan.mmsapplication.entity.Product;
import com.tsipadan.mmsapplication.model.ProductInfo;
import com.tsipadan.mmsapplication.util.Utils;
import com.tsipadan.mmsapplication.validator.CustomerInfoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@Transactional
@RequiredArgsConstructor
@EnableWebMvc
public class BossController {

  private final CustomerInfoValidator customerInfoValidator;
  private final OrderDAO orderDAO;
  private final ProductDAO productDAO;

  @InitBinder
  public void myInitBinder(WebDataBinder dataBinder) {
    Object target = dataBinder.getTarget();
    if (target == null) {
      return;
    }
    System.out.println("Target=" + target);

    if (target.getClass() == CartInfo.class) {
      dataBinder.setValidator(customerInfoValidator);
    } else if (target.getClass() == CustomerInfo.class) {
      dataBinder.setValidator(customerInfoValidator);
    }
  }

  @GetMapping(value = "/403")
  public String accessDenied() {
    return "/403";
  }

  @GetMapping(value = "/")
  public String home() {
    return "index";
  }

  @GetMapping(value = "/productList")
  public String listProductHandler(Model model,
                                   @RequestParam(value = "name", defaultValue = "") String likeName,
                                   @RequestParam(value = "page", defaultValue = "1") int page) {
    final int maxResult = 5;
    final int maxNavigationPage = 10;

    PaginationResult<ProductInfo> result = productDAO.queryProducts(page, maxResult, maxNavigationPage, likeName);

    model.addAttribute("paginationProducts", result);
    return "productList";
  }

  @RequestMapping(value = "/buyProduct")
  public String listProductHandler(HttpServletRequest request, Model model,
                                   @RequestParam(value = "code", defaultValue = "") String code) {

    Product product = null;
    if (code != null && code.length() > 0) {
      product = productDAO.findProduct(code);
    }
    if (product != null) {
      CartInfo cartInfo = Utils.getCartInSession(request);
      ProductInfo productInfo = new ProductInfo(product);
      cartInfo.addProduct(productInfo, 1);
    }
    return "redirect:/shoppingCart";
  }

  @RequestMapping(value = "/shoppingCartRemoveProduct")
  public String removeProductHandler(HttpServletRequest request, Model model,
                                     @RequestParam(value = "code", defaultValue = "") String code) {
    Product product = null;
    if (code != null && code.length() > 0) {
      product = productDAO.findProduct(code);
    }
    if (product != null) {
      CartInfo cartInfo = Utils.getCartInSession(request);
      ProductInfo productInfo = new ProductInfo(product);
      cartInfo.removeProduct(productInfo);
    }
    return "redirect:/shoppingCart";
  }

  @PostMapping(value = "/shoppingCart")
  public String shoppingCartUpdateQuantity(HttpServletRequest request, Model model,
                                      @ModelAttribute("cartForm") CartInfo cartForm) {
    CartInfo cartInfo = Utils.getCartInSession(request);
    cartInfo.updateQuantity(cartForm);
    return "redirect:/shoppingCart";
  }

  @GetMapping(value = "/shoppingCart")
  public String shoppingCartHandler(HttpServletRequest request, Model model) {
    CartInfo myCart = Utils.getCartInSession(request);
    model.addAttribute("cartForm", myCart);
    return "shoppingCart";
  }

  @GetMapping(value = "/shoppingCartCustomer")
  public String shoppingCartCustomerForm(HttpServletRequest request, Model model) {
    CartInfo cartInfo = Utils.getCartInSession(request);
    if (cartInfo.isEmpty()) {
      return "redirect:/shoppingCart";
    }
    CustomerInfo customerInfo = cartInfo.getCustomerInfo();
    if (customerInfo == null) {
      customerInfo = new CustomerInfo();
    }
    model.addAttribute("customerForm", customerInfo);
    return "shoppingCartCustomer";
  }

  @PostMapping(value = "/shoppingCartCustomer")
  public String shoppingCartCustomerSave(HttpServletRequest request, Model model,
                                         @ModelAttribute("customerForm") @Validated CustomerInfo customerForm,
                                         BindingResult result, final RedirectAttributes redirectAttributes) {
    if (result.hasErrors()) {
      customerForm.setValid(false);
      return "shoppingCartCustomer";
    }
    customerForm.setValid(true);
    CartInfo cartInfo = Utils.getCartInSession(request);
    cartInfo.setCustomerInfo(customerForm);
    return "redirect:/shoppingCartConfirmation";
  }

  @GetMapping(value = "/shoppingCartConfirmation")
  public String shoppingCartConfirmationReview(HttpServletRequest request, Model model) {
    CartInfo cartInfo = Utils.getCartInSession(request);
    if (cartInfo.isEmpty()) {
      return "redirect:/shoppingCart";
    } else if (!cartInfo.isValidCustomer()) {
      return "redirect:/shoppingCartCustomer";
    }
    return "shoppingCartConfirmation";
  }

  @PostMapping(value = "/shoppingCartConfirmation")
  @Transactional(propagation = Propagation.NEVER)
  public String shoppingCartConfirmationSave(HttpServletRequest request, Model model) {
    CartInfo cartInfo = Utils.getCartInSession(request);
    if (cartInfo.isEmpty()) {
      return "redirect:/shoppingCart";
    } else if (!cartInfo.isValidCustomer()) {
      return "redirect:/shoppingCartCustomer";
    }

    try {
      orderDAO.saveOrder(cartInfo);
    } catch (Exception e) {
      return "shoppingCartConfirmation";
    }

    Utils.removeCartInSession(request);
    Utils.storeLastOrderedCartInSession(request, cartInfo);
    return "redirect:/shoppingCartFinalize";
  }

  @GetMapping(value = "/shoppingCartFinalize")
  public String shoppingCartFinalize(HttpServletRequest request, Model model) {
    CartInfo lastOrderedCart = Utils.getLastOrderedCartInSession(request);
    if (lastOrderedCart == null) {
      return "redirect:/shoppingCart";
    }
    return "shoppingCartFinalize";
  }

  @GetMapping(value = "/productImage")
  public void productImage(HttpServletRequest request, HttpServletResponse response, Model model,
                           @RequestParam("code") String code) throws IOException {
    Product product = null;
    if (code != null) {
      product = this.productDAO.findProduct(code);
    }
    if (product != null && product.getImage() != null) {
      response.setContentType("image/jpeg, image/jpg, image/png");
      response.getOutputStream().write(product.getImage());
    }
    response.getOutputStream().close();
  }





//  @GetMapping(value = "/registration")
//  public ModelAndView view_registration() {
//    final ModelAndView modelAndView = new ModelAndView();
//    modelAndView.addObject("registrationForm", new Client());
//    modelAndView.setViewName("registration_page");
//    return modelAndView;
//  }
//
//  @PostMapping(value = "/registration")
//  public ModelAndView addClient(@ModelAttribute("registrationForm")
//                                    ClientDto clientDto, BindingResult bindingResult){
//    final ModelAndView modelAndView = new ModelAndView();
//    clientService.saveClientAndAddressByDto(clientDto);
//    modelAndView.setViewName("redirect:/welcome");
//    return modelAndView;
//  }
//
//  @GetMapping(value = "/login")
//  public ModelAndView login(String error, String logout){
//    final ModelAndView modelAndView = new ModelAndView();
//    if (error != null){
//      modelAndView.addObject("error", "Your firstName and password is invalid.");
//    }
//    if (logout != null){
//      modelAndView.addObject("message", "You have been logged out successfully.");
//    }
//    modelAndView.setViewName("login");
//    return modelAndView;
//  }
}
