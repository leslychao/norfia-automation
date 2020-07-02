package com.vkim.skyeng.entity;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "dictionaries", indexes = {
    @Index(name = "dictionaries_idx_01", columnList = "id", unique = true),
    @Index(name = "dictionaries_idx_02", columnList = "dictionary, key", unique = true)
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@SequenceGenerator(name = "sequence_generator", sequenceName = "dictionaries_seq", allocationSize = 1)
public class DictionaryEntity extends BaseEntity {

  private String dictionary;
  private String key;
  private String value;

}
