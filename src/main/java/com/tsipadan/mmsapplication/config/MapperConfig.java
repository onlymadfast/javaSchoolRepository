//package com.tsipadan.mmsapplication.config;
//
//import com.tsipadan.mmsapplication.dto.ProductDTO;
//import com.tsipadan.mmsapplication.entity.Product;
//import com.tsipadan.mmsapplication.exception.DefaultException;
//import com.tsipadan.mmsapplication.service.Mapper;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.ByteArrayInputStream;
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//import java.nio.channels.IllegalSelectorException;
//
//@Configuration
//public class MapperConfig {
//
//  @Bean
//  Mapper<Product, ProductDTO> toProductDTOMapper() {
//    return product -> {
//      final MultipartFile file;
//      if (product.getImage() != null) {
//        file = this.toFile(product.getImage());
//      } else {
//        file = null;
//      }
//
//      final ProductDTO dto = new ProductDTO();
//      dto.setCategory(product.getCategory());
//      dto.setCode(product.getCode());
//      dto.setImage(file);
//      dto.setName(product.getName());
//      dto.setPrice(product.getPrice());
//      dto.setSize(product.getSize());
//      return dto;
//    };
//  }
//
//  @Bean
//  Mapper<ProductDTO, Product> toProductMapper() {
//    return dto -> {
//      final byte[] bytes;
//      try {
//        if (dto.getImage() != null) {
//          bytes = dto.getImage().getBytes();
//        } else {
//          bytes = null;
//        }
//      } catch (IOException ex) {
//        throw new DefaultException("Cannot read bytes from multipart", ex);
//      }
//
//      final Product product = new Product();
//      product.setCategory(dto.getCategory());
//      product.setCode(dto.getCode());
//      product.setImage(bytes);
//      product.setName(dto.getName());
//      product.setPrice(dto.getPrice());
//      product.setSize(dto.getSize());
//      return product;
//    };
//  }
//
//  private MultipartFile toFile(byte[] bytes) {
//    return new MultipartFile() {
//      @Override
//      public String getName() {
//        return "image";
//      }
//
//      @Override
//      public String getOriginalFilename() {
//        return "image";
//      }
//
//      @Override
//      public String getContentType() {
//        return null;
//      }
//
//      @Override
//      public boolean isEmpty() {
//        return false;
//      }
//
//      @Override
//      public long getSize() {
//        return 0;
//      }
//
//      @Override
//      public byte[] getBytes() {
//        return bytes;
//      }
//
//      @Override
//      public InputStream getInputStream() {
//        return new ByteArrayInputStream(bytes);
//      }
//
//      @Override
//      public void transferTo(File file) throws IllegalStateException {
//        throw new IllegalSelectorException();
//      }
//    };
//  }
//}
