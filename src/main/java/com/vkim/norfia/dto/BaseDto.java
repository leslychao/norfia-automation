package com.vkim.norfia.dto;

import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public abstract class BaseDto implements Serializable {

  private static final long serialVersionUID = -2457582915826281933L;

  private Long id;
}