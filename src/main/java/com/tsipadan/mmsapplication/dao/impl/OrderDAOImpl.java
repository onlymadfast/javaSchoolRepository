//package com.tsipadan.mmsapplication.dao.impl;
//
//import com.tsipadan.mmsapplication.dao.OrderDAO;
//import com.tsipadan.mmsapplication.dao.ProductDAO;
//import com.tsipadan.mmsapplication.entity.Order;
//import com.tsipadan.mmsapplication.entity.OrderDetail;
//import com.tsipadan.mmsapplication.entity.Product;
//import com.tsipadan.mmsapplication.model.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//import javax.persistence.NoResultException;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Query;
//import java.util.Date;
//import java.util.List;
//import java.util.UUID;
//import java.util.stream.Collectors;
//
//@Transactional
//@Repository
//public class OrderDAOImpl implements OrderDAO {
//
//  @PersistenceContext
//  private EntityManager entityManager;
//
//  @Autowired
//  private ProductDAO productDAO;
//
//  public OrderDAOImpl() {
//  }
//
//  @Override
//  public void saveOrder(CartInfo cartInfo) {
//    int random = (int) (Math.random() * 1000);
//    int orderNum = random + 1;
//    Order order = new Order();
//    order.setId(UUID.randomUUID().toString());
//    order.setOrderNum(orderNum);
//    order.setOrderDate(new Date());
//    order.setAmount(cartInfo.getAmountTotal());
//
//    CustomerInfo customerInfo = cartInfo.getCustomerInfo();
//    order.setCustomerFirstName(customerInfo.getCustomerFirstName());
//    order.setCustomerLastName(customerInfo.getCustomerLastName());
//    order.setCustomerBirthday(customerInfo.getCustomerBirthday());
//    order.setCustomerEmail(customerInfo.getCustomerEmail());
//    order.setCustomerCountry(customerInfo.getCustomerCountry());
//    order.setCustomerCity(customerInfo.getCustomerCity());
//    order.setCustomerZip(customerInfo.getCustomerZip());
//    order.setCustomerStreet(customerInfo.getCustomerStreet());
//    order.setCustomerHouse(customerInfo.getCustomerHouse());
//    order.setCustomerApartment(customerInfo.getCustomerApartment());
//    entityManager.persist(order);
//
//    List<CartLineInfo> lines = cartInfo.getCartLines();
//    for (CartLineInfo line : lines) {
//      OrderDetail detail = new OrderDetail();
//      detail.setId(UUID.randomUUID().toString());
//      detail.setAmount(line.getAmount());
//      detail.setPrice(line.getProductInfo().getPrice());
//      detail.setQuantity(line.getQuantity());
//      detail.setOrder(order);
//      String code = line.getProductInfo().getCode();
//      Product product = productDAO.findProduct(code).orElse(null);
//      detail.setProduct(product);
//      entityManager.persist(detail);
//    }
//    cartInfo.setOrderNum(orderNum);
//  }
//
//  @Override
//  public PaginationResult<OrderInfo> listOrderInfo(int page, int maxResult, int maxNavigationPage) {
//    String sql = "select new " + OrderInfo.class.getName()
//        + " (ord.id, ord.orderNum, ord.orderDate, ord.amount, " +
//        " ord.customerFirstName, ord.customerEmail, ord.customerCountry, " +
//        "ord.customerCity, ord.customerZip, ord.customerStreet, ord.customerHouse, ord.customerApartment) "
//        + " FROM " + Order.class.getName() + " ord "
//        + " order by ord.orderNum DESC ";
//
//    Query query = entityManager.createQuery(sql);
//
//    return new PaginationResult<OrderInfo>((org.hibernate.query.Query) query, page, maxResult, maxNavigationPage);
//  }
//
//  public Order findOrder(String orderId) {
//    String sql = " select i from Order i where i.id = :orderId ";
//    Query query = entityManager.createQuery(sql);
//    query.setParameter("orderId", orderId);
//    return (Order) query.getSingleResult();
//  }
//
//  @Override
//  public OrderInfo getOrderInfo(String orderId) {
//    Order order = this.findOrder(orderId);
//    if (order == null) {
//      return null;
//    }
//    return new OrderInfo(order.getId(), order.getOrderNum(), order.getOrderDate(),
//        order.getAmount(), order.getCustomerFirstName(), order.getCustomerEmail(), order.getCustomerCountry(),
//        order.getCustomerCity(), order.getCustomerZip(), order.getCustomerStreet(), order.getCustomerHouse(),
//        order.getCustomerApartment());
//  }
//
//
//  @Override
//  public List<OrderDetailInfo> listOrderDetailInfos(String orderId) {
//    String sql = " select new " + OrderDetailInfo.class.getName()
//        + " (d.id, d.product.code, d.product.name, d.quantity, d.price, d.amount) "
//        + " from " + OrderDetail.class.getName() + " d "
//        + " where d.order.id = :orderId ";
//
//    Query query = entityManager.createQuery(sql);
//    query.setParameter("orderId", orderId);
//
//    return query.getResultList();
//  }
//
//  public Order findOrderForUser(String orderNum) {
//    String sql = " select i from Order i where i.orderNum = :orderNum ";
//    Query query = entityManager.createQuery(sql);
//    query.setParameter("orderNum", Integer.parseInt(orderNum));
//    try {
//      return (Order) query.getSingleResult();
//    } catch (NoResultException ex) {
//      return null;
//    }
//  }
//
//  @Override
//  public OrderInfo getOrderInfoForUser(String orderNum) {
//    final Order order = this.findOrderForUser(orderNum);
//    if (order != null) {
//      return new OrderInfo(order.getId(), order.getOrderNum(), order.getOrderDate(),
//          order.getAmount(), order.getCustomerFirstName(), order.getCustomerEmail(), order.getCustomerCountry(),
//          order.getCustomerCity(), order.getCustomerZip(), order.getCustomerStreet(), order.getCustomerHouse(),
//          order.getCustomerApartment());
//    } else {
//      return null;
//    }
//  }
//
//  @Override
//  public List<OrderDetailInfo> listOrderDetailInfoForUser(String orderNum) {
//    String sql = " select detail from OrderDetail detail "
//        + " INNER JOIN Order o on detail.order=o "
//        + " where o.orderNum = :orderNum ";
//    Query query = entityManager.createQuery(sql);
//    query.setParameter("orderNum", Integer.parseInt(orderNum));
//    final List<OrderDetail> resultList = query.getResultList();
//    return resultList.stream()
//        .map((OrderDetail orderDetail) -> new OrderDetailInfo(orderDetail.getId(),
//            orderDetail.getProduct().getCode(),
//            orderDetail.getProduct().getName(),
//            orderDetail.getQuantity(),
//            orderDetail.getPrice(),
//            orderDetail.getAmount())).collect(Collectors.toList());
//  }
//}
