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
    statementEntity.setId(dto.getId());
    statementEntity.setCredit(dto.getCredit());
    statementEntity.setInn(dto.getInn());
    statementEntity.setName(dto.getName());
    statementEntity.setShortName(dto.getShortName());
    statementEntity.setPaymentDetails(dto.getPaymentDetails());
    statementEntity.setLastUpdated(dto.getLastUpdated());
    statementEntity.setPackId(dto.getPackId());
    statementEntity.setSyncState(dto.getSyncState());
    return statementEntity;
  }

  @Override
  public StatementDto mapToDto(StatementEntity entity) {
    if (isNull(entity)) {
      return null;
    }
    StatementDto statementDto = new StatementDto();
    statementDto.setId(entity.getId());
    statementDto.setCredit(entity.getCredit());
    statementDto.setInn(entity.getInn());
    statementDto.setName(entity.getName());
    statementDto.setShortName(entity.getShortName());
    statementDto.setPaymentDetails(entity.getPaymentDetails());
    statementDto.setLastUpdated(entity.getLastUpdated());
    statementDto.setPackId(entity.getPackId());
    statementDto.setSyncState(entity.getSyncState());
    return statementDto;
  }

  @Override
  public StatementEntity updateEntityWithDto(StatementDto dto, StatementEntity entity) {
    entity.setName(dto.getName());
    entity.setPaymentDetails(dto.getPaymentDetails());
    entity.setSyncState(dto.getSyncState());
    entity.setShortName(dto.getShortName());
    return entity;
  }
}
