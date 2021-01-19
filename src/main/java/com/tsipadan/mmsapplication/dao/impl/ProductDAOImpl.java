package com.tsipadan.mmsapplication.dao.impl;

import com.tsipadan.mmsapplication.dao.ProductDAO;
import com.tsipadan.mmsapplication.model.PaginationResult;
import com.tsipadan.mmsapplication.entity.Product;
import com.tsipadan.mmsapplication.model.ProductInfo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class ProductDAOImpl implements ProductDAO {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public Product findProduct(String code) {
    TypedQuery<Product> query = entityManager.createQuery("SELECT code FROM Product", Product.class);
    List<Product> results = query.getResultList();
    return (Product) results;
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
      product = this.findProduct(code);
    }
    if (product == null) {
      isNew = true;
      product = new Product();
      product.setCreateDate(new Date());
    }
    product.setCode(code);
    product.setName(product.getName());
    product.setPrice(product.getPrice());
    product.setCategory(product.getCategory());
    product.setSize(product.getSize());

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
    String sql = "select new" + ProductInfo.class.getName()
        + "productInfo.code, productInfo.name, productInfo.price, productInfo.category, productInfo.size"
        + "FROM" + Product.class.getName() + "productInfo";

    if (likeName != null && likeName.length() > 0) {
      sql += "where lower (productInfo.name) like :likeName";
    }
    sql += "order by productInfo.createDate desc";

    Query query = entityManager.createQuery(sql);
    if (likeName != null && likeName.length() > 0) {
      query.setParameter("likeName", "%" + likeName.toLowerCase() + "%");
    }
    return new PaginationResult<ProductInfo>((org.hibernate.query.Query) query,page,maxResult,maxNavigationPage);
  }

  @Override
  public PaginationResult<ProductInfo> queryProducts(int page, int maxResult, int maxNavigationPage) {
    return queryProducts(page, maxResult, maxNavigationPage, null);
  }

}
