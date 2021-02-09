//package com.tsipadan.mmsapplication.dao.impl;
//
//import com.tsipadan.mmsapplication.dao.ProductDAO;
//import com.tsipadan.mmsapplication.entity.Product;
//import com.tsipadan.mmsapplication.model.PaginationResult;
//import com.tsipadan.mmsapplication.model.ProductInfo;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//import javax.persistence.NoResultException;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Query;
//import java.util.Date;
//import java.util.Optional;
//
//
//@Repository
//@Transactional
//public class ProductDAOImpl implements ProductDAO {
//
//  @PersistenceContext
//  private EntityManager entityManager;
//
//  @Override
//  public Optional<Product> findProduct(String code) {
//
//    String sql = " SELECT c from Product c WHERE c.code = :code ";
//    Query query = entityManager.createQuery(sql, Product.class);
//    query.setParameter("code", code);
//    try {
//      return Optional.ofNullable((Product) query.getSingleResult());
//    } catch (NoResultException ex) {
//      return Optional.empty();
//    }
//  }
//
//  @Override
//  public Product save(Product product) {
//    product.setCreateDate(new Date());
//    entityManager.persist(product);
//    entityManager.flush();
//    return product;
//  }
//
//  @Override
//  public PaginationResult<ProductInfo> queryProducts(int page, int maxResult, int maxNavigationPage, String likeName) {
//    String sql = "select new " + ProductInfo.class.getName()
//        + " (p.code, p.name, p.price, p.category, p.size) "
//        + " FROM " + Product.class.getName() + " p ";
//
//    if (likeName != null && likeName.length() > 0) {
//      sql += " WHERE LOWER (p.name) like :likeName ";
//    }
//    sql += "order by p.createDate desc ";
//
//    Query query = entityManager.createQuery(sql);
//
//    if (likeName != null && likeName.length() > 0) {
//      query.setParameter("likeName", "%" + likeName.toLowerCase() + "%");
//    }
//    return new PaginationResult<ProductInfo>((org.hibernate.query.Query<ProductInfo>) query,
//        page, maxResult, maxNavigationPage);
//  }
//
//  @Override
//  public PaginationResult<ProductInfo> queryProducts(int page, int maxResult, int maxNavigationPage) {
//    return queryProducts(page, maxResult, maxNavigationPage, null);
//  }
//
//  @Override
//  public PaginationResult<ProductInfo> filterOne(int page, int maxResult, int maxNavigationPage) {
//    String sql = " select new " + ProductInfo.class.getName()
//        + " (p.code, p.name, p.price, p.category, p.size) "
//        + " from " + Product.class.getName() + " p "
//        + " where p.category like 'SuperCategory1' ";
//
//    Query query = entityManager.createQuery(sql);
//
//    return new PaginationResult<ProductInfo>((org.hibernate.query.Query<ProductInfo>) query,
//        page, maxResult, maxNavigationPage);
//  }
//
//  @Override
//  public PaginationResult<ProductInfo> filterTwo(int page, int maxResult, int maxNavigationPage) {
//    String sql = " select new " + ProductInfo.class.getName()
//        + " (p.code, p.name, p.price, p.category, p.size) "
//        + " from " + Product.class.getName() + " p "
//        + " where p.category like 'SuperCategory2' ";
//
//    Query query = entityManager.createQuery(sql);
//
//    return new PaginationResult<ProductInfo>((org.hibernate.query.Query<ProductInfo>) query,
//        page, maxResult, maxNavigationPage);
//  }
//
//}
