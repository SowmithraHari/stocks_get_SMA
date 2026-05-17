package com.medallion.Medallion.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "stock_corporate_action_data")
public class StockCorporateActionDataEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "stock_corporate_action_data_id")
	private List<DividendEntity> dividend;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "stock_corporate_action_data_id")
	private List<SplitEntity> splits;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "stock_corporate_action_data_id")
	private List<AnnualGeneralMeetingEntity> annualGeneralMeeting;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "stock_corporate_action_data_id")
	private List<BoardMeetingEntity> boardMeetings;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<DividendEntity> getDividend() {
		return dividend;
	}

	public void setDividend(List<DividendEntity> dividend) {
		this.dividend = dividend;
	}

	public List<SplitEntity> getSplits() {
		return splits;
	}

	public void setSplits(List<SplitEntity> splits) {
		this.splits = splits;
	}

	public List<AnnualGeneralMeetingEntity> getAnnualGeneralMeeting() {
		return annualGeneralMeeting;
	}

	public void setAnnualGeneralMeeting(List<AnnualGeneralMeetingEntity> annualGeneralMeeting) {
		this.annualGeneralMeeting = annualGeneralMeeting;
	}

	public List<BoardMeetingEntity> getBoardMeetings() {
		return boardMeetings;
	}

	public void setBoardMeetings(List<BoardMeetingEntity> boardMeetings) {
		this.boardMeetings = boardMeetings;
	}
}
