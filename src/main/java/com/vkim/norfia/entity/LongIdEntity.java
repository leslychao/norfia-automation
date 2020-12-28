package com.vkim.norfia.entity;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode
public class LongIdEntity implements Serializable {

  private static final long serialVersionUID = 4933415689033617501L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

}
