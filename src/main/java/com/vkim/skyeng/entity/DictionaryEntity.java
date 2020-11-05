package com.vkim.skyeng.entity;

import com.vkim.skyeng.DictionaryType;

import javax.persistence.*;

@Entity
@Table(name = "dictionaries", indexes = {
        @Index(name = "dictionaries_idx_01", columnList = "id", unique = true),
        @Index(name = "dictionaries_idx_02", columnList = "module"),
        @Index(name = "dictionaries_idx_03", columnList = "dictionaryType, dictionaryKey")
})
@SequenceGenerator(name = "sequence_generator", sequenceName = "dictionaries_seq", allocationSize = 1)
public class DictionaryEntity extends LongIdEntity {

    private String module;
    @Enumerated(EnumType.STRING)
    private DictionaryType dictionaryType;
    private String dictionaryKey;
    private String dictionaryValue;

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public DictionaryType getDictionaryType() {
        return dictionaryType;
    }

    public void setDictionaryType(DictionaryType dictionaryType) {
        this.dictionaryType = dictionaryType;
    }

    public String getDictionaryKey() {
        return dictionaryKey;
    }

    public void setDictionaryKey(String dictionaryKey) {
        this.dictionaryKey = dictionaryKey;
    }

    public String getDictionaryValue() {
        return dictionaryValue;
    }

    public void setDictionaryValue(String dictionaryValue) {
        this.dictionaryValue = dictionaryValue;
    }
}
