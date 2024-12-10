/**
 * @author John Ryan - john.ryan@drake.edu
 * CS 067 - Fall 2024
 * Dec 9th, 2024
 */
package controller;

/**
 * This exception occurs if there has been an improperly selected date range.
 * In practice this means that the second date variable occurs before the first
 * date variable.
 */
public class ImproperDateRangeException extends Exception {

	/**
	 * Eclipse complains if there is no serialVersionUID, so here it is
	 */
	private static final long serialVersionUID = 3263262880435411002L;

}
