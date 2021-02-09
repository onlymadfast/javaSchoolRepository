//package com.tsipadan.mmsapplication.controller;
//
//import com.tsipadan.mmsapplication.dto.ProductDTO;
//import com.tsipadan.mmsapplication.exception.ResourceCreationException;
//import com.tsipadan.mmsapplication.exception.ResourceUpdateException;
//import com.tsipadan.mmsapplication.service.ProductService;
//import com.tsipadan.mmsapplication.validator.ProductInfoValidator;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.WebDataBinder;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.util.Collections;
//
//@Slf4j
//@Controller
//@RequiredArgsConstructor
//public class ProductController {
//  private final ProductService productService;
//  private final ProductInfoValidator productInfoValidator;
//  private final String productViewName = "product";
//
//  @ExceptionHandler(ResourceCreationException.class)
//  public String handleProductCreationException(ResourceCreationException ex, Model model) {
//    log.error("Product was not created.", ex);
//    model.addAttribute("message", ex.getMessage());
//    model.addAttribute("productForm", new ProductDTO());
//    return productViewName;
//  }
//
//  @ExceptionHandler(ResourceUpdateException.class)
//  public ModelAndView handleProductUpdateException(ResourceUpdateException ex) {
//    log.error("Product was not updated.", ex);
//    return new ModelAndView(productViewName, Collections.singletonMap("message", ex.getMessage()));
//  }
//
//  @InitBinder("productInfoValidator")
//  public void myInitBinder(WebDataBinder dataBinder) {
//    dataBinder.setValidator(productInfoValidator);
//    dataBinder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
//  }
//
//  @GetMapping(value = "/product")
//  public String openCreationPage(Model model) {
//    model.addAttribute("productForm", new ProductDTO());
//    model.addAttribute("message", null);
//    return "product";
//  }
//
//  @PostMapping(value = "/product")
//  public String createNewProduct(@ModelAttribute("productForm") @Validated ProductDTO productDTO,
//                                 BindingResult bindingResult) {
//    if (bindingResult.hasErrors()) {
//      return productViewName;
//    }
//    try {
//      productService.createProduct(productDTO);
//    } catch (Exception e) {
//      throw new ResourceCreationException("Unexpected exception during creation of new product.", e);
//    }
//    return "redirect:/productList";
//  }
//
//  @GetMapping(value = "/productEdit")
//  public String openEditPage(Model model) {
//    model.addAttribute("productForm", new ProductDTO());
//    model.addAttribute("message", null);
//    return "productEdit";
//  }
//
//  @PostMapping(value = "/productEdit")
//  public String updateProduct(@ModelAttribute("productForm") @Validated ProductDTO productDTO,
//      BindingResult bindingResult) {
//
//    if (bindingResult.hasErrors()) {
//      return productViewName;
//    }
//    productService.updateProduct(productDTO.getCode(), productDTO);
//    return "redirect:/productList";
//  }
//
//}
//
