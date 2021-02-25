package com.tsipadan.controller;

import com.tsipadan.dto.UserAddressDTO;
import com.tsipadan.dto.UserDTO;
import com.tsipadan.service.api.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequiredArgsConstructor
public class UserInformationController {

  private final UserService userService;

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
  }

  /**
   * Get account information
   *
   * @param model - model
   * @return accountInformation.jsp
   */
  @GetMapping(value = "/accInfo")
  public String viewAllAccountInformation(Model model) {
    return "accountInformation";
  }

  /**
   * Get account details
   *
   * @param model - model
   * @param authentication - login name
   * @return viewAccInfo.jsp
   */
  @GetMapping(value = "/viewAccInfo")
  public String viewAccountDetails(Model model, Authentication authentication) {
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    model.addAttribute("user", userService.findByUserName(userDetails.getUsername()));
    UserAddressDTO userAddressDTO = userService.findAddressByUsername(userDetails.getUsername());
    model.addAttribute("userAddress", userAddressDTO);
    return "viewAccInfo";
  }

  /**
   * Get edit account page
   *
   * @param id - user id
   * @param model - model
   * @return editAccInfo.jsp
   */
  @GetMapping(value = "/editAccInfo")
  public String viewEditAccountInfo(@RequestParam("id") long id, Model model) {
    model.addAttribute("user", userService.findById(id));
    return "editAccInfo";
  }

  /**
   * Update user information
   *
   * @param userDTO - user
   * @return viewAccInfo.jsp
   */
  @PostMapping(value = "/editAccInfo")
  public String editInformation(@ModelAttribute("user") UserDTO userDTO) {
    userService.update(userDTO);
    return "redirect:/viewAccInfo";
  }

  /**
   * Get address information from username
   *
   * @param name -username
   * @param model - model
   * @return editAddressInfo.jsp
   */
  @GetMapping(value = "/editAddress")
  public String viewAddressInformation(@RequestParam("username") String name, Model model) {
    model.addAttribute("address", userService.findAddressByUsername(name));
    return "editAddressInfo";
  }

  /**
   * Edit user address
   *
   * @param userAddressDTO - userAddress
   * @return viewAccInfo.jsp
   */
  @PostMapping(value = "/editAddress")
  public String editAddressInformation(@ModelAttribute("address") UserAddressDTO userAddressDTO) {
    userService.updateAddress(userAddressDTO);
    return "redirect:/viewAccInfo";
  }

  /**
   * Get change password page
   *
   * @param model - model
   * @return editPassword.jsp
   */
  @GetMapping(value = "/changePassword")
  public String viewChangePasswordPage(Model model) {
    return "editPassword";
  }

  /**
   * Update password for user
   *
   * @param request - get user password
   * @param model - model
   * @return viewAccInfo.jsp
   */
  @PostMapping(value = "/changePassword")
  public String changePasswordForm(HttpServletRequest request, Model model) {
    UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String password = request.getParameter("userPassword");
    userService.updatePassword(password, userDetails.getUsername());
    return "redirect:/viewAccInfo";
  }

}
