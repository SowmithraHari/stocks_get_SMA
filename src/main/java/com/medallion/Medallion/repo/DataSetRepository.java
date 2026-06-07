package com.medallion.Medallion.repo;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medallion.Medallion.entity.DatasetEntity;

@Repository
public interface DataSetRepository extends JpaRepository<DatasetEntity, Integer> {

	public DatasetEntity findByCreatedDate(LocalDate createdDate);

	public DatasetEntity findTopByStocknameAndCreatedDateLessThanEqualOrderByCreatedDateDesc(String stock,
			LocalDate createdDate);
	
	public DatasetEntity findTopByStocknameOrderByCreatedDateDesc(String stocks);
	
	public DatasetEntity findTopByStocknameAndPeriodOrderByCreatedDateDesc(String stocks,String period);
	
	
}
