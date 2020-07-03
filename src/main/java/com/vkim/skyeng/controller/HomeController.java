package com.vkim.skyeng.controller;

import com.vkim.skyeng.dto.AppConfigDto;
import com.vkim.skyeng.dto.DictionaryDto;
import com.vkim.skyeng.dto.StatementDto;
import com.vkim.skyeng.service.DictionaryService;
import com.vkim.skyeng.service.SkyEngIntegrationService;
import com.vkim.skyeng.service.StatementService;
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
import org.springframework.web.servlet.view.RedirectView;

@Controller
@Slf4j
public class HomeController {

  private StatementService statementService;
  private DictionaryService dictionaryService;
  private SkyEngIntegrationService skyEngIntegrationService;

  @Autowired
  public HomeController(StatementService statementService,
      DictionaryService dictionaryService, SkyEngIntegrationService skyEngIntegrationService) {
    this.statementService = statementService;
    this.dictionaryService = dictionaryService;
    this.skyEngIntegrationService = skyEngIntegrationService;
  }

  @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
  public String home(Model model, HttpSession session) {
    AppConfigDto appConfigDto = (AppConfigDto) session.getAttribute("app_config");
    model.addAttribute("statements", statementService.findByPackId(appConfigDto.getPackId()));
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
  public String dictionaryForm(Model model) {
    model.addAttribute("dictionary", new DictionaryDto());
    return "dictionaryform";
  }

  @RequestMapping(value = {"/home/addDictionary"}, method = RequestMethod.POST)
  public RedirectView addDictionary(@ModelAttribute DictionaryDto dictionaryDto) {
    log.info("add dictionary {}", dictionaryDto);
    dictionaryService.update(dictionaryDto);
    return new RedirectView("/home/dictionary");
  }

  @RequestMapping(value = {"/home/editDictionary"}, method = RequestMethod.GET)
  public String editDictionary(Model model, @RequestParam long id) {
    model.addAttribute("dictionary", dictionaryService.get(id));
    return "dictionaryform";
  }

  @RequestMapping(value = {"/home/removeDictionary"}, method = RequestMethod.GET)
  public RedirectView removeDictionary(@RequestParam("id") Long id) {
    log.info("remove dictionary with id {}", id);
    dictionaryService.delete(id);
    return new RedirectView("/home/dictionary");
  }

  @RequestMapping(value = {"/home/syncCompanies"}, method = RequestMethod.GET)
  public RedirectView syncCompanies(Model model, HttpSession session) {
    AppConfigDto appConfigDto = (AppConfigDto) session.getAttribute("app_config");
    skyEngIntegrationService.syncCompanies(appConfigDto);
    return new RedirectView("/home");
  }

  @RequestMapping(value = {"/home/editStatement"}, method = RequestMethod.GET)
  public String editStatement(@RequestParam("id") long id, Model model) {
    model.addAttribute("statement", statementService.get(id));
    return "statementeditform";
  }

  @RequestMapping(value = {"/home/saveStatement"}, method = RequestMethod.POST)
  public RedirectView saveStatement(@ModelAttribute StatementDto statementDto) {
    statementService.setShortName(statementDto);
    statementService.update(statementDto);
    return new RedirectView("/home");
  }

}
