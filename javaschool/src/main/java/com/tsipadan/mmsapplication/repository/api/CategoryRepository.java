package com.tsipadan.mmsapplication.repository.api;

import com.tsipadan.mmsapplication.entity.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<ItemCategory, Long> {

  ItemCategory findByItemCategory(String category);

}
