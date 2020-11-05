package com.vkim.skyeng.dto;

import com.vkim.skyeng.DictionaryType;

public class DictionaryDto extends BaseDto {

    private String module;
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
