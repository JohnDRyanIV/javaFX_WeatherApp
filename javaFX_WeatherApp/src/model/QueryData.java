/**
 * @author John Ryan - john.ryan@drake.edu
 * CS 067 - Fall 2024
 * Dec 9th, 2024
 */
package model;

import java.time.LocalDate;

/**
 * This class holds data that will be required to construct a query. 
 */
public class QueryData {

	private LocalDate startDate;
	private LocalDate endDate;
	private String location;

	/**
	 * Holds data to be used to construct a query
	 * @param start - Start date of query
	 * @param end - end date of query
	 * @param loc - location of query
	 */
	public QueryData(LocalDate start, LocalDate end, String loc) {
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
