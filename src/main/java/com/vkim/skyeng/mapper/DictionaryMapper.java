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
    dictionaryEntity.setModule(dto.getModule());
    dictionaryEntity.setDictionaryType(dto.getDictionaryType());
    dictionaryEntity.setDictionaryKey(dto.getDictionaryKey());
    dictionaryEntity.setDictionaryValue(dto.getDictionaryValue());
    dictionaryEntity.setId(dto.getId());
    dictionaryEntity.setLastUpdated(dto.getLastUpdated());
    return dictionaryEntity;
  }

  @Override
  public DictionaryDto mapToDto(DictionaryEntity entity) {
    if (isNull(entity)) {
      return null;
    }
    DictionaryDto dictionaryDto = new DictionaryDto();
    dictionaryDto.setModule(entity.getModule());
    dictionaryDto.setDictionaryType(entity.getDictionaryType());
    dictionaryDto.setDictionaryKey(entity.getDictionaryKey());
    dictionaryDto.setDictionaryValue(entity.getDictionaryValue());
    dictionaryDto.setId(entity.getId());
    dictionaryDto.setLastUpdated(entity.getLastUpdated());
    return dictionaryDto;
  }

  @Override
  public DictionaryEntity updateEntityWithDto(DictionaryDto dto, DictionaryEntity entity) {
    entity.setModule(dto.getModule());
    entity.setDictionaryType(dto.getDictionaryType());
    entity.setDictionaryKey(dto.getDictionaryKey());
    entity.setDictionaryValue(dto.getDictionaryValue());
    return entity;
  }
}
