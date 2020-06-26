package com.vkim.skyeng.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BaseEntity implements Serializable {

  private static final long serialVersionUID = 4933415689033617501L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator")
  private Long id;

  @Column(name = "last_updated")
  private LocalDateTime lastUpdated;
}
