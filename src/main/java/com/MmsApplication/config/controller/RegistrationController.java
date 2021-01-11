package com.MmsApplication.config.controller;

import com.MmsApplication.config.dao.ClientsDAOImpl;
import com.MmsApplication.config.model.Client;
import com.MmsApplication.config.model.ClientAddress;
import com.MmsApplication.config.service.ClientService;
import com.MmsApplication.config.validator.ClientValidator;
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
  private final ClientValidator clientValidator;
  private final ClientsDAOImpl clientsDAO;


  @GetMapping(value = "/registration")
  public ModelAndView view_registration() {
    final ModelAndView modelAndView = new ModelAndView();
    modelAndView.addObject("registration_form", new Client());
    modelAndView.setViewName("registration_page");
    return modelAndView;
  }

  @PostMapping(value = "/registration")
  public ModelAndView addClient(@ModelAttribute("registration_form")
                                    Client registration_form, ClientAddress clientAddress, BindingResult bindingResult){
    final ModelAndView modelAndView = new ModelAndView();
    clientValidator.validate(registration_form, bindingResult);
    if (bindingResult.hasErrors()){
      return modelAndView;
    }

//    clientService.saveClientAndAddress(registration_form,clientAddress);
//    ClientRegistrationDto  clientRegistrationDto = clientsDAO.saveClientAndAddress(registration_form, clientAddress);
//    Client client = clientRegistrationDto._toConvertClientEntity();

    clientService.saveClientAndAddressByDto(registration_form, clientAddress);

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
    modelAndView.setViewName("redirect:/login");
    return modelAndView;
  }

  @GetMapping(value = "/welcome")
  public ModelAndView welcome(){
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("welcome");
    return modelAndView;
  }
}
