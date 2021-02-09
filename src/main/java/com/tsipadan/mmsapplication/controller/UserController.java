package com.tsipadan.mmsapplication.controller;

import com.tsipadan.mmsapplication.dto.UserDTO;
import com.tsipadan.mmsapplication.entity.User;
import com.tsipadan.mmsapplication.service.CategoryService;
import com.tsipadan.mmsapplication.service.api.ProductService;
import com.tsipadan.mmsapplication.service.SizeService;
import com.tsipadan.mmsapplication.service.UserServiceImpl;
import com.tsipadan.mmsapplication.service.api.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class UserController {

//  private final UserService userService;
//  private final OrderDAO orderDAO;

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

  @Autowired
  private UserService userService;
  @Autowired
  private ProductService productService;
  @Autowired
  private CategoryService categoryService;
  @Autowired
  private SizeService sizeService;

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
  }

  @GetMapping(value = "/yes")
  public String youDidIt(Model model){
    return "youdidit";
  }

  @GetMapping(value = "/error")
  public String error(Model model){
    return "error";
  }

  @GetMapping(value = "/home")
  public String homePage(Model model){
    return "/home";
  }

  @GetMapping(value = "/reg")
  public String registrationPage(Model model) {
    model.addAttribute("userForm", new User());
    return "newRegistration";
  }

  @PostMapping(value = "/reg")
  public String saveUser(@Valid @ModelAttribute("userForm") UserDTO userForm,
                         BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      System.out.println(bindingResult.hasErrors() + " wrong input");
      return "newRegistration";
    }
    if (!userService.save(userForm)) {
      model.addAttribute("usernameError", "User with input name already exist");
      return "newRegistration";
    }
    return "redirect:/youdidit";
  }

  @GetMapping(value = "/login")
  public String viewLoginPage(Model model, @RequestParam(value = "error", required = false) String error){
    if (error != null){
      model.addAttribute("error", " Incorrect data ");
    }
    return "login";
  }

  @GetMapping(value = "/accInfo")
  public String viewAllAccountInformation(Model model){
    return "accountInformation";
  }

  @GetMapping(value = "/viewAccInfo")
  public String viewAccountDetails(Model model, Authentication authentication){
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    model.addAttribute("user", userService.findByUserName(userDetails.getUsername()));
    return "viewAccInfo";
  }

  @GetMapping(value = "/editAccInfo")
  public String viewEditAccountInfo(@RequestParam("id") long id, Model model){
    model.addAttribute("user", userService.findById(id));
    return "editAccInfo";
  }

  @PostMapping(value = "/editAccInfo")
  public String EditInformation(@ModelAttribute("user") UserDTO user, Model model){
    userService.update(user);
    return "redirect:/viewAccInfo";
  }

  @GetMapping(value = "/changePassword")
  public String viewChangePasswordPage(Model model){
    return "editPassword";
  }

  @PostMapping(value = "/changePassword")
  public String changePasswordForm(HttpServletRequest request, Model model){
    UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String password = request.getParameter("userPassword");
    userService.updatePassword(password, userDetails.getUsername());
    return "redirect:/viewAccInfo";
  }

  @GetMapping(value = "/store")
  public String viewStorePage(Model model){
//    model.addAttribute("category", categoryService.all());
    model.addAttribute("allGoods", productService.getAllGoods());
    return "store";
  }



}
