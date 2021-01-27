package com.tsipadan.mmsapplication.controller;

import com.tsipadan.mmsapplication.dao.AccountDAO;
import com.tsipadan.mmsapplication.dao.OrderDAO;
import com.tsipadan.mmsapplication.entity.Account;
import com.tsipadan.mmsapplication.entity.Order;
import com.tsipadan.mmsapplication.model.OrderDetailInfo;
import com.tsipadan.mmsapplication.model.OrderInfo;
import com.tsipadan.mmsapplication.model.PaginationResult;
import com.tsipadan.mmsapplication.service.OrderService;
import com.tsipadan.mmsapplication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;
  private final OrderDAO orderDAO;

  @GetMapping(value = "/regPage")
  public String viewRegistrationPage(Model model){
    return "registrationPage";
  }

  @PostMapping(value = "/regPage")
  public String saveNewAccount(Model model, @ModelAttribute("account") Account account){
    userService.saveUser(account);
    return "redirect:/accountInfo";
  }

  @GetMapping(value = "/orderHistoryList")
  public String viewOrderHistory(Model model,
                                 @RequestParam(value = "page", defaultValue = "1") String pageStr){

    int page = Integer.parseInt(pageStr);
    final int MAX_RESULT = 5;
    final int MAX_NAVIGATION_PAGE = 10;
    PaginationResult<OrderInfo> paginationResult = orderDAO.listOrderInfo(page, MAX_RESULT, MAX_NAVIGATION_PAGE);
    model.addAttribute("paginationResult", paginationResult);

    return "orderHistoryList";
  }

  @GetMapping(value = "/changePassword")
  public String changePasswordForm(Model model){
    return "changePasswordPage";
  }

  @PostMapping(value = "/changePassword")
  public String changePasswordForm(HttpServletRequest request, Model model){
    UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String password = request.getParameter("password");
    userService.updatePassword(password, userDetails.getUsername());
    return "redirect:/accountInfo";
  }

}
