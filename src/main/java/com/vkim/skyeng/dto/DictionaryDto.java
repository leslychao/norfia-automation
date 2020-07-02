package com.vkim.skyeng.dto;

import com.vkim.skyeng.DictionaryType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DictionaryDto extends BaseDto {

  private String module;
  private DictionaryType dictionaryType;
  private String dictionaryKey;
  private String dictionaryValue;
}
