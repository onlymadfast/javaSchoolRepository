//package com.tsipadan.mmsapplication.dao;
//
//import com.tsipadan.mmsapplication.entity.Product;
//import com.tsipadan.mmsapplication.model.PaginationResult;
//import com.tsipadan.mmsapplication.model.ProductInfo;
//
//import java.util.Optional;
//
//public interface ProductDAO {
//
//  Optional<Product> findProduct(String code);
//
//  PaginationResult<ProductInfo> queryProducts(int page, int maxResult, int maxNavigationPage);
//
//  PaginationResult<ProductInfo> queryProducts(int page, int maxResult, int maxNavigationPage, String likeName);
//
//  PaginationResult<ProductInfo> filterOne (int page, int maxResult, int maxNavigationPage);
//
//  PaginationResult<ProductInfo> filterTwo (int page, int maxResult, int maxNavigationPage);
//
//  Product save(Product product);
//
//}
