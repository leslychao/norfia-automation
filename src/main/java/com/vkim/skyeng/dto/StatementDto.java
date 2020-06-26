package com.vkim.skyeng.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class StatementDto extends BaseDto {

  private String credit;
  private String name;
  private String inn;
  private String paymentDetails;

}
