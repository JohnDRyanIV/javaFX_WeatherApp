package model;

import java.time.LocalDate;

public class QueryData {
	
	private LocalDate startDate;
	private LocalDate endDate;
	private String location;
	
	public QueryData(LocalDate start, LocalDate end, String loc)
	{
		startDate = start;
		endDate = end;
		location = loc;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	

}

