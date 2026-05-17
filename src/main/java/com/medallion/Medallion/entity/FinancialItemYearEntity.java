package com.medallion.Medallion.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "financial_item_year")
public class FinancialItemYearEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String displayName;
    private String keyName;
    private String value;

    private String qoQComp;
    private String yqoQComp;

    @ManyToOne
    @JoinColumn(name = "stock_financial_map_id")
    private StockFinancialMapEntity stockFinancialMap;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getQoQComp() {
        return qoQComp;
    }

    public void setQoQComp(String qoQComp) {
        this.qoQComp = qoQComp;
    }

    public String getYqoQComp() {
        return yqoQComp;
    }

    public void setYqoQComp(String yqoQComp) {
        this.yqoQComp = yqoQComp;
    }

    public StockFinancialMapEntity getStockFinancialMap() {
        return stockFinancialMap;
    }

    public void setStockFinancialMap(StockFinancialMapEntity stockFinancialMap) {
        this.stockFinancialMap = stockFinancialMap;
    }
}