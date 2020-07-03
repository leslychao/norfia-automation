package com.vkim.skyeng.entity;

import com.vkim.skyeng.SyncState;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "statements", indexes = {
    @Index(name = "statements_idx_01", columnList = "id", unique = true),
    @Index(name = "statements_idx_02", columnList = "syncState"),
    @Index(name = "statements_idx_03", columnList = "shortName"),
    @Index(name = "statements_idx_04", columnList = "packId")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@SequenceGenerator(name = "sequence_generator", sequenceName = "statements_seq", allocationSize = 1)
public class StatementEntity extends BaseEntity {

  private String credit;
  private String name;
  private String inn;
  private String paymentDetails;
  private String packId;
  @Enumerated(EnumType.STRING)
  private SyncState syncState;
  private String log;
}
