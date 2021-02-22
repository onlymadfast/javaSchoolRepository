package com.tsipadan.mmsapplication.mapper;

import com.tsipadan.dto.GoodsDTO;
import com.tsipadan.mmsapplication.entity.Goods;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ProductMapper {

  private final ModelMapper modelMapper;

  public Goods toEntity(GoodsDTO dto) {
    return Objects.isNull(dto) ? null : modelMapper.map(dto, Goods.class);
  }

  public GoodsDTO toDto(Goods entity) {
    return Objects.isNull(entity) ? null : modelMapper.map(entity, GoodsDTO.class);
  }

}
