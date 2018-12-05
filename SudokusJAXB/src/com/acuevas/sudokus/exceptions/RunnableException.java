package com.acuevas.sudokus.exceptions;

/**
 * The program can keep running while this exceptions are thrown.
 * 
 * @author Alex
 *
 */
@SuppressWarnings("serial")
public class RunnableException extends Exception {
//TODO EXCEPTIONS THAT DOESN'T STOP THE PROGRAM

	public enum RunErrors {
		USER_NOT_FOUND_OR_INCORRECT_PASSWORD(""), WRONG_TIME(""), PASSWORDS_DONT_MATCH(""), USER_IN_USE(""),
		NOT_SUPPORTED("NOT SUPPORTED");

		private String message;

		private RunErrors(String message) {
			this.message = message;
		}
	};

	public RunnableException(RunErrors error) {

	}
}
