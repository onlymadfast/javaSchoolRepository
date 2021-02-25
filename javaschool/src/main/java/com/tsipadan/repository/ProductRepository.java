package com.tsipadan.repository;

import com.tsipadan.entity.Goods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for Goods
 */
@Repository
public interface ProductRepository extends JpaRepository<Goods, Long> {

  Page<Goods> findAll(Pageable pageable);

  List<Goods> findGoodsByItemCategory_ItemCategory(String itemCategory);

  List<Goods> findByItemNameContains(String keyword);

  List<Goods> findTop5ByOrderByItemPriceDesc();

}
