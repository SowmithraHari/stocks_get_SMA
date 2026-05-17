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
@Table(name = "financial_category")
public class FinancialCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category")
    private String category;

    @OneToMany(mappedBy = "financialCategory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FinancialEntryEntity> entries;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "financial_statement_id")
    private FinancialStatementEntity financialStatement;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<FinancialEntryEntity> getEntries() {
        return entries;
    }

    public void setEntries(List<FinancialEntryEntity> entries) {
        this.entries = entries;
    }

    public FinancialStatementEntity getFinancialStatement() {
        return financialStatement;
    }

    public void setFinancialStatement(FinancialStatementEntity financialStatement) {
        this.financialStatement = financialStatement;
    }
}