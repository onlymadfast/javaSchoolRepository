//package com.tsipadan.mmsapplication.service;
//
//import com.tsipadan.mmsapplication.dao.ProductDAO;
//import com.tsipadan.mmsapplication.dto.ProductDTO;
//import com.tsipadan.mmsapplication.entity.Product;
//import com.tsipadan.mmsapplication.exception.ResourceCreationException;
//import com.tsipadan.mmsapplication.exception.ResourceUpdateException;
//import lombok.RequiredArgsConstructor;
//import org.springframework.lang.NonNull;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.io.IOException;
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class ProductService {
//  private final ProductDAO productDAO;
//  private final Mapper<ProductDTO, Product> toProductMapper;
//  private final Mapper<Product, ProductDTO> toProductDTOMapper;
//
//  @Transactional
//  public ProductDTO createProduct(@NonNull ProductDTO dto) {
//    final Optional<Product> existingProductWithSuchCode = productDAO.findProduct(dto.getCode());
//
//    if (existingProductWithSuchCode.isPresent()) {
//      throw new ResourceCreationException("Resource with code <" + dto.getCode() + "> already exists.");
//    }
//
//    return this
//        .toProductMapper
//        .andThen(productDAO::save)
//        .andThen(toProductDTOMapper)
//        .apply(dto);
//  }
//
//  @Transactional
//  public ProductDTO updateProduct(@NonNull String code, @NonNull ProductDTO dto) {
//    return productDAO
//        .findProduct(code)
//        .map(existingProduct -> {
//          existingProduct.setCode(dto.getCode());
//          existingProduct.setCategory(dto.getCategory());
//          existingProduct.setName(dto.getName());
//          existingProduct.setPrice(dto.getPrice());
//          existingProduct.setSize(dto.getSize());
//          try {
//            existingProduct.setImage(dto.getImage().getBytes());
//          } catch (IOException e) {
//            e.printStackTrace();
//          }
//          return existingProduct;
//        })
//        .map(productDAO::save)
//        .map(toProductDTOMapper)
//        .orElseThrow(() -> new ResourceUpdateException("Product with code: " + code + " does not exist"));
//  }
//
//}
