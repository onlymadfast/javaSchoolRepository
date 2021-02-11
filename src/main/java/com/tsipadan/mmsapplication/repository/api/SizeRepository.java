package com.tsipadan.mmsapplication.repository.api;

import com.tsipadan.mmsapplication.entity.SizeTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRepository extends JpaRepository<SizeTable,Long> {

}
