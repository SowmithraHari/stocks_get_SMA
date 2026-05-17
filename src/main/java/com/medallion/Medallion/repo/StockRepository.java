package com.medallion.Medallion.repo;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medallion.Medallion.entity.StockEntity;

@Repository
public interface StockRepository extends JpaRepository<StockEntity, Integer> {

	StockEntity findTopByCompanyNameAndCreatedDateLessThanEqualOrderByCreatedDateDesc(String stock,
			LocalDate createdDate);
}
