/**
 * @author John Ryan - john.ryan@drake.edu
 * CS 067 - Fall 2024
 * Dec 9th, 2024
 */
package model;

import java.io.IOException;

/**
 * This exception occurs when a user tries to enter 2 identical dates, or 2
 * dates where the second date occurs before the first date.
 */
public class ImproperDateSelectionException extends IOException {

	/**
	 * Eclipse complains if there is no serialVersionUID, so here it is
	 */
	private static final long serialVersionUID = 4153061172746943861L;

}
