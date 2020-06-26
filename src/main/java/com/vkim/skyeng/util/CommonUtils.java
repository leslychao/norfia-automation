package com.vkim.skyeng.util;

import static java.util.Objects.isNull;
import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.util.Map;

public class CommonUtils {

  public static String getStringCellValue(Map<String, String> sheetAsMap,
      String cellName,
      String... defaultValue) {
    return getStringCellValue(sheetAsMap.get(cellName), defaultValue);
  }

  public static String getStringCellValue(String cellValue, String... defaultValue) {
    return ofNullable(cellValue)
        .map(String::trim)
        .orElseGet(() -> {
          if (isNull(defaultValue)) {
            return null;
          }
          return defaultValue.length == 1 ? defaultValue[0] : EMPTY;
        });
  }

}
