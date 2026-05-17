package com.medallion.Medallion.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "financial_entry")
public class FinancialEntryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "entry_key")
    private String key;

    @Column(name = "value")
    private String value;

    // Using JSON-friendly flexible types (can be refined later)
    @Column(name = "qoq_comp", columnDefinition = "TEXT")
    private String qoQComp;

    @Column(name = "yoy_comp", columnDefinition = "TEXT")
    private String yqoQComp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "financial_category_id")
    private FinancialCategoryEntity financialCategory;

    // Getters and Setters

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

    public FinancialCategoryEntity getFinancialCategory() {
        return financialCategory;
    }

    public void setFinancialCategory(FinancialCategoryEntity financialCategory) {
        this.financialCategory = financialCategory;
    }
}