package com.acuevas.sudokus.views;

public class View {

	public enum Messages {
		ASK_TIME("How long did it take to complete the sudoku?."), ASK_USERNAME("Please, insert your username."),
		ASK_PASSWORD("Please, insert your password.");

		private String message;

		private Messages(String message) {
			this.message = message;
		}
	};

	public static void printError(String error) {
		System.err.println(error);
	}

	/**
	 * Prints the specified message on console, if nextLine uses println to jump
	 * line else stays on the same line
	 * 
	 * @param message
	 * @param nextLine
	 */
	public static void printMessage(Messages message, boolean nextLine) {
		if (nextLine)
			System.out.println(message);
		else
			System.out.print(message);
	}

}
