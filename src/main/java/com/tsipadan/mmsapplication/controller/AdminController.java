package com.tsipadan.mmsapplication.controller;

import com.tsipadan.mmsapplication.dao.OrderDAO;
import com.tsipadan.mmsapplication.dao.ProductDAO;
import com.tsipadan.mmsapplication.model.OrderDetailInfo;
import com.tsipadan.mmsapplication.model.OrderInfo;
import com.tsipadan.mmsapplication.model.PaginationResult;
import com.tsipadan.mmsapplication.model.ProductInfo;
import com.tsipadan.mmsapplication.validator.ProductInfoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Transactional
@EnableWebMvc
@RequiredArgsConstructor
public class AdminController {

  private final OrderDAO orderDAO;
  private final ProductDAO productDAO;
  private final ProductInfoValidator productInfoValidator;

  @InitBinder
  public void myInitBinder(WebDataBinder dataBinder) {
    Object target = dataBinder.getTarget();
    if (target == null) {
      return;
    }
    System.out.println("Target=" + target);

    if (target.getClass() == ProductInfo.class) {
      dataBinder.setValidator(productInfoValidator);
      dataBinder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
    }
  }

  @GetMapping(value = "/log_in")
  public String login(Model model) {
    return "log_in";
  }

  @GetMapping(value = "/accountInfo")
  public String accountInfo(Model model) {
    UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    System.out.println(userDetails.getPassword());
    System.out.println(userDetails.getUsername());
    System.out.println(userDetails.isEnabled());
    model.addAttribute("userDetails", userDetails);
    return "accountInfo";
  }

  @GetMapping(value ="/orderList")
  public String orderList(Model model,
                          @RequestParam(value = "page", defaultValue = "1") String pageStr) {
    int page = 1;
    try {
      page = Integer.parseInt(pageStr);
    } catch (Exception e) {
      String message = e.getMessage();
      model.addAttribute("message",message);
    }
    final int MAX_RESULT = 5;
    final int MAX_NAVIGATION_PAGE = 10;
    PaginationResult<OrderInfo> paginationResult = orderDAO.listOrderInfo(page, MAX_RESULT, MAX_NAVIGATION_PAGE);
    model.addAttribute("paginationResult", paginationResult);
    return "orderList";
  }

  @GetMapping(value = "/product")
  public String product(Model model, @RequestParam(value = "code", defaultValue = "") String code) {
    ProductInfo productInfo = null;
    if (code != null && code.length() > 0) {
      productInfo = productDAO.findProductInfo(code);
    }
    if (productInfo == null) {
      productInfo = new ProductInfo();
      productInfo.setNewProduct(true);
    }
    model.addAttribute("productForm", productInfo);
    return "product";
  }

  @PostMapping(value = "/product")
  @Transactional(propagation = Propagation.NEVER)
  public String productSave(Model model, @ModelAttribute("productForm") @Validated ProductInfo productInfo,
                            BindingResult result, final RedirectAttributes redirectAttributes) {

    if (result.hasErrors()) {
      return "product";
    }
    try {
      productDAO.save(productInfo);
    } catch (Exception e) {
      String message = e.getMessage();
      model.addAttribute("message", message);
      return "product";
    }
    return "redirect:/productList";
  }

  @GetMapping(value = "/order")
  public String orderView(Model model, @RequestParam("orderId") String orderId) {
    OrderInfo orderInfo = null;
    if (orderId != null) {
      orderInfo = this.orderDAO.getOrderInfo(orderId);
    }
    if (orderInfo == null) {
      return "redirect:/orderList";
    }
    List<OrderDetailInfo> details = this.orderDAO.listOrderDetailInfos(orderId);
    orderInfo.setDetails(details);
    model.addAttribute("orderInfo", orderInfo);
    return "order";
  }
}
