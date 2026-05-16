package com.medallion.Medallion.dto;

public class OfficerDto {

    private Integer rank;
    private String since;

    private String firstName;
    private String middleName;
    private String lastName;

    private Integer age;

    private TitleDto title;

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public String getSince() {
		return since;
	}

	public void setSince(String since) {
		this.since = since;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public TitleDto getTitle() {
		return title;
	}

	public void setTitle(TitleDto title) {
		this.title = title;
	}
}
