package com.vkim.skyeng.service;

import static java.util.stream.Collectors.toList;

import com.vkim.skyeng.dto.DictionaryDto;
import com.vkim.skyeng.entity.DictionaryEntity;
import com.vkim.skyeng.mapper.BeanMapper;
import com.vkim.skyeng.repository.DictionaryRepository;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

  public List<DictionaryDto> findByDictionaryAndKey(String dictionary, String key) {
    return dictionaryRepository.findByDictionaryAndKeyOrderByIdAsc(dictionary, key)
        .stream()
        .map(beanMapper::mapToDto)
        .collect(toList());
  }

  private boolean contains(String str, String searchStr) {
    return Arrays.stream(str.split(" ")).allMatch(s -> {
      Pattern pattern = Pattern
          .compile("\\b(" + s + ")\\b", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
      Matcher matcher = pattern.matcher(searchStr);
      return matcher.find();
    });
  }

  public String extractCompanyShortName(String companyName) {
    List<DictionaryDto> dictionaries = findByDictionaryAndKey("dictionary_organization",
        "name_regex");
    return dictionaries
        .stream()
        .filter(dictionaryDto -> contains(dictionaryDto.getValue(), companyName))
        .findAny()
        .map(dictionaryDto -> {
          Pattern pattern = Pattern.compile("(?<=\")[^\"]+(?=\")");
          Matcher matcher = pattern.matcher(companyName);
          if (matcher.find()) {
            return matcher.group();
          }
          return null;
        })
        .orElse(null);
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
