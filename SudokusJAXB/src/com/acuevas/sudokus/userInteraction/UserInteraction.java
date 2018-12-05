package com.acuevas.sudokus.userInteraction;

import java.util.List;

import com.acuevas.sudokus.model.Ranking;
import com.acuevas.sudokus.model.sudokus.Sudokus.Sudoku;

public abstract class UserInteraction {

	public enum Messages {
		ASK_TIME("How long did it take to complete the sudoku?"), ASK_USERNAME("Please, insert your username"),
		ASK_PASSWORD("Please, insert your password"), ASK_NAME("Please, insert your name"), AGAIN("again"),
		NEW_PASWORD("Insert your new password"), PSWRD_CHANGED("Password changed succesfully"),
		INCORRECT_PASSWORD("Incorrect password"), FINISH_SUDOKU("Did you finish the sudoku?"),
		CHOOSE_OPTION("Please, choose an option"), GOODBYE("Goodbye!"), WRONG_KEY("You pressed a wrong key"),
		MEAN_TIME("Your mean time is: ");

		private String message;

		private Messages(String message) {
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

	public final static String menu1 = " 1. Log in \n 2. Register \n 3. Exit";
	public final static String menu2 = "1. Play sudoku \n 2. Register a record(finish a sudoku) \n 3. Change password \n 4. See my mean time \n 5. Rankings \n 6. Log out";

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
			System.out.println(message + ".");
		else
			System.out.print(message + " ");
	}

	public static void printMenu(String menu) {
		System.out.println(menu);
	}

	public static void printString(String string) {
		System.out.println(string);
	}

	public static void printRankings(List<Ranking> sortedRankings) {
		sortedRankings.forEach(System.out::println);

	}

	public static void printSudoku(Sudoku playedSudoku) {
		System.out.println(playedSudoku);

	}

}
