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

	private LocalDate startDate; // Start date of query
	private LocalDate endDate; // End date of query
	private String location; // Location of query

	/**
	 * Holds data to be used to construct a query
	 * 
	 * @param start - Start date of query
	 * @param end   - end date of query
	 * @param loc   - location of query
	 */
	public QueryData(LocalDate start, LocalDate end, String loc) {
		setStartDate(start);
		setEndDate(end);
		setLocation(loc);
	}

	// Getters - Setters

	public LocalDate getStartDate() {
		return startDate;
	}

	private void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	private void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getLocation() {
		return location;
	}

	private void setLocation(String location) {
		this.location = location;
	}

}
