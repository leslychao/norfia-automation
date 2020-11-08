package com.vkim.skyeng.service.xssf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SheetData {

  private String sheetName;
  private int headerRowNum;
  private int startRowNum;
  private int lastRowNum;
  private int rowCount;
  private List<Map<String, String>> data = new ArrayList<>();
  private Map<String, Integer> columnIndex = new HashMap<>();

  public String getSheetName() {
    return sheetName;
  }

  public void setSheetName(String sheetName) {
    this.sheetName = sheetName;
  }

  public int getHeaderRowNum() {
    return headerRowNum;
  }

  public void setHeaderRowNum(int headerRowNum) {
    this.headerRowNum = headerRowNum;
  }

  public int getStartRowNum() {
    return startRowNum;
  }

  public void setStartRowNum(int startRowNum) {
    this.startRowNum = startRowNum;
  }

  public int getLastRowNum() {
    return lastRowNum;
  }

  public void setLastRowNum(int lastRowNum) {
    this.lastRowNum = lastRowNum;
  }

  public int getRowCount() {
    return rowCount;
  }

  public void setRowCount(int rowCount) {
    this.rowCount = rowCount;
  }

  public List<Map<String, String>> getData() {
    return data;
  }

  public void setData(List<Map<String, String>> data) {
    this.data = data;
  }

  public Map<String, Integer> getColumnIndex() {
    return columnIndex;
  }

  public void setColumnIndex(Map<String, Integer> columnIndex) {
    this.columnIndex = columnIndex;
  }
}
