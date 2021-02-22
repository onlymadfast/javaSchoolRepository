package com.tsipadan.mmsapplication.controller;

import com.tsipadan.dto.GoodsDTO;
import com.tsipadan.dto.UserAddressDTO;
import com.tsipadan.dto.UserDTO;
import com.tsipadan.dto.UserOrderDTO;
import com.tsipadan.mmsapplication.entity.Goods;
import com.tsipadan.mmsapplication.mapper.ItemCategoryMapper;
import com.tsipadan.mmsapplication.mapper.ProductMapper;
import com.tsipadan.mmsapplication.mapper.UserAddressMapper;
import com.tsipadan.mmsapplication.repository.api.ProductRepository;
import com.tsipadan.mmsapplication.service.api.CategoryService;
import com.tsipadan.mmsapplication.service.api.OrderService;
import com.tsipadan.mmsapplication.service.api.ProductService;
import com.tsipadan.mmsapplication.service.api.UserService;
import com.tsipadan.mmsapplication.util.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
  private final CategoryService categoryService;
  private final ProductRepository productRepository;
  private final ProductMapper productMapper;
  private final UserAddressMapper userAddressMapper;
  private final ItemCategoryMapper categoryMapper;
  private final OrderService orderService;

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
  }

  @GetMapping(value = "/yes")
  public String youDidIt(Model model) {
    return "youdidit";
  }

  @GetMapping(value = "/error")
  public String error(Model model) {
    return "error";
  }

  @GetMapping(value = "/home")
  public String homePage(Model model) {
    return "/home";
  }

  @GetMapping(value = "/reg")
  public String registrationPage(Model model) {
    model.addAttribute("userForm", new UserDTO());
    return "newRegistration";
  }

  @PostMapping(value = "/reg")
  public String saveUser(@Valid @ModelAttribute("userForm") UserDTO userForm,
                         BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      System.out.println(bindingResult.hasErrors() + " Wrong input");
      return "newRegistration";
    }
    if (!userService.save(userForm)) {
      model.addAttribute("usernameError", "User with input name already exist");
      return "newRegistration";
    }
    return "redirect:/yes";
  }

  @GetMapping(value = "/login")
  public String viewLoginPage(Model model, @RequestParam(value = "error", required = false) String error) {
    if (error != null) {
      model.addAttribute("error", " Incorrect data ");
    }
    return "login";
  }

  ////////////////////////////

  @GetMapping(value = "/accInfo")
  public String viewAllAccountInformation(Model model) {
    return "accountInformation";
  }

  @GetMapping(value = "/viewAccInfo")
  public String viewAccountDetails(Model model, Authentication authentication) {
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    model.addAttribute("user", userService.findByUserName(userDetails.getUsername()));

    UserAddressDTO userAddressDTO = userService.findAddressByUsername(userDetails.getUsername());
    if (userAddressDTO == null) {
      return "viewAccInfoWithOutAddress";
    }

    model.addAttribute("userAddress", userAddressDTO);
    return "viewAccInfo";
  }

  ////////////////////////////

  @GetMapping(value = "/editAddress")
  public String viewAddressInformation(@RequestParam("username") String name, Model model) {
    model.addAttribute("address", userService.findAddressByUsername(name));
    return "editAddressInfo";
  }

  @PostMapping(value = "/editAddress")
  public String editAddressInformation(@ModelAttribute("address") UserAddressDTO userAddressDTO) {
    userService.updateAddress(userAddressDTO);
    return "redirect:/viewAccInfo";
  }

  //////////////////////////

  @GetMapping(value = "/editAccInfo")
  public String viewEditAccountInfo(@RequestParam("id") long id, Model model) {
    model.addAttribute("user", userService.findById(id));
    return "editAccInfo";
  }

  @PostMapping(value = "/editAccInfo")
  public String editInformation(@ModelAttribute("user") UserDTO userDTO) {
    userService.update(userDTO);
    return "redirect:/viewAccInfo";
  }

  //////////////////////////

  @GetMapping(value = "/changePassword")
  public String viewChangePasswordPage(Model model) {
    return "editPassword";
  }

  @PostMapping(value = "/changePassword")
  public String changePasswordForm(HttpServletRequest request, Model model) {
    UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String password = request.getParameter("userPassword");
    userService.updatePassword(password, userDetails.getUsername());
    return "redirect:/viewAccInfo";
  }

  /////////////////////////

  @RequestMapping(value = "/store")
  public String viewStorePage(Model model) {
    return listByPage(model, 1);
  }

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

  @GetMapping(value = "/showResult/{itemCategory}")
  public String listByCat(Model model, @PathVariable("itemCategory") String itemCategory) {

    if (itemCategory.isBlank()) return "store";

    model.addAttribute("thisCategory", itemCategory);
    model.addAttribute("listCategory", categoryService.all());
    model.addAttribute("goodsByCat", productService.getGoodsByItemCategory(itemCategory));
    return "storeByItemCategory";
  }

  @GetMapping(value = "/userHistory")
  public String getOrderHistory(@RequestParam(value = "orderNum", defaultValue = "") String orderNum, Model model) {
    UserOrderDTO dto = orderService.getOrderInformation(orderNum);
    model.addAttribute("order", dto);
    return "userOrderHistory";
  }

  //  @GetMapping(value = "/orderHistoryList")
//  public String viewOrderHistory(Model model,
//                                 @RequestParam(value = "orderNum", defaultValue = "") String orderNum) {
//
//    Optional.ofNullable(orderNum)
//        .filter(s -> !s.isBlank())
//        .map(orderDAO::getOrderInfoForUser)
//        .map(orderInfo -> {
//          orderInfo.setDetails(this.orderDAO.listOrderDetailInfoForUser(orderNum));
//          return orderInfo;
//        }).ifPresent(orderInfo -> model.addAttribute("orderInfo", orderInfo));
//
//    return "orderHistoryList";
//  }

  /**
   * /////////////////////////////buy product//////////////////////////////
   */

  @RequestMapping(value = "/buyProduct")
  @Transactional
  public String listGoodsHandler(HttpServletRequest request, @RequestParam(value = "id") long id) {
    Goods goods = null;
    if (id > 0) {
      goods = productRepository.getOne(id);
    }
    if (goods != null) {
      UserOrderDTO userOrderDTO = Utils.getCartInSession(request);
      GoodsDTO goodsDTO = productMapper.toDto(goods);
      userOrderDTO.addGoods(goodsDTO, 1);
    }
    return "redirect:/shoppingCart";
  }

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

  @GetMapping(value = "/shoppingCart")
  public String cartHandler(HttpServletRequest request, Model model) {
    UserOrderDTO myCart = Utils.getCartInSession(request);
    model.addAttribute("cartForm", myCart);
    return "cart";
  }

  @PostMapping(value = "/shoppingCart")
  @Transactional
  public String updateQuantityHandler(HttpServletRequest request,
                                      @ModelAttribute("cartForm") UserOrderDTO userOrderDTOForm) {
    UserOrderDTO userOrderDTO = Utils.getCartInSession(request);
    userOrderDTO.updateQuantity(userOrderDTOForm);
    return "redirect:/shoppingCart";
  }


  @GetMapping(value = "/shoppingCartCustomer")
  @Transactional
  public String shoppingCartCustomerForm(HttpServletRequest request, Model model) {

//    Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
//    String name = loggedInUser.getName();
//    model.addAttribute("userAddress", userService.findAddressByUsername(name));

    //TODO: уже получил адрес, вставить сюда надо!

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

  @GetMapping(value = "/shoppingCartConfirmation")
  public String viewShoppingCartReview(HttpServletRequest request, Model model) {
    UserOrderDTO userOrderDTO = Utils.getCartInSession(request);
    if (userOrderDTO.isEmpty()) {
      return "redirect:/shoppingCart";
    } else if (userOrderDTO.isValidCustomer()) {
      return "redirect:/shoppingCartCustomer";
    }
    model.addAttribute("myCart", userOrderDTO);
    return "cartConfirm";
  }

  @PostMapping(value = "/shoppingCartConfirmation")
  @Transactional(propagation = Propagation.NEVER)
  public String sendShoppingCartReview(HttpServletRequest request) {
    UserOrderDTO userOrderDTO = Utils.getCartInSession(request);
    if (userOrderDTO.isEmpty()) {
      return "redirect:/shoppingCart";
    } else if (userOrderDTO.isValidCustomer()) {
      return "redirect:/shoppingCartCustomer";
    }

    try {
      orderService.saveOrder(userOrderDTO);
    } catch (Exception e) {
      return "cartConfirm";
    }

    Utils.removeCartInSession(request);
    Utils.storeLastOrderedCartInSession(request, userOrderDTO);
    return "redirect:/shoppingCartFinal";
  }

  @GetMapping(value = "/shoppingCartFinal")
  public String viewCartFinalPage(HttpServletRequest request) {
    UserOrderDTO userOrderDTO = Utils.getLastOrderedCartInSession(request);
    if (userOrderDTO == null) {
      return "redirect:/shoppingCart";
    }
    return "cartFinalPage";
  }

}
