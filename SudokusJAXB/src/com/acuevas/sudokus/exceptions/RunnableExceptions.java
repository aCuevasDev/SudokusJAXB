package com.acuevas.sudokus.exceptions;

@SuppressWarnings("serial")
public class RunnableExceptions extends Exception {
//TODO EXCEPTIONS THAT DOESN'T STOP THE PROGRAM

	public enum RunErrors {
		USERNOTFOUNDORINCORRECTPASSWORD("");

		private String message;

		private RunErrors(String message) {
			this.message = message;
		}
	};

	public RunnableExceptions(RunErrors error) {

	}
}
