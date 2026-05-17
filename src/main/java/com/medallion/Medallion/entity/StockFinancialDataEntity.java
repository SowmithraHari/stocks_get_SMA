package com.medallion.Medallion.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "stock_financial_data")
public class StockFinancialDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "stockFinancialData", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StockFinancialYearEntity> stockFinancialData;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<StockFinancialYearEntity> getStockFinancialData() {
        return stockFinancialData;
    }

    public void setStockFinancialData(List<StockFinancialYearEntity> stockFinancialData) {
        this.stockFinancialData = stockFinancialData;
    }
}