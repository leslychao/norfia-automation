package com.vkim.skyeng.service.xssf;


import static java.lang.String.valueOf;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class StatementsRowContentCallback implements ExcelRowContentCollback {

  @Override
  public void processRow(int rowNum, Map<String, String> mapData, List<Map<String, String>> data) {
    mapData.put("rowNum", valueOf(rowNum));
    data.add(new LinkedHashMap<>(mapData));
  }
}
