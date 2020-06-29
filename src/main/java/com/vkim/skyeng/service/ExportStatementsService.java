package com.vkim.skyeng.service;

import static com.vkim.skyeng.util.CommonUtils.getStringCellValue;
import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

import com.vkim.skyeng.dto.AppConfigDto;
import com.vkim.skyeng.dto.DictionaryDto;
import com.vkim.skyeng.dto.StatementDto;
import com.vkim.skyeng.entity.StatementEntity;
import com.vkim.skyeng.mapper.BeanMapper;
import com.vkim.skyeng.repository.StatementRepository;
import com.vkim.skyeng.service.xssf.StatementsRowContentCallback;
import com.vkim.skyeng.service.xssf.XlsService;
import com.vkim.skyeng.service.xssf.XlsServiceImpl.SheetData;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ExportStatementsService extends AbstractCrudService<StatementDto, StatementEntity> {

  private static final Pattern QUOTES_VALUE_PATTERN = Pattern.compile("(?<=\")[^\"]+(?=\")");

  private XlsService xlsService;
  private BeanMapper<StatementDto, StatementEntity> beanMapper;
  private StatementRepository statementRepository;
  private DictionaryService dictionaryService;

  @Autowired
  public ExportStatementsService(XlsService xlsService,
      BeanMapper<StatementDto, StatementEntity> beanMapper,
      StatementRepository statementRepository,
      DictionaryService dictionaryService) {
    this.xlsService = xlsService;
    this.beanMapper = beanMapper;
    this.statementRepository = statementRepository;
    this.dictionaryService = dictionaryService;
  }

  public List<StatementDto> findByPackId(String packId) {
    return statementRepository.findByPackId(packId)
        .stream()
        .map(beanMapper::mapToDto)
        .collect(toList());
  }

  static boolean contains(String legalPersonType, String actualCompanyName) {
    return Arrays.stream(legalPersonType.split(" ")).allMatch(s -> {
      Pattern pattern = Pattern
          .compile("\\b(" + s + ")\\b", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
      Matcher matcher = pattern.matcher(actualCompanyName);
      return matcher.find();
    });
  }

  public void processOrgName(boolean excludeIndividual, String packId) {
    List<StatementDto> statementDtos = findByPackId(packId);
    List<DictionaryDto> dictionaryDtos = dictionaryService
        .findByDictionaryAndKey("dictionary_organization", "name_regex");
    if (excludeIndividual) {
      Iterator<StatementDto> statementDtoIterator = statementDtos.iterator();
      while (statementDtoIterator.hasNext()) {
        StatementDto statementDto = statementDtoIterator.next();
        String actualCompanyName = statementDto.getName();
        boolean match = false;
        for (DictionaryDto dictionaryDto : dictionaryDtos) {
          String legalPersonType = dictionaryDto.getValue();
          if (contains(legalPersonType, actualCompanyName)) {
            Matcher matcher = QUOTES_VALUE_PATTERN.matcher(actualCompanyName);
            if (matcher.find()) {
              String shortName = matcher.group();
              statementDto.setName(shortName);
            }
            update(statementDto);
            match = true;
            break;
          }
        }
        if (!match) {
          delete(statementDto.getId());
        }
      }
    }
  }

  public List<StatementDto> exportStatements(AppConfigDto appConfigDto) {
    log.info("start exportStatements");
    if (appConfigDto == null || appConfigDto.getFileData() == null
        || appConfigDto.getSheetName() == null) {
      return Collections.emptyList();
    }
    SheetData sheetData;
    try {
      sheetData = xlsService
          .processXls(new ByteArrayInputStream(appConfigDto.getFileData()),
              appConfigDto.getSheetName(), appConfigDto.getSkipRowNum(),
              appConfigDto.getHeaderRowNum(),
              new StatementsRowContentCallback());
    } catch (InvalidFormatException | IOException e) {
      throw new RuntimeException(e);
    }
    List<StatementDto> statementsFromXls = getStatementsToExport(sheetData,
        appConfigDto.getPackId());
    List<StatementDto> statementsFromDb = persistStatementsIntoDb(statementsFromXls);
    log.info("export statements successfully completed!");
    return statementsFromDb;
  }

  List<StatementDto> persistStatementsIntoDb(List<StatementDto> statementsFromXls) {
    if (statementsFromXls.isEmpty()) {
      log.info("nothing to export");
      return Collections.EMPTY_LIST;
    }
    log.info("number of statements to export {}", statementsFromXls.size());
    return statementsFromXls
        .stream()
        .map(statementDto -> save(statementDto))
        .collect(toList());
  }

  private List<StatementDto> getStatementsToExport(SheetData sheetData, String packId) {
    return ofNullable(sheetData.getData())
        .orElse(emptyList())
        .stream()
        .map(stringStringMap -> toStatementDto(stringStringMap, packId))
        .collect(toList());
  }

  private StatementDto toStatementDto(Map<String, String> statementXls, String packId) {
    String credit = getStringCellValue(statementXls, "Кредит", null);
    String name = getStringCellValue(statementXls, "Наименование ", null);
    String inn = getStringCellValue(statementXls, "ИНН", null);
    String paymentDetails = getStringCellValue(statementXls, "Назначение платежа", null);

    StatementDto statementDto = new StatementDto();
    statementDto.setCredit(credit);
    statementDto.setName(name);
    statementDto.setInn(inn);
    statementDto.setPaymentDetails(paymentDetails);
    statementDto.setPackId(packId);
    return statementDto;
  }

  @Override
  protected BeanMapper<StatementDto, StatementEntity> getMapper() {
    return beanMapper;
  }

  @Override
  protected CrudRepository<StatementEntity, Long> getRepository() {
    return statementRepository;
  }
}
