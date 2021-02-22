package com.tsipadan.mmsapplication.repository.api;

import com.tsipadan.mmsapplication.entity.Goods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Goods, Long> {

  List<Goods> findGoodsByItemName(String itemName);

  Goods findById(long id);

  Page<Goods> findAll(Pageable pageable);

  Page<Goods> findGoodsByItemCategory_ItemCategory(String itemCategory, Pageable pageable);

  List<Goods> findGoodsByItemCategory_ItemCategory(String itemCategory);


}
