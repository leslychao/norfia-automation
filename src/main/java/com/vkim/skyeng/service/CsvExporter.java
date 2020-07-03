package com.vkim.skyeng.service;

import com.vkim.skyeng.dto.CompanyDto;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collection;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CsvExporter {

  private CompanyService companyService;

  @Autowired
  public CsvExporter(CompanyService companyService) {
    this.companyService = companyService;
  }

  private String generateRowValue(CompanyDto companyDto) {
    StringBuilder result = new StringBuilder();
    result.append(companyDto.getCompanyName())
        .append(", " + companyDto.getManagers())
        .append(" - " + companyDto.getCredit() + " рублей");
    if (StringUtils.isNotEmpty(companyDto.getPaymentNumber())) {
      result.append(" №" + companyDto.getPaymentNumber());
    }
    return result.toString();
  }

  public byte[] exportToCsv() {
    Collection<CompanyDto> companies = companyService.findAll();
    Workbook workbook = new XSSFWorkbook();
    Sheet sheet = workbook.createSheet("Компании из Выписок B2B");
    int rowNum = 1;
    for (CompanyDto companyDto : companies) {
      Row row = sheet.createRow(rowNum++);
      row.createCell(0).setCellValue(generateRowValue(companyDto));
    }
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    try {
      workbook.write(bos);
    } catch (IOException e) {
      throw new RuntimeException(e);
    } finally {
      try {
        bos.close();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
    return bos.toByteArray();
  }

}
