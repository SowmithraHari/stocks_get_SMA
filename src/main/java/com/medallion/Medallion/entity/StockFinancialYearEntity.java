package com.medallion.Medallion.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "stock_financial_year")
public class StockFinancialYearEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "stock_financial_data_id")
    private StockFinancialDataEntity stockFinancialData;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "stock_financial_map_id")
    private StockFinancialMapEntity stockFinancialMap;

    private String fiscalYear;
    private String endDate;
    private String type;
    private String statementDate;
    private int fiscalPeriodNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StockFinancialDataEntity getStockFinancialData() {
        return stockFinancialData;
    }

    public void setStockFinancialData(StockFinancialDataEntity stockFinancialData) {
        this.stockFinancialData = stockFinancialData;
    }

    public StockFinancialMapEntity getStockFinancialMap() {
        return stockFinancialMap;
    }

    public void setStockFinancialMap(StockFinancialMapEntity stockFinancialMap) {
        this.stockFinancialMap = stockFinancialMap;
    }

    public String getFiscalYear() {
        return fiscalYear;
    }

    public void setFiscalYear(String fiscalYear) {
        this.fiscalYear = fiscalYear;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatementDate() {
        return statementDate;
    }

    public void setStatementDate(String statementDate) {
        this.statementDate = statementDate;
    }

    public int getFiscalPeriodNumber() {
        return fiscalPeriodNumber;
    }

    public void setFiscalPeriodNumber(int fiscalPeriodNumber) {
        this.fiscalPeriodNumber = fiscalPeriodNumber;
    }
}