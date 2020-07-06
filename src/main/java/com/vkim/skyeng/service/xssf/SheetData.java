package com.vkim.skyeng.service.xssf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SheetData {

  private String sheetName;
  private int headerRowNum;
  private int startRowNum;
  private int lastRowNum;
  private int rowCount;
  private List<Map<String, String>> data = new ArrayList<>();
  private Map<String, Integer> columnIndex = new HashMap<>();
}
