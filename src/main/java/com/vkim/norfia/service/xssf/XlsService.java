package com.vkim.norfia.service.xssf;

import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public interface XlsService {

  SheetData processXls(InputStream inputStream, String sheetName, int skipRowNum,
      int headerRowNum, ExcelRowContentCallback excelRowContentCallback)
      throws IOException, InvalidFormatException;
}
