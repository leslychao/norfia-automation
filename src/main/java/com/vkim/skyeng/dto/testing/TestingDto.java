package com.vkim.skyeng.dto.testing;

import com.vkim.skyeng.dto.BaseDto;

public class TestingDto extends BaseDto {

  private byte[] xlsFileData;
  private String xlsFileName;
  private long xlsFileSize;
  private byte[] jsonFileData;
  private String jsonFileName;
  private long jsonFileSize;
  private String sheetName;
  private int skipRowNum;
  private int headerRowNum;

  public byte[] getXlsFileData() {
    return xlsFileData;
  }

  public void setXlsFileData(byte[] xlsFileData) {
    this.xlsFileData = xlsFileData;
  }

  public String getXlsFileName() {
    return xlsFileName;
  }

  public void setXlsFileName(String xlsFileName) {
    this.xlsFileName = xlsFileName;
  }

  public long getXlsFileSize() {
    return xlsFileSize;
  }

  public void setXlsFileSize(long xlsFileSize) {
    this.xlsFileSize = xlsFileSize;
  }

  public byte[] getJsonFileData() {
    return jsonFileData;
  }

  public void setJsonFileData(byte[] jsonFileData) {
    this.jsonFileData = jsonFileData;
  }

  public String getJsonFileName() {
    return jsonFileName;
  }

  public void setJsonFileName(String jsonFileName) {
    this.jsonFileName = jsonFileName;
  }

  public long getJsonFileSize() {
    return jsonFileSize;
  }

  public void setJsonFileSize(long jsonFileSize) {
    this.jsonFileSize = jsonFileSize;
  }

  public String getSheetName() {
    return sheetName;
  }

  public void setSheetName(String sheetName) {
    this.sheetName = sheetName;
  }

  public int getSkipRowNum() {
    return skipRowNum;
  }

  public void setSkipRowNum(int skipRowNum) {
    this.skipRowNum = skipRowNum;
  }

  public int getHeaderRowNum() {
    return headerRowNum;
  }

  public void setHeaderRowNum(int headerRowNum) {
    this.headerRowNum = headerRowNum;
  }
}
