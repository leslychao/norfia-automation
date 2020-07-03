package com.vkim.skyeng.service;

import static java.util.stream.Collectors.toList;

import com.vkim.skyeng.DictionaryType;
import com.vkim.skyeng.dto.DictionaryDto;
import com.vkim.skyeng.entity.DictionaryEntity;
import com.vkim.skyeng.mapper.BeanMapper;
import com.vkim.skyeng.repository.DictionaryRepository;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class DictionaryService extends AbstractCrudService<DictionaryDto, DictionaryEntity> {

  private BeanMapper<DictionaryDto, DictionaryEntity> beanMapper;
  private DictionaryRepository dictionaryRepository;

  @Autowired
  public DictionaryService(
      BeanMapper<DictionaryDto, DictionaryEntity> beanMapper,
      DictionaryRepository dictionaryRepository) {
    this.beanMapper = beanMapper;
    this.dictionaryRepository = dictionaryRepository;
  }

  @Transactional(readOnly = true)
  public List<DictionaryDto> findByDictionaryType(DictionaryType dictionaryType) {
    return dictionaryRepository.findByDictionaryTypeOrderByIdAsc(dictionaryType)
        .stream()
        .map(this::mapToDto)
        .collect(toList());
  }

  @Transactional(readOnly = true)
  public DictionaryDto findByDictionaryTypeAndDictionaryKey(DictionaryType dictionaryType,
      String dictionaryKey) {
    return mapToDto(dictionaryRepository
        .findByDictionaryTypeAndDictionaryKeyOrderByIdAsc(dictionaryType, dictionaryKey));
  }

  @Override
  protected BeanMapper<DictionaryDto, DictionaryEntity> getMapper() {
    return beanMapper;
  }

  @Override
  protected CrudRepository<DictionaryEntity, Long> getRepository() {
    return dictionaryRepository;
  }
}
