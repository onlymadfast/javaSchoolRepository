
//  @RequestMapping(value = "/buyProduct")
//  public String listProductHandler(HttpServletRequest request, @RequestParam(value = "code") String code) {
//
//    Product product = null;
//    if (code != null && code.length() > 0) {
//      product = productDAO.findProduct(code).orElse(null);
//    }
//    if (product != null) {
//      CartInfo cartInfo = Utils.getCartInSession(request);
//      ProductInfo productInfo = new ProductInfo(product);
//      cartInfo.addProduct(productInfo, 1);
//    }
//    return "redirect:/shoppingCart";
//  }
//
//  @GetMapping(value = "/shoppingCart")
//  public String shoppingCartHandler(HttpServletRequest request, Model model) {
//    CartInfo myCart = Utils.getCartInSession(request);
//    model.addAttribute("cartForm", myCart);
//    return "shoppingCart";
//  }
//
//  @PostMapping(value = "/shoppingCart")
//  public String shoppingCartUpdateQuantityHandler(HttpServletRequest request,
//                                                  @ModelAttribute("cartForm") CartInfo cartForm) {
//    CartInfo cartInfo = Utils.getCartInSession(request);
//    cartInfo.updateQuantity(cartForm);
//    return "redirect:/shoppingCart";
//  }
//
//  @GetMapping(value = "/shoppingCartRemoveProduct")
//  public String removeProductHandler(HttpServletRequest request,
//                                     @RequestParam(value = "code", defaultValue = "") String code) {
//    Product product = null;
//    if (code != null && code.length() > 0) {
//      product = productDAO.findProduct(code).orElse(null);
//    }
//    if (product != null) {
//      CartInfo cartInfo = Utils.getCartInSession(request);
//      ProductInfo productInfo = new ProductInfo(product);
//      cartInfo.removeProduct(productInfo);
//    }
//    return "redirect:/shoppingCart";
//  }
//
//  @GetMapping(value = "/shoppingCartCustomer")
//  public String shoppingCartCustomerForm(HttpServletRequest request, Model model) {
//    CartInfo cartInfo = Utils.getCartInSession(request);
//    if (cartInfo.isEmpty()) {
//      return "redirect:/shoppingCart";
//    }
//    CustomerInfo customerInfo = cartInfo.getCustomerInfo();
//    if (customerInfo == null) {
//      customerInfo = new CustomerInfo();
//    }
//    model.addAttribute("customerForm", customerInfo);
//    return "shoppingCartCustomer";
//  }
//
//  @PostMapping(value = "/shoppingCartCustomer")
//  public String shoppingCartCustomerSave(HttpServletRequest request,
//                                         @ModelAttribute("customerForm")
//                                         @Validated CustomerInfo customerForm,
//                                         BindingResult bindingresult) {
//    if (bindingresult.hasErrors()) {
//      customerForm.setValid(false);
//      return "shoppingCartCustomer";
//    }
//    customerForm.setValid(true);
//    CartInfo cartInfo = Utils.getCartInSession(request);
//    cartInfo.setCustomerInfo(customerForm);
//    return "redirect:/shoppingCartConfirmation";
//  }

//  @GetMapping(value = "/shoppingCartConfirmation")
//  public String shoppingCartConfirmationReview(HttpServletRequest request, Model model) {
//    CartInfo cartInfo = Utils.getCartInSession(request);
//    if (cartInfo.isEmpty()) {
//      return "redirect:/shoppingCart";
//    } else if (!cartInfo.isValidCustomer()) {
//      return "redirect:/shoppingCartCustomer";
//    }
//    model.addAttribute("myCart", cartInfo);
//    return "shoppingCartConfirmation";
//  }
//
//  @PostMapping(value = "/shoppingCartConfirmation")
//  @Transactional(propagation = Propagation.NEVER)
//  public String shoppingCartConfirmationSend(HttpServletRequest request) {
//    CartInfo cartInfo = Utils.getCartInSession(request);
//    if (cartInfo.isEmpty()) {
//      return "redirect:/shoppingCart";
//    } else if (!cartInfo.isValidCustomer()) {
//      return "redirect:/shoppingCartCustomer";
//    }
//
//    try {
//      orderDAO.saveOrder(cartInfo);
//    } catch (Exception e) {
//      return "shoppingCartConfirmation";
//    }
//
//    Utils.removeCartInSession(request);
//    Utils.storeLastOrderedCartInSession(request, cartInfo);
//    return "redirect:/shoppingCartFinalize";
//  }
//
//  @GetMapping(value = "/shoppingCartFinalize")
//  public String shoppingCartFinalize(HttpServletRequest request) {
//    CartInfo lastOrderedCart = Utils.getLastOrderedCartInSession(request);
//    if (lastOrderedCart == null) {
//      return "redirect:/shoppingCart";
//    }
//    return "shoppingCartFinalize";
//  }

