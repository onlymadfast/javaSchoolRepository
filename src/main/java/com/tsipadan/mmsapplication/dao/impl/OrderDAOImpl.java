package com.tsipadan.mmsapplication.dao.impl;

import com.tsipadan.mmsapplication.dao.OrderDAO;
import com.tsipadan.mmsapplication.dao.ProductDAO;
import com.tsipadan.mmsapplication.entity.Order;
import com.tsipadan.mmsapplication.entity.OrderDetail;
import com.tsipadan.mmsapplication.entity.Product;
import com.tsipadan.mmsapplication.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public class OrderDAOImpl implements OrderDAO {

  @PersistenceContext
  private EntityManager entityManager;

  private ProductDAO productDAO;

  public OrderDAOImpl() {}

  private int getMaxOrderNum() {
    String sql = "SELECT max(o.orderNum)from" + Order.class.getName() + "o";
    Query query = entityManager.createQuery(sql);
    Integer value = query.getMaxResults();
    if (value == null) {
      return 0;
    }
    return value;
  }

  @Override
  public void saveOrder(CartInfo cartInfo) {

    int orderNum = this.getMaxOrderNum() + 1;
    Order order = new Order();

    order.setID(UUID.randomUUID().toString());
    order.setOrderNum(orderNum);
    order.setOrderDate(new Date());
    order.setAmount(cartInfo.getAmountTotal());

    CustomerInfo customerInfo = cartInfo.getCustomerInfo();
    order.setFirstName(customerInfo.getFirstName());
    order.setLastName(customerInfo.getLastName());
    order.setBirthday(customerInfo.getBirthday());
    order.setEmail(customerInfo.getEmail());
    order.setCountry(customerInfo.getCountry());
    order.setCity(customerInfo.getCity());
    order.setStreet(customerInfo.getStreet());
    order.setHouse(customerInfo.getHouse());
    order.setApartment(customerInfo.getApartment());

    entityManager.persist(order);

    List<CartLineInfo> lines = cartInfo.getCartLines();

    for (CartLineInfo line : lines) {
      OrderDetail detail = new OrderDetail();
      detail.setId(UUID.randomUUID().toString());
      detail.setOrder(order);
      detail.setAmount(line.getAmount());
      detail.setPrice(line.getProductInfo().getPrice());
      detail.setQuantity(line.getQuantity());

      String code = line.getProductInfo().getCode();
      Product product = this.productDAO.findProduct(code);
      detail.setProduct(product);

      entityManager.persist(detail);

    }
    cartInfo.setOrderNum(orderNum);
  }

  @Override
  public PaginationResult<OrderInfo> listOrderInfo(int page, int maxResult, int maxNavigationPage) {
    String sql = "SELECT new " + OrderInfo.class.getName()
        + "(ord.id, ord.orderDate, ord.orderNum, ord.amount, "
        + "ord.customerName, ord.customerAddress, ord.customerEmail, ord.customerPhone) " + " from "
        + Order.class.getName() + " ord "//
        + "order by ord.orderNum desc";

    Query query = entityManager.createQuery(sql);

    return new PaginationResult<>((org.hibernate.query.Query) query, page, maxResult, maxNavigationPage);
  }

  public Order findOrder(String ID) {
    return entityManager
        .createQuery("SELECT ID FROM Order", Order.class)
        .getSingleResult();
  }

  @Override
  public OrderInfo getOrderInfo(String orderId) {
    Order order = this.findOrder(orderId);
    if (order == null) {
      return null;
    }
    return new OrderInfo(order.getID(), order.getOrderNum(), order.getOrderDate(),
        order.getAmount(), order.getFirstName(), order.getEmail(), order.getCountry(),
        order.getCity(), order.getZip(), order.getStreet(), order.getHouse(), order.getApartment());
  }

  @Override
  public List<OrderDetailInfo> listOrderDetailInfos(String orderId) {
    String sql = "SELECT new" + OrderDetailInfo.class.getName()
        + "(d.id, d.product.code, d.product.name , d.quantity, d.price, d.amount)"
        + "from" + OrderDetail.class.getName() + "d"
        + "where d.order.id = :orderId";

    Query query = entityManager.createQuery(sql);
    query.setParameter("orderId", orderId);

    return query.getResultList();
  }

}
