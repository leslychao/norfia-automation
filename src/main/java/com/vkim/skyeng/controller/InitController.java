package com.vkim.skyeng.controller;

import com.vkim.skyeng.dto.AppConfigDto;
import com.vkim.skyeng.service.ExportStatementsService;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class InitController {

  private ExportStatementsService exportStatementsService;

  @Autowired
  public InitController(ExportStatementsService exportStatementsService) {
    this.exportStatementsService = exportStatementsService;
  }

  @RequestMapping(value = {"/init"})
  public String init(HttpSession session) {
    AppConfigDto appConfigDto = (AppConfigDto) session.getAttribute("app_config");
    appConfigDto.setPackId(UUID.randomUUID().toString());
    return "init";
  }

  @RequestMapping(value = {"/init/submit"}, method = RequestMethod.POST)
  public RedirectView init(@RequestParam MultipartFile xlsFile,
      @RequestParam String sheetName, @RequestParam int skipRowNum,
      @RequestParam int headerRowNum,
      HttpSession session) {
    AppConfigDto appConfigDto = (AppConfigDto) session.getAttribute("app_config");
    try {
      appConfigDto.setFileData(xlsFile.getBytes());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    appConfigDto.setFileName(xlsFile.getOriginalFilename());
    appConfigDto.setFileSize(xlsFile.getSize());
    appConfigDto.setSheetName(sheetName);
    appConfigDto.setSkipRowNum(skipRowNum);
    appConfigDto.setHeaderRowNum(headerRowNum - 1);
    exportStatementsService.exportStatements(appConfigDto);
    return new RedirectView("/home");
  }
}
