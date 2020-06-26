package com.vkim.skyeng.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseDto implements Serializable {

  private static final long serialVersionUID = -2457582915826281933L;

  private Long id;
  private LocalDateTime lastUpdated;
}