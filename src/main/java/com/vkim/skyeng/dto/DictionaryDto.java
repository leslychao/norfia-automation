package com.vkim.skyeng.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DictionaryDto extends BaseDto {

  private String dictionary;
  private String key;
  private String value;
}
