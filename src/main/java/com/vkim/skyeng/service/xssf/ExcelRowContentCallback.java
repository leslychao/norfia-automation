package com.vkim.skyeng.service.xssf;

import java.util.List;
import java.util.Map;

public interface ExcelRowContentCallback {

  void processRow(int rowNum, Map<String, String> mapData, List<Map<String, String>> data);

}
