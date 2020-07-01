package com.vkim.skyeng.util;

import static java.util.Objects.isNull;
import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.util.Arrays;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

  public static boolean contains(String str, String searchStr) {
    return Arrays.stream(str.split(" ")).allMatch(s -> {
      Pattern pattern = Pattern
          .compile("\\b(" + s + ")\\b", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
      Matcher matcher = pattern.matcher(searchStr);
      return matcher.find();
    });
  }

}
