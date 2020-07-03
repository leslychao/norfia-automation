package com.vkim.skyeng.controller;

import com.vkim.skyeng.dto.AppConfigDto;
import com.vkim.skyeng.service.CompanyService;
import com.vkim.skyeng.service.CsvExporter;
import java.time.format.DateTimeFormatter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Slf4j
public class CompanyController {

  private CompanyService companyService;
  private CsvExporter csvExporter;

  @Autowired
  public CompanyController(CompanyService companyService, CsvExporter csvExporter) {
    this.companyService = companyService;
    this.csvExporter = csvExporter;
  }

  @RequestMapping(value = {"/company"}, method = RequestMethod.GET)
  public String home(Model model, HttpSession session) {
    AppConfigDto appConfigDto = (AppConfigDto) session.getAttribute("app_config");
    model.addAttribute("companies", companyService.findAll());
    model.addAttribute("localDateTimeFormatter", DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
    return "company";
  }

  @RequestMapping(path = "/company/download", method = RequestMethod.GET)
  public HttpEntity<ByteArrayResource> download(HttpServletResponse response) {
    HttpHeaders header = new HttpHeaders();
    header.setContentType(new MediaType("application", "force-download"));
    header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=my_file.xlsx");
    return new HttpEntity<>(new ByteArrayResource(csvExporter.exportToCsv()), header);
  }

}
