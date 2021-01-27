package com.tsipadan.mmsapplication.dao.impl;

import com.tsipadan.mmsapplication.dao.OrderDAO;
import com.tsipadan.mmsapplication.dao.ProductDAO;
import com.tsipadan.mmsapplication.entity.Account;
import com.tsipadan.mmsapplication.entity.Order;
import com.tsipadan.mmsapplication.entity.OrderDetail;
import com.tsipadan.mmsapplication.entity.Product;
import com.tsipadan.mmsapplication.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Transactional
@Repository
public class OrderDAOImpl implements OrderDAO {

  @PersistenceContext
  private EntityManager entityManager;

  @Autowired
  private ProductDAO productDAO;

  public OrderDAOImpl() {}

  @Override
  public void saveOrder(CartInfo cartInfo) {
    int random = (int) (Math.random()*1000);
    int orderNum = random + 1;
    Order order = new Order();
    order.setId(UUID.randomUUID().toString());
    order.setOrderNum(orderNum);
    order.setOrderDate(new Date());
    order.setAmount(cartInfo.getAmountTotal());

    CustomerInfo customerInfo = cartInfo.getCustomerInfo();
    order.setCustomerFirstName(customerInfo.getCustomerFirstName());
    order.setCustomerLastName(customerInfo.getCustomerLastName());
    order.setCustomerBirthday(customerInfo.getCustomerBirthday());
    order.setCustomerEmail(customerInfo.getCustomerEmail());
    order.setCustomerCountry(customerInfo.getCustomerCountry());
    order.setCustomerCity(customerInfo.getCustomerCity());
    order.setCustomerZip(customerInfo.getCustomerZip());
    order.setCustomerStreet(customerInfo.getCustomerStreet());
    order.setCustomerHouse(customerInfo.getCustomerHouse());
    order.setCustomerApartment(customerInfo.getCustomerApartment());
    entityManager.persist(order);

    List<CartLineInfo> lines = cartInfo.getCartLines();
    for (CartLineInfo line : lines) {
      OrderDetail detail = new OrderDetail();
      detail.setId(UUID.randomUUID().toString());
      detail.setAmount(line.getAmount());
      detail.setPrice(line.getProductInfo().getPrice());
      detail.setQuantity(line.getQuantity());
      detail.setOrder(order);
      String code = line.getProductInfo().getCode();
      Product product = productDAO.findProduct(code);
      detail.setProduct(product);
      entityManager.persist(detail);
    }
    cartInfo.setOrderNum(orderNum);
  }

  @Override
  public PaginationResult<OrderInfo> listOrderInfo(int page, int maxResult, int maxNavigationPage) {
    String sql = "select new " + OrderInfo.class.getName()
        + " (ord.id, ord.orderNum, ord.orderDate, ord.amount, " +
        " ord.customerFirstName, ord.customerEmail, ord.customerCountry, " +
        "ord.customerCity, ord.customerZip, ord.customerStreet, ord.customerHouse, ord.customerApartment) "
        + " FROM " + Order.class.getName() + " ord "
        + " order by ord.orderNum DESC ";

    Query query = entityManager.createQuery(sql);

    return new PaginationResult<OrderInfo>((org.hibernate.query.Query) query, page, maxResult, maxNavigationPage);
  }

  public Order findSpecificOrder(String orderNum) {
    String sql = " select n from Order n where n.orderNum = :orderNum ";
    Query query = entityManager.createQuery(sql);
    query.setParameter("orderNum", orderNum);
    return (Order) query.getSingleResult();
  }

  @Override
  public OrderInfo getSpecificOrderInfo(String orderNum) {
    Order order = this.findSpecificOrder(orderNum);
    return new OrderInfo(order.getId(), order.getOrderNum(), order.getOrderDate(),
        order.getAmount(), order.getCustomerFirstName(), order.getCustomerEmail(),
        order.getCustomerCountry(), order.getCustomerCity(), order.getCustomerZip(),
        order.getCustomerStreet(), order.getCustomerHouse(), order.getCustomerApartment());
  }

  public Order findOrder(String orderId) {
    String sql = " select i from Order i where i.id = :orderId ";
    Query query = entityManager.createQuery(sql);
    query.setParameter("orderId", orderId);
    return (Order) query.getSingleResult();
  }

  @Override
  public OrderInfo getOrderInfo(String orderId) {
    Order order = this.findOrder(orderId);
    if (order == null) {
      return null;
    }
    return new OrderInfo(order.getId(), order.getOrderNum(), order.getOrderDate(),
        order.getAmount(), order.getCustomerFirstName(), order.getCustomerEmail(), order.getCustomerCountry(),
        order.getCustomerCity(), order.getCustomerZip(), order.getCustomerStreet(), order.getCustomerHouse(), order.getCustomerApartment());
  }

  @Override
  public List<OrderDetailInfo> listOrderDetailInfos(String orderId) {
    String sql = " select new " + OrderDetailInfo.class.getName()
        + " (d.id, d.product.code, d.product.name, d.quantity, d.price, d.amount) "
        + " from " + OrderDetail.class.getName() + " d "
        + " where d.order.id = :orderId ";

    Query query = entityManager.createQuery(sql);
    query.setParameter("orderId", orderId);

    return query.getResultList();
  }

  @Override
  public List<Order> search(String keyword) {
    String sql = " SELECT f from Order f where cast(f.orderNum as CHAR) like :keyword% ";
    Query query = entityManager.createQuery(sql);
    query.setParameter("keyword",keyword);
    return query.getResultList();
  }

}
