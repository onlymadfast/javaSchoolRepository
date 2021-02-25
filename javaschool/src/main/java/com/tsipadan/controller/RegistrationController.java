package com.tsipadan.controller;

import com.tsipadan.dto.UserDTO;
import com.tsipadan.service.api.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequiredArgsConstructor
public class RegistrationController {

  private final UserService userService;

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
  }

  /**
   * Return registration page
   *
   * @param model - model
   * @return newRegistration.jsp
   */
  @GetMapping(value = "/reg")
  public String registrationPage(Model model) {
    model.addAttribute("userForm", new UserDTO());
    return "newRegistration";
  }

  /**
   * Save new user
   *
   * @param userForm - userDto
   * @param bindingResult - result
   * @param model - model
   * @return home.jsp
   */
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
    return "redirect:/home";
  }

  /**
   * Get login page
   *
   * @param model - model
   * @param error - error
   * @return security return home.jsp
   */
  @GetMapping(value = "/login")
  public String viewLoginPage(Model model, @RequestParam(value = "error", required = false) String error) {
    if (error != null) {
      model.addAttribute("error", " Incorrect data ");
    }
    return "login";
  }

}
