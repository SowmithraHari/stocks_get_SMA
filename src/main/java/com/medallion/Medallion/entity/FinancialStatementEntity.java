package com.medallion.Medallion.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "financial_statement")
public class FinancialStatementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "financialStatement", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FinancialCategoryEntity> stockFinancials;

    @Column(name = "fiscal_year")
    private String fiscalYear;

    @Column(name = "end_date")
    private String endDate;

    @Column(name = "type")
    private String type;

    @Column(name = "statement_date")
    private String statementDate;

    @Column(name = "fiscal_period_number")
    private int fiscalPeriodNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "financials_id")
    private FinancialsEntity financials;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<FinancialCategoryEntity> getStockFinancials() {
        return stockFinancials;
    }

    public void setStockFinancials(List<FinancialCategoryEntity> stockFinancials) {
        this.stockFinancials = stockFinancials;
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

    public FinancialsEntity getFinancials() {
        return financials;
    }

    public void setFinancials(FinancialsEntity financials) {
        this.financials = financials;
    }
}