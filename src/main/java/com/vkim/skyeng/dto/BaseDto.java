package com.vkim.skyeng.dto;

import java.io.Serializable;
import java.time.LocalDateTime;


public abstract class BaseDto implements Serializable {

    private static final long serialVersionUID = -2457582915826281933L;

    private Long id;
    private LocalDateTime lastUpdated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}