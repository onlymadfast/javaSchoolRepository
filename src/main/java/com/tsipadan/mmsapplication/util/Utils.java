package com.tsipadan.mmsapplication.util;

import javax.servlet.http.HttpServletRequest;
import com.tsipadan.mmsapplication.model.CartInfo;

public class Utils {

  public static CartInfo getCartInSession(HttpServletRequest httpServletRequest){

    CartInfo cartInfo = (CartInfo) httpServletRequest.getSession().getAttribute("myCart");

    if (cartInfo == null) {
      cartInfo = new CartInfo();
      httpServletRequest.getSession().setAttribute("myCart", cartInfo);
    }
    return cartInfo;
  }

  public static void removeCartInSession(HttpServletRequest httpServletRequest) {
    httpServletRequest.getSession().removeAttribute("myCart");
  }

  public static void storeLastOrderedCartInSession(HttpServletRequest httpServletRequest, CartInfo cartInfo) {
    httpServletRequest.getSession().setAttribute("lastOrderedCart", cartInfo);
  }

  public static CartInfo getLastOrderedCartInSession(HttpServletRequest httpServletRequest) {
    return (CartInfo) httpServletRequest.getSession().getAttribute("lastOrderedCart");
  }

}
