package com.tsipadan.repository;

import com.tsipadan.entity.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for ItemCategory
 */
@Repository
public interface CategoryRepository extends JpaRepository<ItemCategory, Long> {

  ItemCategory findByItemCategory(String category);

}
