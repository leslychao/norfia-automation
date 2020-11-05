package com.vkim.skyeng.repository;

import com.vkim.skyeng.DictionaryType;
import com.vkim.skyeng.entity.DictionaryEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DictionaryRepository extends JpaRepository<DictionaryEntity, Long> {

  List<DictionaryEntity> findByDictionaryTypeOrderByIdAsc(DictionaryType dictionaryType);

  DictionaryEntity findByDictionaryTypeAndDictionaryKeyOrderByIdAsc(DictionaryType dictionaryType,
      String dictionaryKey);
}
