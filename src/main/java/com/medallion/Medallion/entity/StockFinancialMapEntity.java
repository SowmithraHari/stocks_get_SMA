package com.medallion.Medallion.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "stock_financial_map")
public class StockFinancialMapEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "stock_financial_map_id")
    private List<FinancialItemYearEntity> CAS;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "stock_financial_map_id")
    private List<FinancialItemYearEntity> BAL;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FinancialItemYearEntity> INC;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<FinancialItemYearEntity> getCAS() {
        return CAS;
    }

    public void setCAS(List<FinancialItemYearEntity> CAS) {
        this.CAS = CAS;
    }

    public List<FinancialItemYearEntity> getBAL() {
        return BAL;
    }

    public void setBAL(List<FinancialItemYearEntity> BAL) {
        this.BAL = BAL;
    }

    public List<FinancialItemYearEntity> getINC() {
        return INC;
    }

    public void setINC(List<FinancialItemYearEntity> INC) {
        this.INC = INC;
    }
}