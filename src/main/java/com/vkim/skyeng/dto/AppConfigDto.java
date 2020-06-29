package com.vkim.skyeng.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AppConfigDto extends BaseDto {

  private byte[] fileData;
  private String fileName;
  private long fileSize;
  private String skyengCookie;
  private String sheetName;
  private int skipRowNum;
  private int headerRowNum;
  private String lastUrl;
  private String packId;
}
