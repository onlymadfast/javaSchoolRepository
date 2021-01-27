package com.tsipadan.mmsapplication.dao.impl;

import com.tsipadan.mmsapplication.dao.ProductDAO;
import com.tsipadan.mmsapplication.model.PaginationResult;
import com.tsipadan.mmsapplication.entity.Product;
import com.tsipadan.mmsapplication.model.ProductInfo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

import java.util.Date;


@Repository
@Transactional
public class ProductDAOImpl implements ProductDAO {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public Product findProduct(String code) {

    String sql = " SELECT c from Product c WHERE c.code = :code ";
    Query query = entityManager.createQuery(sql, Product.class);
    query.setParameter("code", code);
    return (Product) query.getSingleResult();

  }

  @Override
  public ProductInfo findProductInfo(String code) {
    Product product = this.findProduct(code);
    if (product == null) {
      return null;
    }
    return new ProductInfo(product.getCode(), product.getName(), product.getPrice(),
        product.getCategory(),product.getSize());
  }

  @Override
  public void save(ProductInfo productInfo) {

    String code = productInfo.getCode();

    Product product = null;
    boolean isNew = false;

    if (code != null) {
      product = this.findProduct(productInfo.getCode());
    }
    if (product == null) {
      isNew = true;
      product = new Product();
      product.setCreateDate(new Date());
    }
    product.setCode(code);
    product.setName(productInfo.getName());
    product.setPrice(productInfo.getPrice());
    product.setCategory(productInfo.getCategory());
    product.setSize(productInfo.getSize());

    if (productInfo.getFileData() != null) {
      byte[] image = productInfo.getFileData().getBytes();
      if (image != null && image.length > 0) {
        product.setImage(image);
      }
    }
    if (isNew) {
      this.entityManager.persist(product);
    }
    this.entityManager.flush();
  }

  @Override
  public PaginationResult<ProductInfo> queryProducts(int page, int maxResult, int maxNavigationPage, String likeName) {
    String sql = "select new " + ProductInfo.class.getName()
        + " (p.code, p.name, p.price, p.category, p.size) "
        + " FROM " + Product.class.getName() + " p ";

    if (likeName != null && likeName.length() > 0) {
      sql += " WHERE LOWER (p.name) like :likeName ";
    }
    sql += "order by p.createDate desc ";

    Query query = entityManager.createQuery(sql);

    if (likeName != null && likeName.length() > 0) {
      query.setParameter("likeName", "%" + likeName.toLowerCase() + "%");
    }
    return new PaginationResult<ProductInfo>((org.hibernate.query.Query<ProductInfo>) query,
        page,maxResult,maxNavigationPage);
  }

  @Override
  public PaginationResult<ProductInfo> queryProducts(int page, int maxResult, int maxNavigationPage) {
    return queryProducts(page, maxResult, maxNavigationPage, null);
  }

  @Override
  public PaginationResult<ProductInfo> filterOne (int page, int maxResult, int maxNavigationPage){
    String sql = " select new " + ProductInfo.class.getName()
        + " (p.code, p.name, p.price, p.category, p.size) "
        + " from " + Product.class.getName() + " p "
        + " where p.category like 'SuperCategory1' ";

    Query query = entityManager.createQuery(sql);

    return new PaginationResult<ProductInfo>((org.hibernate.query.Query<ProductInfo>) query,
        page,maxResult,maxNavigationPage);
  }

  @Override
  public PaginationResult<ProductInfo> filterTwo (int page, int maxResult, int maxNavigationPage){
    String sql = " select new " + ProductInfo.class.getName()
        + " (p.code, p.name, p.price, p.category, p.size) "
        + " from " + Product.class.getName() + " p "
        + " where p.category like 'SuperCategory2' ";

    Query query = entityManager.createQuery(sql);

    return new PaginationResult<ProductInfo>((org.hibernate.query.Query<ProductInfo>) query,
        page,maxResult,maxNavigationPage);
  }

}
