package com.vkim.norfia.service.testing;

import static java.lang.String.valueOf;

import com.vkim.norfia.service.xssf.SheetData;
import com.vkim.norfia.service.xssf.XlsService;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class TestingService {

  @Autowired
  private XlsService xlsService;

  public void process(MultipartFile xlsFile, MultipartFile jsonFile,
      String sheetName,
      @RequestParam int skipRowNum,
      @RequestParam int headerRowNum) {
    SheetData sheetData;
    try {
      sheetData = xlsService
          .processXls(new ByteArrayInputStream(xlsFile.getBytes()),
              sheetName, skipRowNum,
              headerRowNum,
              (rowNum, mapData, data) -> {
                mapData.put("rowNum", valueOf(rowNum));
                data.add(new LinkedHashMap<>(mapData));
              });
    } catch (IOException | InvalidFormatException e) {
      throw new RuntimeException(e);
    }
    System.out.println(sheetData);
    try {
      String json = new String(jsonFile.getBytes());
      System.out.println(json);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
