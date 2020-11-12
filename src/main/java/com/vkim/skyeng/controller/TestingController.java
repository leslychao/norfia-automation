package com.vkim.skyeng.controller;

import com.vkim.skyeng.dto.testing.TestingDto;
import com.vkim.skyeng.service.testing.TestingService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/testing")
public class TestingController {

  @Autowired
  private TestingService testingService;

  @GetMapping("init")
  public String initPage() {
    return "init_testing";
  }

  @PostMapping
  public String init(
      @RequestParam MultipartFile xlsFile,
      @RequestParam MultipartFile jsonFile,
      @RequestParam String sheetName,
      @RequestParam int skipRowNum,
      @RequestParam int headerRowNum,
      Model model) {
    testingService.process(xlsFile, jsonFile, sheetName, skipRowNum, headerRowNum);
    return "testing_result";
  }

  @GetMapping
  public String result(@ModelAttribute List<TestingDto> testingDtoList) {
    return "testing_result";
  }
}
