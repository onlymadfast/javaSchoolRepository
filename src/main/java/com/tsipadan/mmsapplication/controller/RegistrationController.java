package com.tsipadan.mmsapplication.controller;

import com.tsipadan.mmsapplication.dao.ClientDAOImpl;
import com.tsipadan.mmsapplication.dto.ClientDto;
import com.tsipadan.mmsapplication.model.Client;
import com.tsipadan.mmsapplication.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class RegistrationController {

  private final ClientService clientService;
//  private final ClientValidator clientValidator;
  private final ClientDAOImpl clientsDAO;


  @GetMapping(value = "/registration")
  public ModelAndView view_registration() {
    final ModelAndView modelAndView = new ModelAndView();
    modelAndView.addObject("registrationForm", new Client());
    modelAndView.setViewName("registration_page");
    return modelAndView;
  }

  @PostMapping(value = "/registration")
  public ModelAndView addClient(@ModelAttribute("registrationForm")
                                    ClientDto clientDto, BindingResult bindingResult){

    final ModelAndView modelAndView = new ModelAndView();
//    clientValidator.validate(clientDto, bindingResult);
//    if (bindingResult.hasErrors()){
//      return modelAndView;
//    }

    clientService.saveClientAndAddressByDto(clientDto);

    modelAndView.setViewName("redirect:/welcome");
    return modelAndView;
  }

  @GetMapping(value = "/login")
  public ModelAndView login(String error, String logout){
    final ModelAndView modelAndView = new ModelAndView();
    if (error != null){
      modelAndView.addObject("error", "Your firstName and password is invalid.");
    }
    if (logout != null){
      modelAndView.addObject("message", "You have been logged out successfully.");
    }
    modelAndView.setViewName("login");
    return modelAndView;
  }

  @GetMapping(value = "/welcome")
  public ModelAndView welcome(){
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("welcome");
    return modelAndView;
  }
}
