package com.medallion.Medallion.dto;

public class FinancialEntryDto {

    private String displayName;
    private String key;
    private String value;
    private Object qoQComp;
    private Object yqoQComp;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Object getQoQComp() {
        return qoQComp;
    }

    public void setQoQComp(Object qoQComp) {
        this.qoQComp = qoQComp;
    }

    public Object getYqoQComp() {
        return yqoQComp;
    }

    public void setYqoQComp(Object yqoQComp) {
        this.yqoQComp = yqoQComp;
    }
}
