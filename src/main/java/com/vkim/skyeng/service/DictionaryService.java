package com.vkim.skyeng.service;

import com.vkim.skyeng.dto.DictionaryDto;
import com.vkim.skyeng.entity.DictionaryEntity;
import com.vkim.skyeng.mapper.BeanMapper;
import com.vkim.skyeng.repository.DictionaryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

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

  @Override
  protected BeanMapper<DictionaryDto, DictionaryEntity> getMapper() {
    return beanMapper;
  }

  @Override
  protected CrudRepository<DictionaryEntity, Long> getRepository() {
    return dictionaryRepository;
  }
}
