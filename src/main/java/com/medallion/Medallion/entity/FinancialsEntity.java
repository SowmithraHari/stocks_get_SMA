package com.medallion.Medallion.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "financials")
public class FinancialsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "financials", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FinancialStatementEntity> financials;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<FinancialStatementEntity> getFinancials() {
        return financials;
    }

    public void setFinancials(List<FinancialStatementEntity> financials) {
        this.financials = financials;
    }
}