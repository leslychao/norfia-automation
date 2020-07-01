package com.vkim.skyeng.controller;

import com.vkim.skyeng.dto.AppConfigDto;
import com.vkim.skyeng.service.CompanyService;
import java.time.format.DateTimeFormatter;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Slf4j
public class CompanyController {

  private CompanyService companyService;

  @Autowired
  public CompanyController(CompanyService companyService) {
    this.companyService = companyService;
  }

  @RequestMapping(value = {"/company"}, method = RequestMethod.GET)
  public String home(Model model, HttpSession session) {
    AppConfigDto appConfigDto = (AppConfigDto) session.getAttribute("app_config");
    model.addAttribute("companies", companyService.findAll());
    model.addAttribute("localDateTimeFormatter", DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
    return "company";
  }
}
