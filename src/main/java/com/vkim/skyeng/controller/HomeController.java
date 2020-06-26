package com.vkim.skyeng.controller;

import com.vkim.skyeng.dto.AppConfigDto;
import com.vkim.skyeng.dto.DictionaryDto;
import com.vkim.skyeng.service.DictionaryService;
import com.vkim.skyeng.service.ExportStatementsService;
import java.time.format.DateTimeFormatter;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@Slf4j
public class HomeController {

  private ExportStatementsService exportStatementsService;

  private DictionaryService dictionaryService;

  @Autowired
  public HomeController(ExportStatementsService exportStatementsService,
      DictionaryService dictionaryService) {
    this.exportStatementsService = exportStatementsService;
    this.dictionaryService = dictionaryService;
  }

  @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
  public String home(Model model, HttpSession session) {
    AppConfigDto appConfigDto = (AppConfigDto) session.getAttribute("app_config");
    model.addAttribute("statements", exportStatementsService.exportStatements(appConfigDto));
    model.addAttribute("localDateTimeFormatter", DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
    return "home";
  }

  @RequestMapping(value = {"/home/saveskyengcookie"}, method = RequestMethod.POST)
  public RedirectView saveskyengcookie(@RequestParam String skyengcookie, HttpSession session) {
    AppConfigDto appConfigDto = (AppConfigDto) session.getAttribute("app_config");
    appConfigDto.setSkyengCookie(skyengcookie);
    return new RedirectView("/home");
  }

  @RequestMapping(value = {"/home/clearskyengcookie"}, method = RequestMethod.GET)
  public RedirectView clearskyengcookie(HttpSession session) {
    AppConfigDto appConfigDto = (AppConfigDto) session.getAttribute("app_config");
    appConfigDto.setSkyengCookie(null);
    return new RedirectView("/home");
  }

  @RequestMapping(value = {"/home/dictionary"}, method = RequestMethod.GET)
  public String dictionary(Model model) {
    model.addAttribute("dictionaries", dictionaryService.findAll());
    return "dictionary";
  }

  @RequestMapping(value = {"/home/dictionaryform"}, method = RequestMethod.GET)
  public String dictionaryForm(Model model, @RequestParam("id") Long id) {
    model.addAttribute("dictionary", new DictionaryDto());
    return "dictionaryform";
  }

  @RequestMapping(value = {"/home/addDictionary"}, method = RequestMethod.POST)
  public RedirectView addDictionary(@ModelAttribute("employee") DictionaryDto dictionaryDto) {
    log.info("add dictionary {}", dictionaryDto);
    dictionaryService.save(dictionaryDto);
    return new RedirectView("/home/dictionary");
  }

  @RequestMapping(value = {"/home/editDictionary"}, method = RequestMethod.GET)
  public String editDictionary(RedirectAttributes attributes, @RequestParam("id") Long id) {
    attributes.addAttribute("dictionary", dictionaryService.get(id));
    return "dictionaryform";
  }

  @RequestMapping(value = {"/home/removeDictionary"}, method = RequestMethod.GET)
  public RedirectView removeDictionary(@RequestParam("id") Long id) {
    dictionaryService.delete(id);
    return new RedirectView("/home/dictionary");
  }
}
