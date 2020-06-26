package com.vkim.skyeng.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "statements")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@SequenceGenerator(name = "sequence_generator", sequenceName = "statements_seq", allocationSize = 1)
public class StatementEntity extends BaseEntity {

  @Column(name = "credit")
  private String credit;
  @Column(name = "name")
  private String name;
  @Column(name = "inn")
  private String inn;
  @Column(name = "payment_details")
  private String paymentDetails;
}
