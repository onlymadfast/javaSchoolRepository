package com.tsipadan.mmsapplication.util;

import com.tsipadan.dto.UserOrderDTO;

import javax.servlet.http.HttpServletRequest;

public class Utils {

  public static UserOrderDTO getCartInSession(HttpServletRequest httpServletRequest) {

    UserOrderDTO userOrderDTO = (UserOrderDTO) httpServletRequest.getSession().getAttribute("myCart");

    if (userOrderDTO == null) {
      userOrderDTO = new UserOrderDTO();
      httpServletRequest.getSession().setAttribute("myCart", userOrderDTO);
    }
    return userOrderDTO;
  }

  public static void removeCartInSession(HttpServletRequest httpServletRequest) {
    httpServletRequest.getSession().removeAttribute("myCart");
  }

  public static void storeLastOrderedCartInSession(HttpServletRequest httpServletRequest, UserOrderDTO userOrderDTO) {
    httpServletRequest.getSession().setAttribute("lastOrderedCart", userOrderDTO);
  }

  public static UserOrderDTO getLastOrderedCartInSession(HttpServletRequest httpServletRequest) {
    return (UserOrderDTO) httpServletRequest.getSession().getAttribute("lastOrderedCart");
  }

}
