package com.medallion.Medallion.dto;

import java.util.List;

public class StockCorporateActionDataDto {

	private List<BonusDto> bonus;
	private List<DividendDto> dividend;
	private List<RightsDto> rights;
	private List<SplitDto> splits;
	private List<AnnualGeneralMeetingDto> annualGeneralMeeting;
	private List<BoardMeetingDto> boardMeetings;
	public List<BonusDto> getBonus() {
		return bonus;
	}
	public void setBonus(List<BonusDto> bonus) {
		this.bonus = bonus;
	}
	public List<DividendDto> getDividend() {
		return dividend;
	}
	public void setDividend(List<DividendDto> dividend) {
		this.dividend = dividend;
	}
	public List<RightsDto> getRights() {
		return rights;
	}
	public void setRights(List<RightsDto> rights) {
		this.rights = rights;
	}
	public List<SplitDto> getSplits() {
		return splits;
	}
	public void setSplits(List<SplitDto> splits) {
		this.splits = splits;
	}
	public List<AnnualGeneralMeetingDto> getAnnualGeneralMeeting() {
		return annualGeneralMeeting;
	}
	public void setAnnualGeneralMeeting(List<AnnualGeneralMeetingDto> annualGeneralMeeting) {
		this.annualGeneralMeeting = annualGeneralMeeting;
	}
	public List<BoardMeetingDto> getBoardMeetings() {
		return boardMeetings;
	}
	public void setBoardMeetings(List<BoardMeetingDto> boardMeetings) {
		this.boardMeetings = boardMeetings;
	}
	
	

}
