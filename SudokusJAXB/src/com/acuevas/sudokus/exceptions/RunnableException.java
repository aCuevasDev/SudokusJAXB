package com.acuevas.sudokus.exceptions;

/**
 * The program can keep running while this exceptions are thrown.
 * 
 * @author Alex
 *
 */
@SuppressWarnings("serial")
public class RunnableException extends Exception {

	private RunErrors error;

	public enum RunErrors {
		USER_NOT_FOUND_OR_INCORRECT_PASSWORD("User not found or incorrect password"), WRONG_TIME("Time incorrect"),
		PASSWORDS_DONT_MATCH("Passwords don't match"), USER_IN_USE("Username already in use"),
		NOT_SUPPORTED("NOT SUPPORTED"), NOT_PLAYING("You're not playing a sudoku"),
		SAME_PASSWORD("Can't use the same password"), NOT_PLAYED("You did not play a sudoku");

		private String message;

		private RunErrors(String message) {
			this.message = message;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {
			return message;
		}

	};

	public RunnableException(RunErrors error) {
		this.error = error;
	}

	@Override
	public String getMessage() {
		return error.message;
	}
}
