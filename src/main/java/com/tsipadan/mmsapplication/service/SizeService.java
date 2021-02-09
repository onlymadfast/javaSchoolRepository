package com.tsipadan.mmsapplication.service;

import com.tsipadan.mmsapplication.entity.SizeTable;
import com.tsipadan.mmsapplication.repository.SizeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SizeService {

  @Autowired
  private SizeRepository sizeRepository;

  @Transactional
  public List<SizeTable> all(){
    return sizeRepository.findAll();
  }




  public SizeTable findSizeById(long id){
    return sizeRepository.getOne(id);
  }




}
