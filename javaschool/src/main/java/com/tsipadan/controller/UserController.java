package com.tsipadan.controller;

import com.tsipadan.dto.GoodsDTO;
import com.tsipadan.dto.UserAddressDTO;
import com.tsipadan.dto.UserOrderDTO;
import com.tsipadan.entity.Goods;
import com.tsipadan.mapper.ProductMapper;
import com.tsipadan.repository.ProductRepository;
import com.tsipadan.service.api.OrderService;
import com.tsipadan.service.api.ProductService;
import com.tsipadan.service.api.UserService;
import com.tsipadan.util.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;
  private final ProductService productService;
  private final ProductRepository productRepository;
  private final ProductMapper productMapper;
  private final OrderService orderService;

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
  }

  /**
   * Get order history
   *
//   * @param orderNum - order number
   * @param model - model
   * @return userOrderHistory.jsp
   */
  @GetMapping(value = "/userHistory")
  public String getOrderHistory( Authentication authentication, Model model) {
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    List<UserOrderDTO> dto = orderService.getUserOrderInfoByName(userDetails.getUsername());
    model.addAttribute("orders", dto);
    return "userOrderHistory";
  }

  /**
   * Get cart page
   *
   * @param request - save cart in session
   * @param model - model
   * @return cart.jsp
   */
  @GetMapping(value = "/shoppingCart")
  public String cartHandler(HttpServletRequest request, Model model) {
    UserOrderDTO myCart = Utils.getCartInSession(request);
    model.addAttribute("cartForm", myCart);
    return "cart";
  }

  /**
   * Buy product
   *
   * @param request - save cart in session
   * @param id - goods id
   * @return cart.jsp
   */
  @RequestMapping(value = "/buyProduct")
  public String listGoodsHandler(HttpServletRequest request, @RequestParam(value = "id") long id) {

    GoodsDTO goods = productService.findByIdGoodsDto(id);
    if (goods != null) {
      UserOrderDTO userOrderDTO = Utils.getCartInSession(request);
      userOrderDTO.addGoodsInOrderInGoodsList(goods, 1);
    }
    return "redirect:/shoppingCart";
  }

  /**
   * Remove goods from cart
   *
   * @param request - request
   * @param id - goods id
   * @return cart.jsp
   */
  @GetMapping(value = "/shoppingCartRemoveProduct")
  @Transactional
  public String removeProductFromCart(HttpServletRequest request,
                                      @RequestParam(value = "id", defaultValue = "") long id) {
    Goods goods = null;
    if (id > 0) {
      goods = productRepository.getOne(id);
    }
    if (goods != null) {
      UserOrderDTO userOrderDTO = Utils.getCartInSession(request);
      GoodsDTO goodsDTO = productMapper.toDto(goods);
      userOrderDTO.removeProduct(goodsDTO);
    }
    return "redirect:/shoppingCart";
  }

  /**
   * Update quantity
   *
   * @param request - request
   * @param userOrderDTOForm - userOrder
   * @return cart.jsp
   */
  @PostMapping(value = "/shoppingCart")
  @Transactional
  public String updateQuantityHandler(HttpServletRequest request,
                                      @ModelAttribute("cartForm") UserOrderDTO userOrderDTOForm) {
    UserOrderDTO userOrderDTO = Utils.getCartInSession(request);
    userOrderDTO.updateQuantity(userOrderDTOForm);
    return "redirect:/shoppingCart";
  }

  /**
   * Get customer form
   *
   * @param request - get in cart session
   * @param model - model
   * @param authentication - get address from user
   * @return cartCustomerForm.jsp
   */
  @GetMapping(value = "/shoppingCartCustomer")
  @Transactional
  public String shoppingCartCustomerForm(HttpServletRequest request, Model model, Authentication authentication) {

    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    UserAddressDTO address = userService.findAddressByUsername(userDetails.getUsername());
    model.addAttribute("userAddress", address);

    UserOrderDTO userOrderDTO = Utils.getCartInSession(request);
    if (userOrderDTO.isEmpty()) {
      return "redirect:/shoppingCart";
    }
    UserAddressDTO userAddressDTO = userOrderDTO.getUserAddressDTO();
    if (userAddressDTO == null) {
      userAddressDTO = new UserAddressDTO();
    }
    model.addAttribute("customerForm", userAddressDTO);
    return "cartCustomerForm";
  }

  /**
   * Save customer form
   *
   * @param request - request
   * @param userAddressDTO - userAddress
   * @param bindingResult - result
   * @return cartConfirm.jsp
   */
  @PostMapping(value = "/shoppingCartCustomer")
  @Transactional
  public String saveShoppingCartCustomerForm(HttpServletRequest request,
                                             @ModelAttribute("customerForm")
                                             @Valid UserAddressDTO userAddressDTO,
                                             BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      userAddressDTO.setValid(false);
      return "cartCustomerForm";
    }
    userAddressDTO.setValid(true);
    UserOrderDTO userOrderDTO = Utils.getCartInSession(request);
    userOrderDTO.setUserAddressDTO(userAddressDTO);
    return "redirect:/shoppingCartConfirmation";
  }

  /**
   * Get confirmation information
   *
   * @param request - request
   * @param model - model
   * @return cartConfirm.jsp
   */
  @GetMapping(value = "/shoppingCartConfirmation")
  public String viewShoppingCartReview(HttpServletRequest request, Model model) {
    UserOrderDTO userOrderDTO = Utils.getCartInSession(request);
    if (userOrderDTO.isEmpty()) {
      return "redirect:/cart";
    } else if (userOrderDTO.isValidCustomer()) {
      return "redirect:/cartCustomerForm";
    }
    model.addAttribute("myCart", userOrderDTO);
    return "cartConfirm";
  }

  /**
   * Save order
   *
   * @param request - remove cart in session
   * @return cartFinalPage.jsp
   */
  @PostMapping(value = "/shoppingCartConfirmation")
  @Transactional(propagation = Propagation.NEVER)
  public String sendShoppingCartReview(HttpServletRequest request, Authentication authentication,
                                       @RequestParam("howPay")String howPay,
                                       @RequestParam("howDeliver")String howDeliver) {
    UserOrderDTO lastOrderedCart = Utils.getCartInSession(request);
    if (lastOrderedCart.isEmpty()) {
      return "redirect:/cart";
    } else if (lastOrderedCart.isValidCustomer()) {
      return "redirect:/cartCustomerForm";
    }
    try {
      UserDetails details = (UserDetails) authentication.getPrincipal();
      orderService.saveOrder(lastOrderedCart, details, howPay, howDeliver, request);
    } catch (Exception e) {
      e.printStackTrace();
      return "cartConfirm";
    }
    Utils.removeCartInSession(request);
    Utils.storeLastOrderedCartInSession(request, lastOrderedCart);
    return "redirect:/shoppingCartFinal";
  }

  /**
   * Get final step in buy product session
   *
   * @param request - request
   * @return cartFinalPage.jsp
   */
  @GetMapping(value = "/shoppingCartFinal")
  public String viewCartFinalPage(HttpServletRequest request, Model model) {
    UserOrderDTO lastOrderedCart = Utils.getLastOrderedCartInSession(request);
    if (lastOrderedCart == null) {
      return "redirect:/cart";
    }
    model.addAttribute("lastOrderedCart", request.getAttribute("lastOrderedCart"));
    return "cartFinalPage";
  }

}
