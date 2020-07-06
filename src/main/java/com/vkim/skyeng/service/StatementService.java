package com.vkim.skyeng.service;

import static com.vkim.skyeng.util.CommonUtils.getStringCellValue;
import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

import com.vkim.skyeng.SyncState;
import com.vkim.skyeng.dto.AppConfigDto;
import com.vkim.skyeng.dto.StatementDto;
import com.vkim.skyeng.entity.StatementEntity;
import com.vkim.skyeng.mapper.BeanMapper;
import com.vkim.skyeng.repository.StatementRepository;
import com.vkim.skyeng.service.xssf.SheetData;
import com.vkim.skyeng.service.xssf.StatementsRowContentCallback;
import com.vkim.skyeng.service.xssf.XlsService;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class StatementService extends AbstractCrudService<StatementDto, StatementEntity> {

  private XlsService xlsService;
  private BeanMapper<StatementDto, StatementEntity> beanMapper;
  private StatementRepository statementRepository;
  private DictionaryService dictionaryService;

  @Autowired
  public StatementService(XlsService xlsService,
      BeanMapper<StatementDto, StatementEntity> beanMapper,
      StatementRepository statementRepository,
      DictionaryService dictionaryService) {
    this.xlsService = xlsService;
    this.beanMapper = beanMapper;
    this.statementRepository = statementRepository;
    this.dictionaryService = dictionaryService;
  }

  public List<StatementDto> findByPackId(String packId) {
    return statementRepository.findByPackIdOrderByIdAsc(packId)
        .stream()
        .map(this::mapToDto)
        .collect(toList());
  }


  public List<StatementDto> processStatementsFromXls(AppConfigDto appConfigDto) {
    if (appConfigDto == null
        || appConfigDto.getFileData() == null
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
    return saveAll(statementsFromXls(sheetData, appConfigDto.getPackId()));
  }

  private List<StatementDto> statementsFromXls(SheetData sheetData, String packId) {
    return ofNullable(sheetData.getData())
        .orElse(emptyList())
        .stream()
        .map(stringStringMap -> toStatementDto(stringStringMap, packId))
        .collect(toList());
  }

  @Transactional
  public List<StatementDto> saveAll(List<StatementDto> statements) {
    if (statements.isEmpty()) {
      return Collections.EMPTY_LIST;
    }
    return statements
        .stream()
        .map(statementDto -> save(statementDto))
        .collect(toList());
  }

  private StatementDto toStatementDto(Map<String, String> statementXls, String packId) {
    String credit = getStringCellValue(statementXls, "Кредит", null);
    String name = getStringCellValue(statementXls, "Наименование", null);
    String inn = getStringCellValue(statementXls, "ИНН", null);
    String paymentDetails = getStringCellValue(statementXls, "Назначение платежа", null);

    StatementDto statementDto = new StatementDto();
    statementDto.setCredit(credit);
    statementDto.setName(name);
    statementDto.setInn(inn);
    statementDto.setPaymentDetails(paymentDetails);
    statementDto.setPackId(packId);
    statementDto.setSyncState(SyncState.READY_TO_SEND);

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
