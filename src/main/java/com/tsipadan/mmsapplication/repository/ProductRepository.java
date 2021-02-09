package com.tsipadan.mmsapplication.repository;

import com.tsipadan.mmsapplication.dto.GoodsDTO;
import com.tsipadan.mmsapplication.entity.Goods;
import com.tsipadan.mmsapplication.entity.ItemCategory;
import com.tsipadan.mmsapplication.entity.SizeTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Goods,Long> {

  List<Goods> findGoodsByItemName(String itemName);

}
