package com.vkim.norfia.util;

import static java.util.Objects.isNull;
import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

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

  public static Object getNode(Object jsonObject, List<String> path) {
    return getNode(jsonObject, path, path.size() - 1, 0);
  }

  public static Object getNode(Object jsonNode, List<String> path, int position, int step) {
    if (position >= path.size() || position < 0) {
      throw new IndexOutOfBoundsException();
    }
    if (step > position) {
      return jsonNode;
    }
    String key = path.get(step);
    if (jsonNode instanceof JSONArray) {
      JSONArray jsonArray = (JSONArray) jsonNode;
      return getNode(jsonArray.get(Integer.parseInt(key)), path, position, ++step);
    } else if (jsonNode instanceof JSONObject) {
      JSONObject jsonObject = (JSONObject) jsonNode;
      if (jsonObject.has(key)) {
        return getNode(jsonObject.get(key), path, position, ++step);
      }
      return null;
    } else {
      return jsonNode;
    }
  }

}
