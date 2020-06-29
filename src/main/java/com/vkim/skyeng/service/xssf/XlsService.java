package com.vkim.skyeng.service.xssf;

import com.vkim.skyeng.service.xssf.XlsServiceImpl.SheetData;
import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public interface XlsService {

  SheetData processXls(InputStream inputStream, String sheetName, int skipRowNum,
      int headerRowNum, ExcelRowContentCollback excelRowContentCollback)
      throws IOException, InvalidFormatException;
}
