package com.vkim.skyeng.entity;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "companies", indexes = {
    @Index(name = "companies_idx_01", columnList = "id", unique = true),
    @Index(name = "companies_idx_02", columnList = "companyName", unique = true)
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@SequenceGenerator(name = "sequence_generator", sequenceName = "companies_seq", allocationSize = 1)
public class CompanyEntity extends BaseEntity {

  private Long externalCompanyId;
  private String companyName;
  private String managers;
  private String credit;
  private String paymentNumber;

}
