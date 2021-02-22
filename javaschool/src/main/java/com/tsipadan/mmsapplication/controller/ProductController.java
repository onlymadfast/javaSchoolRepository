
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
