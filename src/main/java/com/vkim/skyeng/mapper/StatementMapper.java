package com.vkim.skyeng.mapper;

import static java.util.Objects.isNull;

import com.vkim.skyeng.dto.StatementDto;
import com.vkim.skyeng.entity.StatementEntity;
import org.springframework.stereotype.Component;

@Component
public class StatementMapper implements BeanMapper<StatementDto, StatementEntity> {

  @Override
  public StatementEntity mapToEntity(StatementDto dto) {
    StatementEntity statementEntity = new StatementEntity();
    statementEntity.setCredit(dto.getCredit());
    statementEntity.setName(dto.getName());
    statementEntity.setInn(dto.getInn());
    statementEntity.setPaymentDetails(dto.getPaymentDetails());
    statementEntity.setPackId(dto.getPackId());
    statementEntity.setSyncState(dto.getSyncState());
    statementEntity.setLog(dto.getLog());
    statementEntity.setId(dto.getId());
    statementEntity.setLastUpdated(dto.getLastUpdated());
    return statementEntity;
  }

  @Override
  public StatementDto mapToDto(StatementEntity entity) {
    if (isNull(entity)) {
      return null;
    }
    StatementDto statementDto = new StatementDto();
    statementDto.setCredit(entity.getCredit());
    statementDto.setName(entity.getName());
    statementDto.setInn(entity.getInn());
    statementDto.setPaymentDetails(entity.getPaymentDetails());
    statementDto.setPackId(entity.getPackId());
    statementDto.setSyncState(entity.getSyncState());
    statementDto.setLog(entity.getLog());
    statementDto.setId(entity.getId());
    statementDto.setLastUpdated(entity.getLastUpdated());
    return statementDto;
  }

  public StatementEntity updateEntityWithDto(StatementDto dto, StatementEntity entity) {
    entity.setCredit(dto.getCredit());
    entity.setName(dto.getName());
    entity.setInn(dto.getInn());
    entity.setPaymentDetails(dto.getPaymentDetails());
    entity.setPackId(dto.getPackId());
    entity.setSyncState(dto.getSyncState());
    entity.setLog(dto.getLog());
    return entity;
  }
}
