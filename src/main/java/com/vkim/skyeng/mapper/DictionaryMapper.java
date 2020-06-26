package com.vkim.skyeng.mapper;

import static java.util.Objects.isNull;

import com.vkim.skyeng.dto.DictionaryDto;
import com.vkim.skyeng.entity.DictionaryEntity;
import org.springframework.stereotype.Component;

@Component
public class DictionaryMapper implements BeanMapper<DictionaryDto, DictionaryEntity> {

  @Override
  public DictionaryEntity mapToEntity(DictionaryDto dto) {
    DictionaryEntity dictionaryEntity = new DictionaryEntity();
    dictionaryEntity.setId(dto.getId());
    dictionaryEntity.setLastUpdated(dto.getLastUpdated());
    dictionaryEntity.setKey(dto.getKey());
    dictionaryEntity.setValue(dto.getValue());
    dictionaryEntity.setDictionary(dto.getDictionary());
    return dictionaryEntity;
  }

  @Override
  public DictionaryDto mapToDto(DictionaryEntity entity) {
    if (isNull(entity)) {
      return null;
    }
    DictionaryDto dictionaryDto = new DictionaryDto();
    dictionaryDto.setId(entity.getId());
    dictionaryDto.setLastUpdated(entity.getLastUpdated());
    dictionaryDto.setKey(entity.getKey());
    dictionaryDto.setValue(entity.getValue());
    dictionaryDto.setDictionary(entity.getDictionary());
    return dictionaryDto;
  }

  @Override
  public DictionaryEntity updateEntityWithDto(DictionaryDto dto, DictionaryEntity entity) {
    entity.setDictionary(dto.getDictionary());
    entity.setKey(dto.getKey());
    entity.setValue(dto.getValue());
    return entity;
  }
}
