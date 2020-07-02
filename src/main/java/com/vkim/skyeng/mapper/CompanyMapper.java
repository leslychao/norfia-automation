package com.vkim.skyeng.mapper;

import static java.util.Objects.isNull;

import com.vkim.skyeng.dto.CompanyDto;
import com.vkim.skyeng.entity.CompanyEntity;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper implements BeanMapper<CompanyDto, CompanyEntity> {

  @Override
  public CompanyEntity mapToEntity(CompanyDto dto) {
    CompanyEntity companyEntity = new CompanyEntity();
    companyEntity.setExternalCompanyId(dto.getExternalCompanyId());
    companyEntity.setCompanyName(dto.getCompanyName());
    companyEntity.setManagers(dto.getManagers());
    companyEntity.setCredit(dto.getCredit());
    companyEntity.setPaymentNumber(dto.getPaymentNumber());
    companyEntity.setId(dto.getId());
    companyEntity.setLastUpdated(dto.getLastUpdated());
    return companyEntity;
  }

  @Override
  public CompanyDto mapToDto(CompanyEntity entity) {
    if (isNull(entity)) {
      return null;
    }
    CompanyDto companyDto = new CompanyDto();
    companyDto.setExternalCompanyId(entity.getExternalCompanyId());
    companyDto.setCompanyName(entity.getCompanyName());
    companyDto.setManagers(entity.getManagers());
    companyDto.setCredit(entity.getCredit());
    companyDto.setPaymentNumber(entity.getPaymentNumber());
    companyDto.setId(entity.getId());
    companyDto.setLastUpdated(entity.getLastUpdated());
    return companyDto;
  }

  @Override
  public CompanyEntity updateEntityWithDto(CompanyDto dto, CompanyEntity entity) {
    entity.setExternalCompanyId(dto.getExternalCompanyId());
    entity.setCompanyName(dto.getCompanyName());
    entity.setManagers(dto.getManagers());
    entity.setCredit(dto.getCredit());
    entity.setPaymentNumber(dto.getPaymentNumber());
    return entity;
  }
}
