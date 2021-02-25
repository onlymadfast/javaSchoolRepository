package com.tsipadan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  /**
   * Return home page
   *
   * @param model - model
   * @return home.jsp
   */
  @GetMapping(value = "/home")
  public String homePage(Model model) {
    return "home";
  }

  /**
   * Return error page
   *
   * @param model - model
   * @return error.jsp
   */
  @GetMapping(value = "/error")
  public String errorPage(Model model) {
    return "error";
  }



}
