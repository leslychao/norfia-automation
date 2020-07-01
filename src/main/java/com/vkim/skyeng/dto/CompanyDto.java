package com.vkim.skyeng.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CompanyDto extends BaseDto {

  private Long externalCompanyId;
  private String companyName;
  private String managers;
  private String credit;
  private String paymentNumber;
  private boolean innMatched;

}
