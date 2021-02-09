package com.tsipadan.mmsapplication.repository;

import com.tsipadan.mmsapplication.entity.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<ItemCategory, Long> {

  List<ItemCategory> findItemCategoriesByName(String itemCategory);

}
