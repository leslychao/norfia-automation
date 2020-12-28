package com.vkim.norfia.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class LongIdEntity implements BaseEntity, Serializable {

  private static final long serialVersionUID = 4933415689033617501L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator")
  private Long id;

  private LocalDateTime lastUpdated;

  @Override
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public LocalDateTime getLastUpdated() {
    return lastUpdated;
  }

  @Override
  public void setLastUpdated(LocalDateTime lastUpdated) {
    this.lastUpdated = lastUpdated;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LongIdEntity that = (LongIdEntity) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

}
