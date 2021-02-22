package com.tsipadan.mmsapplication.mapper;

import com.tsipadan.dto.ItemCategoryDTO;
import com.tsipadan.mmsapplication.entity.ItemCategory;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ItemCategoryMapper {

  private final ModelMapper modelMapper;

  public ItemCategory toEntity(ItemCategoryDTO dto) {
    return Objects.isNull(dto) ? null : modelMapper.map(dto, ItemCategory.class);
  }

  public ItemCategoryDTO toDto(ItemCategory entity) {
    return Objects.isNull(entity) ? null : modelMapper.map(entity, ItemCategoryDTO.class);
  }

}
